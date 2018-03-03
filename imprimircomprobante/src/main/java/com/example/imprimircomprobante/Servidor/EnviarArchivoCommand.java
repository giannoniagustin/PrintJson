package com.example.imprimircomprobante.Servidor;

import com.example.imprimircomprobante.Archivos.ControladorArchivos;
import com.example.imprimircomprobante.Archivos.ElementoArchivo;

import java.io.InputStream;





/**
 * Created by soled_000 on 16/10/2015.
 */
public abstract class EnviarArchivoCommand extends ServidorCommand {

    protected ElementoArchivo archivo;

    protected String nombreUsuario;
    protected ControladorArchivos controladorArchivos;

    public void setArchivo(ElementoArchivo archivo) {
        this.archivo = archivo;
    }



    public void setControladorArchivos(ControladorArchivos controladorArchivos){
        this.controladorArchivos = controladorArchivos;
    }
    public void setUsuario(String usuario){
        this.nombreUsuario = usuario;
    }

    @Override
    public abstract void procesarRespuesta(InputStream s);



    public void Ejecutar(){

        super.server.enviarArchivoGeneral(archivo, this,nombreUsuario);
    }


    @Override
    public void Deshacer() {

    }


}
