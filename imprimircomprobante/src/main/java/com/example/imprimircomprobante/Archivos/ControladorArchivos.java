package com.example.imprimircomprobante.Archivos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.imprimircomprobante.Configuracion.Preferencias;
import com.example.imprimircomprobante.ManejadorServidor;
import com.example.imprimircomprobante.Servidor.EnviarArchivoCommand;
import com.example.imprimircomprobante.Servidor.EnviarArchivoTrackCommand;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;





public class ControladorArchivos /*implements AsyncRespuestaEnvioArchivos*/{

    private static final int BUFFER_SIZE = 1024;
    private String nombreArchivo;
    private Context contexto;

    private ManejadorServidor manejadorServidor;
    private Preferencias preferencia;
    public     final static String TARJETA_MEMORIA=android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
    public     final static String RAIZ_APP=TARJETA_MEMORIA+"/PrintJson";
    public     final static String DATA_USER=RAIZ_APP+"/DataUser";
    public     final static String PATH_LOG_USER_TXT=RAIZ_APP+"/Log";
    public     final static String NOMBRE_LOG_USER_TXT="Logging.txt";
    public     final static String NOMBRE_LOG_USER_HTML="Logging.txt";
    public     final static String LOG_USER_HTML=RAIZ_APP+"/Log"+"/Logging.html";
  //  public     final static String BD=RAIZ_APP+"/BD/"+BdHelper.DB_NAME;
    public     final static String PATH_ARCH_TRACK =RAIZ_APP+"/ArchImprimir";
    public     final static String NOMBRE_ARCH_TRACK="imprimir";
    public final static String EXTENSION_ZIP=".gz";
    public final static String EXTENSION_TXT=".txt";
    public final static String SALTO_LINEA="\r\n";

    //private BdHelper bdHelper;

    public ControladorArchivos(Context context){

            this.nombreArchivo = "";
            this.preferencia = new Preferencias();
            this.contexto = context;


      //OJO CON ESTE NEEEEEEEEW




    }

    public void setManejadorServidor(ManejadorServidor manejadorServidor) {
        this.manejadorServidor = manejadorServidor;
    }

//   public ControladorArchivos(Context context){contexto=context;}

    //Crea el command para enviar el archivo de trackeo al servidor
    public void enviarArchivoServidor(Archivo archivo/*String nombreArchivo, String path*/){

        EnviarArchivoCommand enviarArchivoTrackCommand = new EnviarArchivoTrackCommand();
        enviarArchivoTrackCommand.setControladorArchivos(this);
    //    String nombreUsuario = preferencia.getUsuario(contexto);
        enviarArchivoTrackCommand.setUsuario("PrintAndroid");

        enviarArchivoTrackCommand.setArchivo(archivo);

        manejadorServidor.agregarElemento(enviarArchivoTrackCommand);


   /*
        try {

            Log.LOGGER.info("Archivo a enviar: " + nombreArchivo);
            //llamado asincronico
            String usuario = preferencia.getUsuario(contexto);
            Log.LOGGER.info("Usuario archivo: "+usuario);
            this.nombreArchivo = nombreArchivo;
            this.pathArchivo=path;
            Log.LOGGER.info("Path archivo: " + path);
     //       this.usuarioActual.add((new BasicNameValuePair("usuario", usuario)));

            ArrayList<NameValuePair> parametrosConcatenar = new ArrayList<NameValuePair>();
            parametrosConcatenar.add(new BasicNameValuePair("usuario", usuario));

            HashMap<String, Object> parametros;
            parametros = new HashMap<String, Object>();
            parametros.put("url", URL_SERVIDOR);
            parametros.put("nombreArchivo", nombreArchivo);
            parametros.put("rutaArchivo", path);
            parametros.put("parametrosEnvio", parametrosConcatenar);

            SubirArchivoServidor enviarArchivo = new SubirArchivoServidor();
            enviarArchivo.setValor(this);
            enviarArchivo.execute(parametros, null, null);

        }catch (Exception e){
            Log.LOGGER.severe(e.toString()+"Archivo "+nombreArchivo+"Path: "+path);}
*/
    }

    public void zippearArchivo(String rutaArchivo, String rutaArchivoComprimido) throws Exception {
        // objetos en memoria
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ZipOutputStream zipos = null;

        // buffer
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            // ruta y nombre del fichero a comprimir
            fis = new FileInputStream(rutaArchivo);
            // ruta y nombre del fichero contenedor del zip
            fos = new FileOutputStream(rutaArchivoComprimido);
            // fichero comprimido
            zipos = new ZipOutputStream(fos);
            //     String pFile = RUTA_ARCHIVO.substring(RUTA_ARCHIVO.lastIndexOf(File.separator +1));
            ZipEntry entry = new ZipEntry(rutaArchivo.substring(rutaArchivo.lastIndexOf("/") + 1));
            //    ZipEntry zipEntry = new ZipEntry(pFile);
            zipos.putNextEntry(entry);
            int len = 0;
            // zippear
            while ((len = fis.read(buffer, 0, BUFFER_SIZE)) != -1)
                zipos.write(buffer, 0, len);
            // volcar la memoria al disco
            zipos.flush();
        } catch (Exception e) {
            throw e;
        } finally {
            // cerramos los files
            zipos.close();
            fis.close();
            fos.close();
        } // end try
    } // end Zippear






    public Archivo crearArchivoTrack(String nombreArchivo){

        Archivo archivoTrack=new Archivo("txt",ControladorArchivos.PATH_ARCH_TRACK,nombreArchivo);

        return archivoTrack;



    }





}
