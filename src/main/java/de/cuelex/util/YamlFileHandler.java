package de.cuelex.util;

import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.util
    Date: 23.05.2021
    
*/
public class YamlFileHandler {

    private final YamlFile configFile = new YamlFile("config.yml");
    public void loadConfigFile(){
        try {
            configFile.load();
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }
    public YamlFile getConfigFile() {
        return configFile;
    }

    /**
     * Get a file string by using YAML
     *
     * @param filepath   is the file-key
     * @param stringpath is the file-objectr
     * @return a string
     */
    public String getFileString(String filepath, String stringpath) {
        YamlFile yamlFile = new YamlFile(filepath);
        try {
            yamlFile.load();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return yamlFile.getString(stringpath);
    }

    /**
     * Copy a file in another directory
     *
     * @param in  is the input-file
     * @param out is the output-file
     * @throws IOException necessary because of the IOException
     */
    public void copyFile(File in, File out) throws IOException {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = new FileInputStream(in).getChannel();
            outChannel = new FileOutputStream(out).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (inChannel != null)
                    inChannel.close();
                if (outChannel != null)
                    outChannel.close();
            } catch (IOException e) {
            }
        }
    }
}
