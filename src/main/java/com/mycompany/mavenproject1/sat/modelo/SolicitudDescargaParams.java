package com.mycompany.mavenproject1.sat.modelo;

import java.time.LocalDate;

/**
 * Parámetros de una solicitud de descarga masiva de CFDI ante el SAT.
 */
public class SolicitudDescargaParams {

    private final String rfcSolicitante;
    private final LocalDate fechaInicial;
    private final LocalDate fechaFinal;
    private final TipoConsultaCfdi tipo;

    public SolicitudDescargaParams(String rfcSolicitante, LocalDate fechaInicial, LocalDate fechaFinal, TipoConsultaCfdi tipo) {
        this.rfcSolicitante = rfcSolicitante;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.tipo = tipo;
    }

    public String getRfcSolicitante() {
        return rfcSolicitante;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public TipoConsultaCfdi getTipo() {
        return tipo;
    }
}
