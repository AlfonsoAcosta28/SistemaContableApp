package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class Filas {

    private List<FilaItem> items;
    private List<FilaItemConfig> configFiltros;
    private final Runnable onUpdate;

    public Filas(List<FilaItem> items, Runnable onUpdate) {
        this.items = items;
        this.onUpdate = onUpdate;
        this.configFiltros = new ArrayList<>();
    }

    public JPanel generar(JPanel panelCheckboxesFilas) {
        configFiltros.clear();
        panelCheckboxesFilas.removeAll();
        panelCheckboxesFilas.setLayout(new BoxLayout(panelCheckboxesFilas, BoxLayout.Y_AXIS));
        panelCheckboxesFilas.setBackground(java.awt.Color.WHITE);

        for (FilaItem item : items) {
            JPanel panelCategoria = new JPanel();
            panelCategoria.setLayout(new BoxLayout(panelCategoria, BoxLayout.Y_AXIS));
            panelCategoria.setBackground(new java.awt.Color(240, 240, 245));
            panelCategoria.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createEmptyBorder(6, 4, 2, 4),
                    javax.swing.BorderFactory.createCompoundBorder(
                            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 200), 1, true),
                            javax.swing.BorderFactory.createEmptyBorder(4, 6, 4, 6)
                    )
            ));

            JLabel lblCategoria = new JLabel(item.getNombre());
            lblCategoria.setFont(lblCategoria.getFont().deriveFont(java.awt.Font.BOLD, 11f));
            lblCategoria.setForeground(new java.awt.Color(60, 60, 120));
            lblCategoria.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            lblCategoria.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 3, 0));
            panelCategoria.add(lblCategoria);

            JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
            sep.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 1));
            sep.setForeground(new java.awt.Color(180, 180, 200));
            panelCategoria.add(sep);
            panelCategoria.add(javax.swing.Box.createVerticalStrut(3));

            FilaItemConfig config = new FilaItemConfig(item.getColumnaFiltro());
            for (ItemValor iv : item.getItems()) {
                JCheckBox cb = new JCheckBox(iv.getEtiqueta(), true);
                cb.setToolTipText(iv.getValor());
                cb.setBackground(new java.awt.Color(240, 240, 245));
                cb.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
                cb.addActionListener(evt -> onUpdate.run());
                config.agregar(cb, iv.getValor());
                panelCategoria.add(cb);
            }
            configFiltros.add(config);
            panelCategoria.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            panelCheckboxesFilas.add(panelCategoria);
        }
        return panelCheckboxesFilas;
    }

    public List<FilaItemConfig> getConfigFiltros() {
        return configFiltros;
    }

    public List<FilaItem> getItems() {
        return items;
    }

    public void setItems(List<FilaItem> items) {
        this.items = items;
    }
}
