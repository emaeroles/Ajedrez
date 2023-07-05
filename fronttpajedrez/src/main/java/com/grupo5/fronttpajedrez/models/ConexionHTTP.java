package com.grupo5.fronttpajedrez.models;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConexionHTTP {

    public ConexionHTTP(){

    }

    public StringBuilder get(String ruta) throws IOException {
        URL url = new URL("http://localhost:8080/" + ruta);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");

        StringBuilder informationString = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());
        informationString.append(scanner.nextLine());

        scanner.close();

        return informationString;
    }

    public void postPut(String ruta, String metodo, String json) throws IOException {
        URL url = new URL("http://localhost:8080/" + ruta);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod(metodo);
        if(json != null){
            conexion.setDoOutput(true);
            conexion.setRequestProperty("Content-Type", "application/json");

            OutputStream outputStream = conexion.getOutputStream();
            outputStream.write(json.getBytes());
            outputStream.close();
        }
        conexion.getResponseCode();
    }

    public void delete(String ruta) throws IOException {
        URL url3 = new URL("http://localhost:8080/" + ruta);
        HttpURLConnection conexion3 = (HttpURLConnection) url3.openConnection();
        conexion3.setRequestMethod("DELETE");
        conexion3.getResponseCode();
    }
}
