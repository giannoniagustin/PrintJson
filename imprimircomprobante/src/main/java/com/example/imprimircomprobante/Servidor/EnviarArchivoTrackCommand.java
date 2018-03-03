package com.example.imprimircomprobante.Servidor;

import com.example.imprimircomprobante.Archivos.Log;
import com.example.imprimircomprobante.Parser.ManejadorParser;

import java.io.InputStream;



/**
 * Created by soled_000 on 16/10/2015.
 */
public class EnviarArchivoTrackCommand extends EnviarArchivoCommand {



    @Override
    public void procesarRespuesta(InputStream s) {
        try {


            ManejadorParser manejadorParser = new ManejadorParser();

            Integer valor = manejadorParser.parsearRespuesta(s);
            if (valor == 1) {//Envio con exito


               Log.LOGGER.info("Respuesta servidor archivo enviado: " + super.archivo.getNombre());

                super.archivo.borrarArchivo();
                Log.LOGGER.info("Se borro el archivo "+ super.archivo.getNombre());

            } else {
                if (valor == 0 || valor == 2) {//Error al enviar


                    Log.LOGGER.severe("Error al enviar archivo");


                }
            }


        } catch (Exception e) {

            Log.LOGGER.severe(e.toString());

            e.printStackTrace();

        }
    }
}
