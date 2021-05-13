package de.cuelex.util;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/*

    Copyright © 2019-2021 Alexander F.
    Twitter: @Taventiksch
    Location: HomeCloud/de.cuelex.util
    Date: 26.01.2021
    
*/
public class JSONWebDataHandler {

    public JSONObject jsonObject;

    /**
     * Getting the whole data from a website
     *
     * @param url is the url of the website
     */
    public void getDataFromWesbite(String url) {
        try {
            jsonObject = getObjectFromWebsite(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a specific object from a website
     * @param url is the url of the website
     * @return a JSONObject
     * @throws IOException
     */
    public JSONObject getObjectFromWebsite(final String url) throws IOException {
        final InputStream inputStream = new URL(url).openStream();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            final String rawJson = read(bufferedReader);
            final JSONObject jsonObject = new JSONObject(rawJson);
            return jsonObject;
        } finally {
            inputStream.close();
        }
    }

    /**
     * Reading the raw json
     * @param reader is the necessary reader, for Example: "BufferedReader"
     * @return a string by using the StringBuilder
     * @throws IOException
     */
    public String read(final Reader reader) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        int count;
        while ((count = reader.read()) != -1) {
            stringBuilder.append((char) count);
        }
        return stringBuilder.toString();
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }


    public void createJsonFile(String name) {
        File file = new File(name);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(new File(name));
            fileWriter.write("{ }");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setting a string to a value in a json file
     *
     * @param path  file-path
     * @param key   object-key
     * @param value object-value
     */
    public void setJsonString(String path, String key, String value) {

        try {
            File file = new File(path);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(content);
            jsonObject.put(key, value);
            FileWriter fileWriter = new FileWriter(new File(path));
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getting a string
     *
     * @param path  file-path
     * @param key   object-key
     */
    public String getJsonString(String path, String key) {

        try {
            File file = new File(path);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(content);
            return jsonObject.getString(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject.getString(key);
    }

    /**
     * Getting a int
     *
     * @param path  file-path
     * @param key   object-key
     */
    public Integer getJsonInt(String path, String key) {

        try {
            File file = new File(path);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(content);
            return jsonObject.getInt(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject.getInt(key);
    }

    /**
     * Setting a boolean to a value in a json file
     *
     * @param path  file-path
     * @param key   object-key
     * @param value object-value
     */
    public void setJsonBoolean(String path, String key, boolean value) {

        try {
            File file = new File(path);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(content);
            jsonObject.put(key, value);
            FileWriter fileWriter = new FileWriter(new File(path));
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setting an array-list to a value in a json file
     *
     * @param path  file-path
     * @param key   object-key
     * @param value object-value
     */
    public void setJsonArrayList(String path, String key, ArrayList<String> value) {

        try {
            File file = new File(path);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(content);
            jsonObject.put(key, toJSON(value));
            FileWriter fileWriter = new FileWriter(new File(path));
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setting an integer to a value in a json file
     *
     * @param path  file-path
     * @param key   object-key
     * @param value object-value
     */
    public void setJsonInteger(String path, String key, Integer value) {

        try {
            File file = new File(path);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(content);
            jsonObject.put(key, value);
            FileWriter fileWriter = new FileWriter(new File(path));
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getting an object in a json file
     *
     * @param path file-path
     * @param key  object-key
     * @return json-object
     */
    public Object getJsonObject(String path, String key) {

        try {
            File file = new File(path);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(content);
            return jsonObject.get(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Converting an array-list to a string
     *
     * @param list object-value
     * @return string
     */
    public String toJSON(ArrayList<String> list) {
        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(gson.toJson(s));
        }
        return sb.toString();
    }


}
