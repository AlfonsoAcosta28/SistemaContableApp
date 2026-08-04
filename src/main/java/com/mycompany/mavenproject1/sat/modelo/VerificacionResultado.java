package com.mycompany.mavenproject1.sat.modelo;

import java.util.Collections;
import java.util.List;

/**
 * Respuesta del servicio de Verificación de Solicitud de Descarga del SAT.
 */
public class VerificacionResultado {

    /** 1=Aceptada, 2=EnProceso, 3=Terminada, 4=Error, 5=Rechazada, 6=Vencida */
    private final int estadoSolicitud;
    private final String codigoEstadoSolicitud;
    private final String mensaje;
    private final List<String> idsPaquetes;

    public VerificacionResultado(int estadoSolicitud, String codigoEstadoSolicitud, String mensaje, List<String> idsPaquetes) {
        this.estadoSolicitud = estadoSolicitud;
        this.codigoEstadoSolicitud = codigoEstadoSolicitud;
        this.mensaje = mensaje;
        this.idsPaquetes = idsPaquetes == null ? Collections.emptyList() : idsPaquetes;
    }

    public int getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public String getCodigoEstadoSolicitud() {
        return codigoEstadoSolicitud;
    }

    public String getMensaje() {
        return mensaje;
    }

    public List<String> getIdsPaquetes() {
        return idsPaquetes;
    }

    public boolean isTerminada() {
        return estadoSolicitud == 3;
    }

    public boolean isEnProceso() {
        return estadoSolicitud == 1 || estadoSolicitud == 2;
    }
}
