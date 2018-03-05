package com.example.imprimircomprobante.Servidor;



import com.example.imprimircomprobante.Archivos.ElementoArchivo;
import com.example.imprimircomprobante.Archivos.Log;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;






/**
 * Created by Agustín on 06/10/2015.
 */
public class Server {


    private static final String IP="192.168.1.106";
 // private static final String IP="169.254.157.165";
    private static final String URL_SERVIDOR = "http://"+IP+":8080/PrintJsonServer/AlmacenarArchivoServlet";

     private HttpClient client ;


    public Server() {



    }

    public HttpClient getClientUsuario(){
        return client;
    }
    public void setClient(HttpClient client){
        this.client = client;
    }


    public void enviarArchivoGeneral(ElementoArchivo archivo, EnviarArchivoCommand enviarArchivoCommand, String nombreUsuario){
        //Preparar parametros

        try {
        //    contexto=pcontexto;

            Log.LOGGER.info("Archivo a enviar: " + archivo.getNombre());
            //llamado asincronico

            Log.LOGGER.info("Usuario archivo: " + nombreUsuario);

            Log.LOGGER.info("Path archivo: " + archivo.getPath());


            ArrayList<NameValuePair> parametrosConcatenar = new ArrayList<NameValuePair>();

            parametrosConcatenar.add(new BasicNameValuePair("nombreArchivo", archivo.getNombre()));


            HashMap<String, Object> parametros;
            parametros = new HashMap<String, Object>();
            parametros.put("url", URL_SERVIDOR);
            parametros.put("rutaArchivo",archivo.getPath());
            parametros.put("nombreArchivo", archivo.getNombre());
            parametros.put("parametrosEnvio", parametrosConcatenar);




            SubirArchivosServidorTomcat enviarArchivo = new SubirArchivosServidorTomcat(enviarArchivoCommand, client);
            enviarArchivo.execute(parametros, null, null);

        }catch (Exception e){
            Log.LOGGER.severe(e.toString()+"Archivo "+archivo.getNombre()+"Path: "+archivo.getPath());
            e.printStackTrace();}

    }

}



