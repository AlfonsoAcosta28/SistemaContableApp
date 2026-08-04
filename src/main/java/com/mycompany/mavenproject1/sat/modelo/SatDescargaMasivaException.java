package com.mycompany.mavenproject1.sat.modelo;

/**
 * Excepción de negocio del flujo de descarga masiva de CFDI: el mensaje
 * ya viene listo en español para mostrarse directamente al usuario.
 */
public class SatDescargaMasivaException extends Exception {

    public SatDescargaMasivaException(String mensaje) {
        super(mensaje);
    }

    public SatDescargaMasivaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
