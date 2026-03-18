package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;

/**
 * Almacena la configuración de un filtro de filas: columna + checkboxes con sus valores.
 */
public class FilaItemConfig {
    private final String columnaFiltro;
    private final List<CheckboxValor> checkboxes = new ArrayList<>();

    public FilaItemConfig(String columnaFiltro) {
        this.columnaFiltro = columnaFiltro;
    }

    public void agregar(JCheckBox cb, String valor) {
        checkboxes.add(new CheckboxValor(cb, valor));
    }

    public String getColumnaFiltro() {
        return columnaFiltro;
    }

    public List<String> getValoresSeleccionados() {
        List<String> sel = new ArrayList<>();
        for (CheckboxValor cv : checkboxes) {
            if (cv.checkbox.isSelected()) {
                sel.add(cv.valor);
            }
        }
        return sel;
    }

    private static class CheckboxValor {
        final JCheckBox checkbox;
        final String valor;

        CheckboxValor(JCheckBox cb, String v) {
            checkbox = cb;
            valor = v;
        }
    }
}
