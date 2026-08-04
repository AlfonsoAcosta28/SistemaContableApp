package com.mycompany.mavenproject1.sat.controlador;

import java.io.File;

/**
 * Callbacks que la Vista implementa para enterarse del avance de una
 * descarga masiva de CFDI, sin que el Controlador necesite conocer a
 * VistaP. Todas las llamadas se hacen en el Event Dispatch Thread.
 */
public interface SatDescargaListener {

    void onProgreso(String mensaje);

    void onExito(File carpetaDestino, int totalXmlDescargados);

    void onError(String mensaje);
}
