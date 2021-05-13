package de.cuelex.util.api;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: HomeCloud/de.cuelex.util.api
    Date: 13.05.2021
    
*/
public class GoogleAuthenticationHandler {

    private boolean isAuthorized = true;
    private final GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
    private final GoogleAuthenticatorKey googleAuthenticatorKey = googleAuthenticator.createCredentials();
    private String googleAuthKey;
    private int keyPW;
    String barCodeUrl = getGoogleAuthenticatorBarCode(googleAuthenticatorKey.getKey(), "taventix@gmail.com", "HomeCloud");
    public boolean isAuthorized() {
        return isAuthorized;
    }
    public void setAuthorized(boolean locked) {
        isAuthorized = locked;
    }

    public void a() {
        File file = new File("qr.png");
        if(file.exists()){
            file.delete();
        }
        String key = googleAuthenticatorKey.getKey();
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, GoogleAuthenticationHandler.class, "Your 2FA-Code is: " + key);
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, GoogleAuthenticationHandler.class, "Please type in your Auth-Code.");
        setGoogleAuthKey(key);
        try {
            createQRCode(barCodeUrl, "qr.png", 300, 300);
            JFrame frame = new JFrame();
            ImageIcon icon = new ImageIcon("qr.png");
            JLabel label = new JLabel(icon);
            frame.add(label);
            frame.setDefaultCloseOperation
                    (JFrame.EXIT_ON_CLOSE);
            frame.pack();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int height = screenSize.height;
            int width = screenSize.width;
            frame.setSize(width/4,  height/3);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    public void b(int code) {
        isAuthorized = googleAuthenticator.authorize(googleAuthenticatorKey.getKey(), code);
        if (isAuthorized) {
            ConsoleLogger.getInstance().log(ConsoleLoggerType.SUCCESS, GoogleAuthenticationHandler.class,"Successfully authorized!");
            setAuthorized(true);
        } else {
            ConsoleLogger.getInstance().log(ConsoleLoggerType.ERROR, GoogleAuthenticationHandler.class,"Error during authentication process! Please restart the application.");
            System.exit(0);
        }
    }


    public String getGoogleAuthKey() {
        return googleAuthKey;
    }

    public void setGoogleAuthKey(String googleAuthKey) {
        this.googleAuthKey = googleAuthKey;
    }

    public int getKeyPW() {
        return keyPW;
    }

    /**
     *
     * @param secretKey googleAuthenticatorKey.getKey()
     * @param account Usually the mail of the Google-Account
     * @param issuer Company-Name used by Google for labeling
     * @return String of barcode
     */
    public static String getGoogleAuthenticatorBarCode(String secretKey, String account, String issuer) {
        try {
            return "otpauth://totp/"
                    + URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20")
                    + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
                    + "&issuer=" + URLEncoder.encode(issuer, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Creating QR-Code
     * @param barCodeData barCodeUrl
     * @param filePath picture-file the QR-Code will get generated
     * @param height Height of the Pop-Up
     * @param width Width of the Pop-Up
     */
    public static void createQRCode(String barCodeData, String filePath, int height, int width)
            throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE,
                width, height);
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            MatrixToImageWriter.writeToStream(matrix, "png", out);
        }
    }

}
