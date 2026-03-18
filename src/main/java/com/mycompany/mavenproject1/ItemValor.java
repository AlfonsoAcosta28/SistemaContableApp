package com.mycompany.mavenproject1;

/**
 * Par valor (para filtrar) y etiqueta (para mostrar en UI).
 */
public class ItemValor {
    private final String valor;
    private final String etiqueta;

    public ItemValor(String valor, String etiqueta) {
        this.valor = valor;
        this.etiqueta = etiqueta != null && !etiqueta.isEmpty() ? etiqueta : valor;
    }

    public ItemValor(String valor) {
        this(valor, valor);
    }

    public String getValor() {
        return valor;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
