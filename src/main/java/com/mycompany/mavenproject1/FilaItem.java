package com.mycompany.mavenproject1;

import java.util.List;

/**
 * Define una fila de filtro: nombre, columna a filtrar y lista de valores con etiquetas.
 */
public class FilaItem {
    private String nombre;
    private String columnaFiltro;
    private List<ItemValor> items;

    public FilaItem(String nombre, String columnaFiltro, List<ItemValor> items) {
        this.nombre = nombre;
        this.columnaFiltro = columnaFiltro;
        this.items = items;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColumnaFiltro() {
        return columnaFiltro;
    }

    public void setColumnaFiltro(String columnaFiltro) {
        this.columnaFiltro = columnaFiltro;
    }

    public List<ItemValor> getItems() {
        return items;
    }

    public void setItems(List<ItemValor> items) {
        this.items = items;
    }
}
