package com.example.imprimircomprobante.Servidor;



import java.io.InputStream;



/**
 * Created by Agustín on 05/10/2015.
 */
public abstract class ServidorCommand implements Command {


    protected Server server;

    public void setServer(Server s){
        this.server = s;
    }

    public abstract void procesarRespuesta(InputStream s);

}
