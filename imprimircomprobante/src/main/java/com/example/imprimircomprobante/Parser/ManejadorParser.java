package com.example.imprimircomprobante.Parser;

import com.example.imprimircomprobante.Archivos.Log;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;




/**
 * Created by Agustín on 13/10/2015.
 */
public class ManejadorParser {



    public ManejadorParser() {

    }


    public String parsearRespuestaString(InputStream in){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(in));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (Exception e) {
            Log.LOGGER.severe(e.toString());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    Log.LOGGER.severe(e.toString());
                }
            }
        }

        String response = sb.toString();
        return response;
    }

    public String LeerSaldo(InputStream in) {

        try {
            String saldo =  this.parsearRespuestaString(in);
            return saldo;
        } catch (Exception e) {
            Log.LOGGER.severe(e.toString());
            return null;
        }
    }
    
    public Integer parsearRespuesta(InputStream in){

        return(Integer.parseInt(parsearRespuestaString(in).trim()));
    }


}
