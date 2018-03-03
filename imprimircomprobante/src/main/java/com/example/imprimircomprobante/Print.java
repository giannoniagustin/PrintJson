package com.example.imprimircomprobante;

import com.example.imprimircomprobante.Archivos.Archivo;
import com.example.imprimircomprobante.Archivos.ControladorArchivos;
import com.example.imprimircomprobante.Archivos.Log;

import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Gianno on 1/3/2018.
 */

public class Print {

    DefaultHttpClient c;

    public boolean imprimir(String combrobante)
    {
        try {
            Log.setup();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControladorArchivos controladorArchivos= new ControladorArchivos(null);
        ManejadorServidor manejadorServidor= new ManejadorServidor();

        c = new DefaultHttpClient();
        manejadorServidor.getServer().setClient(c);

        controladorArchivos.setManejadorServidor(manejadorServidor);


        Archivo archivo= controladorArchivos.crearArchivoTrack();
      archivo.grabarArchivo(combrobante);

      controladorArchivos.enviarArchivoServidor(archivo);




      System.out.println("Se imprimio"+ archivo.isExistente());
        return false;

    }
}
