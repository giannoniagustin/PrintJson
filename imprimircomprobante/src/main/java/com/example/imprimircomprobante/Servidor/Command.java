package com.example.imprimircomprobante.Servidor;

/**
 * Created by Agustín on 05/10/2015.
 */
public interface Command {

    void Ejecutar();
    void Deshacer();
}
