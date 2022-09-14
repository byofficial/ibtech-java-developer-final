package com.ibtech.mall.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class StreamHelper {
    public static void write(OutputStream out, String output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
        writer.write(output + "\r\n");
        writer.flush();
        out.flush();
        out.close();
        writer.close();
    }

    public static String read(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line).append("\r\n");
        }
        reader.close();
        return builder.toString();
    }

    public static String post(String address, String output) throws IOException {
        URL url = new URL(address);
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        StreamHelper.write(connection.getOutputStream(), output);
        return StreamHelper.read(connection.getInputStream());
    }

    public static String get(String address) throws IOException {
        URL url = new URL(address);
        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();
        return read(in);
    }

}

