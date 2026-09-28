/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import com.mycompany.mavenproject1.sat.controlador.SatDescargaListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//Importes para exportar en excel
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javax.swing.ButtonGroup;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableRowSorter;

public class VistaP extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VistaP.class.getName());
    private DefaultTableModel tableModel;
    private ArrayList<String> nombreBotones = new ArrayList<>();

    // Almacenar todas las columnas y sus TableColumn para mostrar/ocultar
    private List<String> todasLasColumnas = new ArrayList<>();
    private List<TableColumn> todasLasTableColumns = new ArrayList<>();
    private List<JCheckBox> checkboxesColumnas = new ArrayList<>();
    private final LocalDateTime ahora = LocalDateTime.now();
    private final DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
    private TableRowSorter<DefaultTableModel> rowSorter;

    private static final Set<String> COLUMNAS_POR_DEFECTO = Set.of(
            "fecha", "folio", "emisor", "subtotal",
            "total", "importe", "descuento", "formadepagop",
            "iva trasladado", "iva retenido", "isr retenido"
    );

    /** Códigos de impuesto SAT CFDI 4.0: 001=ISR, 002=IVA, 003=IEPS */
    private static final Map<String, String> NOMBRES_IMPUESTO = Map.of(
            "001", "ISR", "002", "IVA", "003", "IEPS"
    );

    private JPanel panelCheckboxesColumnas = new JPanel();
    private JPanel panelCheckboxesFilas = new JPanel();
    private JPanel panelAjustes;
    private JCheckBox checkFacturas;
    private ButtonGroup ajustesGrupo = new ButtonGroup();

    private JRadioButton checkSeleccionarTodas;
    private JRadioButton checkPorDefecto;

    private List<FilaItemConfig> configFiltrosFilas = new ArrayList<>();

    // --- Descarga masiva de CFDI (SAT) ---
    private File archivoCer;
    private File archivoKey;
    private JCheckBox checkEmitidos;
    private JCheckBox checkRecibidos;
    private final com.mycompany.mavenproject1.sat.controlador.SatDescargaMasivaController satController
            = new com.mycompany.mavenproject1.sat.controlador.SatDescargaMasivaController();

    public VistaP() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            System.getLogger(VistaP.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InstantiationException ex) {
            System.getLogger(VistaP.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IllegalAccessException ex) {
            System.getLogger(VistaP.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            System.getLogger(VistaP.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        initComponents();
        table.setModel(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        botonesPorDefecto();
        crearCheckboxesFilas();
        crearOpcionesDescargaSAT();
    }

    /** Agrega, por código (sin tocar el .form), los checkboxes de tipo de consulta SAT. */
    private void crearOpcionesDescargaSAT() {
        checkEmitidos = new JCheckBox("Emitidos", true);
        checkRecibidos = new JCheckBox("Recibidos", true);
        checkEmitidos.setBackground(java.awt.Color.WHITE);
        checkRecibidos.setBackground(java.awt.Color.WHITE);
        jPanel3.add(checkEmitidos);
        jPanel3.add(checkRecibidos);
        jPanel3.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        panelOpciones = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnZip = new javax.swing.JButton();
        btnFolder = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btnExportExcel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        jDateInit = new com.toedter.calendar.JDateChooser();
        jDateFinish = new com.toedter.calendar.JDateChooser();
        btnCER = new javax.swing.JButton();
        btnKEY = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        btnSearchSAT = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPaneColumnas = new javax.swing.JScrollPane();
        jScrollPaneFilas = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        panelOpciones.setBackground(new java.awt.Color(255, 102, 153));
        panelOpciones.setLayout(new java.awt.GridLayout(2, 0));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel6.setFocusCycleRoot(true);
        jPanel6.setInheritsPopupMenu(true);
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 50, 0));

        btnZip.setText("Importar Zip");
        btnZip.addActionListener(this::btnZipActionPerformed);
        jPanel7.add(btnZip);

        btnFolder.setText("Importar Carpeta");
        btnFolder.addActionListener(this::btnFolderActionPerformed);
        jPanel7.add(btnFolder);

        jPanel6.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        btnExportExcel.setText("Exportar a Excel");
        btnExportExcel.addActionListener(this::btnExportExcelActionPerformed);
        jPanel8.add(btnExportExcel);

        jPanel6.add(jPanel8);

        panelOpciones.add(jPanel6);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 50, 0));

        lblEstado.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jPanel3.add(lblEstado);
        jPanel3.add(jDateInit);
        jPanel3.add(jDateFinish);

        btnCER.setText("Seleccionar .cer");
        btnCER.addActionListener(this::btnCERActionPerformed);
        jPanel3.add(btnCER);

        btnKEY.setText("Seleccionar .key");
        btnKEY.addActionListener(this::btnKEYActionPerformed);
        jPanel3.add(btnKEY);
        jPanel3.add(txtPassword);

        btnSearchSAT.setText("Consulta SAT");
        btnSearchSAT.addActionListener(this::btnSearchSATActionPerformed);
        jPanel3.add(btnSearchSAT);

        panelOpciones.add(jPanel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.weightx = 99.0;
        gridBagConstraints.weighty = 1.3;
        jPanel1.add(panelOpciones, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table);

        jPanel4.add(jScrollPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 90.0;
        jPanel1.add(jPanel4, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPaneColumnas.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneColumnas.setBorder(null);
        jTabbedPane1.addTab("Columnas", jScrollPaneColumnas);

        jScrollPaneFilas.setBorder(null);
        jTabbedPane1.addTab("Filas", jScrollPaneFilas);

        jPanel5.add(jTabbedPane1);
        jTabbedPane1.getAccessibleContext().setAccessibleName("Columnas\nFilas");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 90.0;
        jPanel1.add(jPanel5, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(252, 252, 252));

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 48)); // NOI18N
        jLabel1.setText("Sistema Contable");
        jPanel2.add(jLabel1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnZipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZipActionPerformed
        seleccionarYExtraerZip();
//        checkFacturas.setSelected(true);
//        checkFacturas.setVisible(true);
    }//GEN-LAST:event_btnZipActionPerformed

    private void btnFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFolderActionPerformed
        seleccionarYExtraerFolder();
//        checkFacturas.setSelected(true);
//        checkFacturas.setVisible(true);
    }//GEN-LAST:event_btnFolderActionPerformed

    private void btnExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcelActionPerformed
        exportarAExcel();
    }//GEN-LAST:event_btnExportExcelActionPerformed

    private void btnSearchSATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchSATActionPerformed
        Date initDate = jDateInit.getDate();
        Date finishDate = jDateFinish.getDate();
        char[] password = txtPassword.getPassword();

        if (archivoCer == null || archivoKey == null) {
            JOptionPane.showMessageDialog(this, "Seleccione el archivo .cer y el archivo .key de su e.firma.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (password.length == 0) {
            JOptionPane.showMessageDialog(this, "Capture la contraseña de la e.firma.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (initDate == null || finishDate == null) {
            JOptionPane.showMessageDialog(this, "Seleccione la fecha inicial y la fecha final.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (initDate.after(finishDate)) {
            JOptionPane.showMessageDialog(this, "La fecha inicial no puede ser posterior a la fecha final.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!checkEmitidos.isSelected() && !checkRecibidos.isSelected()) {
            JOptionPane.showMessageDialog(this, "Seleccione al menos un tipo de comprobante: Emitidos o Recibidos.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar carpeta destino para los CFDI descargados");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File carpetaDestino = fileChooser.getSelectedFile();

        LocalDate fechaInicial = initDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaFinal = finishDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        btnSearchSAT.setEnabled(false);
        lblEstado.setText("Iniciando descarga masiva de CFDI...");

        satController.descargar(archivoCer, archivoKey, password, fechaInicial, fechaFinal,
                checkEmitidos.isSelected(), checkRecibidos.isSelected(), carpetaDestino, new SatDescargaListener() {
            @Override
            public void onProgreso(String mensaje) {
                lblEstado.setText(mensaje);
            }

            @Override
            public void onExito(File carpeta, int totalXmlDescargados) {
                btnSearchSAT.setEnabled(true);
                lblEstado.setText(totalXmlDescargados + " CFDI descargado(s) en " + carpeta.getName());
                JOptionPane.showMessageDialog(VistaP.this,
                        totalXmlDescargados + " CFDI descargado(s) correctamente en:\n" + carpeta.getAbsolutePath(),
                        "Descarga masiva completada", JOptionPane.INFORMATION_MESSAGE);
                if (totalXmlDescargados > 0) {
                    seleccionarYExtraerFolder(carpeta);
                }
            }

            @Override
            public void onError(String mensaje) {
                btnSearchSAT.setEnabled(true);
                lblEstado.setText("Error en la descarga masiva de CFDI");
                JOptionPane.showMessageDialog(VistaP.this, mensaje, "Error al descargar CFDI del SAT",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }//GEN-LAST:event_btnSearchSATActionPerformed

    private void btnCERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCERActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar certificado .cer de la e.firma");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Certificado e.firma (*.cer)", "cer"));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            archivoCer = fileChooser.getSelectedFile();
            btnCER.setText(archivoCer.getName());
            btnCER.setToolTipText(archivoCer.getAbsolutePath());
        }
    }//GEN-LAST:event_btnCERActionPerformed

    private void btnKEYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKEYActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar llave privada .key de la e.firma");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Llave privada e.firma (*.key)", "key"));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            archivoKey = fileChooser.getSelectedFile();
            btnKEY.setText(archivoKey.getName());
            btnKEY.setToolTipText(archivoKey.getAbsolutePath());
        }
    }//GEN-LAST:event_btnKEYActionPerformed

    private void checkPorDefectoItemStateChanged(java.awt.event.MouseEvent evt) {
        seleccionarPorDefecto();
    }

    private void checkSeleccionarTodasItemStateChanged(java.awt.event.MouseEvent evt) {
        seleccionarTodas();
    }

    private void checkFacturasItemStateChanged(java.awt.event.ItemEvent evt) {
        aplicarFiltroCompleto();
    }

    private void botonesPorDefecto() {
        panelCheckboxesColumnas.setLayout(new BoxLayout(panelCheckboxesColumnas, BoxLayout.Y_AXIS));
        panelCheckboxesColumnas.setBackground(java.awt.Color.WHITE);
        panelAjustes = new JPanel();

        panelAjustes.setLayout(new BoxLayout(panelAjustes, BoxLayout.Y_AXIS));
        panelAjustes.setBackground(new java.awt.Color(240, 240, 245));
        panelAjustes.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createEmptyBorder(6, 4, 2, 4),
                javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 200), 1, true),
                        javax.swing.BorderFactory.createEmptyBorder(4, 6, 4, 6)
                )
        ));

        // Encabezado de categoría
        JLabel lblCategoria = new JLabel("Ajustes");
        lblCategoria.setFont(lblCategoria.getFont().deriveFont(java.awt.Font.BOLD, 11f));
        lblCategoria.setForeground(new java.awt.Color(60, 60, 120));
        lblCategoria.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        lblCategoria.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 3, 0));
        panelAjustes.add(lblCategoria);

        // Separador fino bajo el título
        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        sep.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 1));
        sep.setForeground(new java.awt.Color(180, 180, 200));
        panelAjustes.add(sep);
        panelAjustes.add(javax.swing.Box.createVerticalStrut(3));

        checkFacturas = new JCheckBox("Datos en 0", true);
        checkFacturas.setBackground(new java.awt.Color(240, 240, 245));
        checkFacturas.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        checkFacturas.addItemListener(this::checkFacturasItemStateChanged);
        panelAjustes.add(checkFacturas);

        JSeparator sep2 = new JSeparator(JSeparator.HORIZONTAL);
        sep2.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 1));
        sep2.setForeground(new java.awt.Color(180, 180, 200));
        panelAjustes.add(sep2);
        panelAjustes.add(javax.swing.Box.createVerticalStrut(3));

        checkPorDefecto = new JRadioButton("Valores por defecto", true);
        checkPorDefecto.setBackground(new java.awt.Color(240, 240, 245));
        checkPorDefecto.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        checkPorDefecto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkPorDefectoItemStateChanged(evt);
            }
        });
        ajustesGrupo.add(checkPorDefecto);
        panelAjustes.add(checkPorDefecto);

        checkSeleccionarTodas = new JRadioButton("Seleccionar todas", false);
        checkSeleccionarTodas.setBackground(new java.awt.Color(240, 240, 245));
        checkSeleccionarTodas.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        ajustesGrupo.add(checkSeleccionarTodas);
        checkSeleccionarTodas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkSeleccionarTodasItemStateChanged(evt);
            }
        });
        panelAjustes.add(checkSeleccionarTodas);

        panelAjustes.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        panelCheckboxesColumnas.add(panelAjustes);
    }

    private int encontrarColumnaTotal() {
        return encontrarColumnaPorEtiqueta("total");
    }

    private int encontrarColumnaPorEtiqueta(String etiqueta) {
        return encontrarColumnaPorEtiqueta(etiqueta, null);
    }

    private int encontrarColumnaPorEtiqueta(String etiqueta, String etiquetaAlternativa) {
        String e1 = etiqueta.toLowerCase();
        String e2 = etiquetaAlternativa != null ? etiquetaAlternativa.toLowerCase() : null;
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            String corta = obtenerEtiquetaCorta(tableModel.getColumnName(i)).toLowerCase();
            if (corta.equals(e1) || (e2 != null && corta.equals(e2))) {
                return i;
            }
        }
        return -1;
    }

    private void aplicarFiltroCompleto() {
        if (rowSorter == null) {
            logImport("aplicarFiltroCompleto: rowSorter es null, no se aplican filtros");
            return;
        }

        List<RowFilter<DefaultTableModel, Integer>> filtros = new ArrayList<>();

        boolean mostrarDatosEnCero = checkFacturas.isSelected();
        logImport("aplicarFiltroCompleto: checkbox 'Datos en 0'=" + mostrarDatosEnCero
                + " (si está desmarcado, se ocultan filas con Total<=0 o no numérico)");

        if (!checkFacturas.isSelected()) {
            int totalCol = encontrarColumnaTotal();
            if (totalCol >= 0) {
                logImport("aplicarFiltroCompleto: activando filtro Total>0, índice columna Total=" + totalCol
                        + " (encabezado='" + tableModel.getColumnName(totalCol) + "')");
                filtros.add(new RowFilter<DefaultTableModel, Integer>() {
                    @Override
                    public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                        Object valor = entry.getModel().getValueAt(entry.getIdentifier(), totalCol);
                        if (valor == null) {
                            return false;
                        }
                        try {
                            return Double.parseDouble(valor.toString()) > 0;
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    }
                });
            } else {
                logImport("aplicarFiltroCompleto: se pidió ocultar totales en 0 pero no se encontró columna 'Total'");
            }
        }

        for (FilaItemConfig config : configFiltrosFilas) {
            List<String> seleccionados = config.getValoresSeleccionados();
            if (seleccionados.isEmpty()) {
                logImport("aplicarFiltroCompleto: filtro filas '" + config.getColumnaFiltro()
                        + "' sin valores seleccionados (todos desmarcados) → ese filtro no aplica");
                continue;
            }

            Set<String> set = new HashSet<>(seleccionados);
            int col = encontrarColumnaPorEtiqueta(config.getColumnaFiltro());
            if (col < 0 && "MetodoPago".equals(config.getColumnaFiltro())) {
                col = encontrarColumnaPorEtiqueta("MetodoPago", "FormaPago");
            }
            if (col >= 0) {
                logImport("aplicarFiltroCompleto: filtro filas columna=" + config.getColumnaFiltro()
                        + " índice=" + col + " valores permitidos=" + seleccionados
                        + " (encabezado='" + tableModel.getColumnName(col) + "')");
                int colIdx = col;
                filtros.add(new RowFilter<DefaultTableModel, Integer>() {
                    @Override
                    public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                        Object valor = entry.getModel().getValueAt(entry.getIdentifier(), colIdx);
                        if (valor == null) {
                            return false;
                        }
                        return set.contains(valor.toString().trim());
                    }
                });
            } else {
                logImport("aplicarFiltroCompleto: no se encontró columna para filtro '" + config.getColumnaFiltro() + "'");
            }
        }

        if (filtros.isEmpty()) {
            rowSorter.setRowFilter(null);
            logImport("aplicarFiltroCompleto: ningún filtro activo (RowFilter=null)");
        } else {
            rowSorter.setRowFilter(RowFilter.andFilter(filtros));
            logImport("aplicarFiltroCompleto: aplicando AND de " + filtros.size() + " filtro(s)");
        }

        int modelo = tableModel.getRowCount();
        int vista = table.getRowCount();
        logImport("aplicarFiltroCompleto: filas modelo=" + modelo + ", filas visibles=" + vista);

        int colArchivo = -1;
        for (int c = 0; c < tableModel.getColumnCount(); c++) {
            if ("Archivo".equals(tableModel.getColumnName(c))) {
                colArchivo = c;
                break;
            }
        }
        int colTotal = encontrarColumnaTotal();
        for (int i = 0; i < modelo; i++) {
            String archivo = colArchivo >= 0 ? String.valueOf(tableModel.getValueAt(i, colArchivo)) : "?";
            String totalStr = colTotal >= 0 ? String.valueOf(tableModel.getValueAt(i, colTotal)) : "?";
            int viewIdx = table.convertRowIndexToView(i);
            boolean visible = viewIdx >= 0;
            logImport("  fila modelo " + i + ": Archivo=" + archivo + ", Total=" + totalStr
                    + ", visible=" + visible + (visible ? " (vista índice " + viewIdx + ")" : " OCULTA por filtro"));
        }

        ajustarAnchoColumnas();
    }

    private void exportarAExcel() {
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay datos para exportar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Integer> columnasSeleccionadas = new ArrayList<>();
        for (int i = 0; i < checkboxesColumnas.size(); i++) {
            if (checkboxesColumnas.get(i).isSelected()) {
                columnasSeleccionadas.add(i);
            }
        }

        if (columnasSeleccionadas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay columnas seleccionadas.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Exportar a Excel");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
        fileChooser.setSelectedFile(new File("datos_exportados.xlsx"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            if (!archivo.getName().toLowerCase().endsWith(".xlsx")) {
                archivo = new File(archivo.getAbsolutePath() + ".xlsx");
            }

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Datos");

                // Estilo para encabezados
                CellStyle headerStyle = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                headerStyle.setFont(font);
                headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                // Fila de encabezados
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < columnasSeleccionadas.size(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(todasLasColumnas.get(columnasSeleccionadas.get(i)));
                    cell.setCellStyle(headerStyle);
                }

                // Filas de datos
                for (int row = 0; row < tableModel.getRowCount(); row++) {
                    Row excelRow = sheet.createRow(row + 1);
                    for (int i = 0; i < columnasSeleccionadas.size(); i++) {
                        Object valor = tableModel.getValueAt(row, columnasSeleccionadas.get(i));
                        Cell cell = excelRow.createCell(i);
                        if (valor instanceof Number) {
                            cell.setCellValue(((Number) valor).doubleValue());
                        } else {
                            cell.setCellValue(valor != null ? valor.toString() : "");
                        }
                    }
                }

                // Autoajustar columnas
                for (int i = 0; i < columnasSeleccionadas.size(); i++) {
                    sheet.autoSizeColumn(i);
                }

                // Guardar archivo
                try (FileOutputStream fos = new FileOutputStream(archivo)) {
                    workbook.write(fos);
                }

                JOptionPane.showMessageDialog(this,
                        "Archivo exportado:\n" + archivo.getAbsolutePath()
                        + "\nColumnas: " + columnasSeleccionadas.size()
                        + "\nFilas: " + tableModel.getRowCount(),
                        "Exportación exitosa", JOptionPane.INFORMATION_MESSAGE);
                lblEstado.setText("Exportado: " + archivo.getName());

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al exportar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void seleccionarYExtraerFolder() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar carpeta con archivos XML");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            seleccionarYExtraerFolder(fileChooser.getSelectedFile());
        }
    }

    /** Igual que {@link #seleccionarYExtraerFolder()} pero sin volver a preguntar la carpeta. */
    private void seleccionarYExtraerFolder(File carpeta) {
        lblEstado.setText("Procesando carpeta...");

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            private Exception error = null;
            private Object[] resultado;

            @Override
            protected Void doInBackground() throws Exception {
                try {
                    resultado = leerXmlDesdeCarpeta(carpeta);
                } catch (Exception ex) {
                    error = ex;
                }
                return null;
            }

            @Override
            protected void done() {
                if (error != null) {
                    JOptionPane.showMessageDialog(VistaP.this,
                            "Error al procesar la carpeta: " + error.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    lblEstado.setText("Error al procesar la carpeta");
                    error.printStackTrace();
                } else {
                    procesarDatos(resultado);
                }
            }
        };

        worker.execute();
    }

    private void seleccionarYExtraerZip() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".zip");
            }

            @Override
            public String getDescription() {
                return "Archivos ZIP (*.zip)";
            }
        });

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File archivoZip = fileChooser.getSelectedFile();

            lblEstado.setText("Procesando...");

            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                private Exception error = null;
                private Object[] resultado;

                @Override
                protected Void doInBackground() throws Exception {
                    try {
                        resultado = leerXmlDesdeZip(archivoZip);
                    } catch (Exception ex) {
                        error = ex;
                    }
                    return null;
                }

                @Override
                protected void done() {
                    if (error != null) {
                        JOptionPane.showMessageDialog(VistaP.this,
                                "Error al procesar el archivo ZIP: " + error.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        lblEstado.setText("Error al procesar el archivo");
                        error.printStackTrace();
                    } else {
                        procesarDatos(resultado);
                    }
                }
            };

            worker.execute();
        }
    }

    private void procesarDatos(Object[] resultado) {
        @SuppressWarnings("unchecked")
        List<Map<String, String>> listaXmlData = (List<Map<String, String>>) resultado[0];
        @SuppressWarnings("unchecked")
        List<String> columnas = (List<String>) resultado[1];

        logImport("procesarDatos: listaXmlData.size()=" + listaXmlData.size()
                + ", columnas=" + columnas.size());

        poblarTabla(columnas, listaXmlData);
        logImport("poblarTabla: tableModel.getRowCount()=" + tableModel.getRowCount());

        rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);
        ajustarAnchoColumnas();
        crearCheckboxesColumnas();
        aplicarFiltroCompleto();

        int filasModelo = tableModel.getRowCount();
        int filasVista = table.getRowCount();
        logImport("tras aplicarFiltroCompleto: filas modelo=" + filasModelo
                + ", filas visibles (JTable)=" + filasVista
                + (filasModelo != filasVista ? " → ALGUNAS FILAS OCULTAS POR FILTRO (ver log aplicarFiltroCompleto)" : ""));

        lblEstado.setText(listaXmlData.size() + " archivo(s) XML procesado(s) - "
                + tableModel.getColumnCount() + " columna(s) detectada(s)");
    }

    private void logImport(String mensaje) {
        logger.log(Level.INFO, "[Import XML] {0}", mensaje);
    }

    /**
     * Lee y parsea los XMLs del ZIP en un hilo de fondo (sin tocar la UI).
     * Retorna [List<Map<String,String>>, List<String>] = [datos, columnas]
     */
    private Object[] leerXmlDesdeZip(File archivoZip) throws Exception {
        List<Map<String, String>> listaXmlData = new ArrayList<>();
        LinkedHashSet<String> todosLosCampos = new LinkedHashSet<>();

        logImport("leerXmlDesdeZip: archivo=" + archivoZip.getAbsolutePath());

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(archivoZip))) {
            ZipEntry entry;
            int indiceEntrada = 0;

            while ((entry = zis.getNextEntry()) != null) {
                indiceEntrada++;
                String nombreCompleto = entry.getName();
                boolean esXml = !entry.isDirectory() && nombreCompleto.toLowerCase().endsWith(".xml");

                if (!esXml) {
                    if (!entry.isDirectory()) {
                        logImport("entrada #" + indiceEntrada + " omitida (no es .xml): " + nombreCompleto);
                    }
                    zis.closeEntry();
                    continue;
                }

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;

                while ((len = zis.read(buffer)) > 0) {
                    baos.write(buffer, 0, len);
                }

                byte[] xmlBytes = baos.toByteArray();
                logImport("entrada #" + indiceEntrada + " XML: rutaZip=\"" + nombreCompleto
                        + "\", bytes=" + xmlBytes.length
                        + ", soloNombre=\"" + new File(nombreCompleto).getName() + "\"");

                Map<String, String> datosXml = extraerCamposXml(xmlBytes);

                if (datosXml.isEmpty()) {
                    logImport("  → NO agregado: extraerCamposXml devolvió mapa vacío (XML inválido o no CFDI parseable)");
                } else {
                    String nombreArchivo = new File(entry.getName()).getName();
                    datosXml.put("_NOMBRE_ARCHIVO", nombreArchivo);

                    listaXmlData.add(datosXml);
                    todosLosCampos.addAll(datosXml.keySet());
                    logImport("  → agregado fila índice " + (listaXmlData.size() - 1) + ", Archivo=\"" + nombreArchivo
                            + "\", campos extraídos=" + datosXml.size());
                }

                zis.closeEntry();
            }

            logImport("leerXmlDesdeZip: total filas en listaXmlData=" + listaXmlData.size());
        }

        // Preparar lista de columnas (solo datos, no toca UI)
        List<String> columnas = new ArrayList<>();

        if (todosLosCampos.contains("_NOMBRE_ARCHIVO")) {
            columnas.add("Archivo");
            todosLosCampos.remove("_NOMBRE_ARCHIVO");
        }

        columnas.addAll(todosLosCampos);

        return new Object[]{listaXmlData, columnas};
    }

    /**
     * Lee y parsea los XMLs de una carpeta (incluyendo subcarpetas). Retorna
     * [List<Map<String,String>>, List<String>] = [datos, columnas]
     */
    private Object[] leerXmlDesdeCarpeta(File carpeta) throws Exception {
        List<Map<String, String>> listaXmlData = new ArrayList<>();
        LinkedHashSet<String> todosLosCampos = new LinkedHashSet<>();

        // Buscar todos los archivos .xml recursivamente
        List<Path> archivosXml = Files.walk(carpeta.toPath())
                .filter(p -> !Files.isDirectory(p))
                .filter(p -> p.toString().toLowerCase().endsWith(".xml"))
                .toList();

        for (Path archivoXml : archivosXml) {
            byte[] xmlBytes = Files.readAllBytes(archivoXml);

            Map<String, String> datosXml = extraerCamposXml(xmlBytes);

            if (!datosXml.isEmpty()) {
                datosXml.put("_NOMBRE_ARCHIVO", archivoXml.getFileName().toString());

                listaXmlData.add(datosXml);
                todosLosCampos.addAll(datosXml.keySet());
            }
        }

        // Preparar lista de columnas
        List<String> columnas = new ArrayList<>();

        if (todosLosCampos.contains("_NOMBRE_ARCHIVO")) {
            columnas.add("Archivo");
            todosLosCampos.remove("_NOMBRE_ARCHIVO");
        }

        columnas.addAll(todosLosCampos);

        return new Object[]{listaXmlData, columnas};
    }

    /**
     * Llena la tabla con los datos. DEBE ejecutarse en el EDT.
     */
    private void poblarTabla(List<String> columnas, List<Map<String, String>> listaXmlData) {
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);

        for (String columna : columnas) {
            tableModel.addColumn(columna);
        }

        for (Map<String, String> xmlData : listaXmlData) {
            Object[] fila = new Object[columnas.size()];

            for (int i = 0; i < columnas.size(); i++) {
                String nombreColumna = columnas.get(i);
                String claveMapa = nombreColumna.equals("Archivo") ? "_NOMBRE_ARCHIVO" : nombreColumna;
                fila[i] = xmlData.getOrDefault(claveMapa, "");
            }

            tableModel.addRow(fila);
        }
    }

    private Map<String, String> extraerCamposXml(byte[] xmlBytes) {
        Map<String, String> campos = new LinkedHashMap<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xmlBytes));

            Element root = doc.getDocumentElement();

            // 1. Extraer Descuento del nodo raíz (cfdi:Comprobante)
            String descuento = root.getAttribute("Descuento");
            if (descuento != null && !descuento.isEmpty()) {
                try {
                    double valor = Double.parseDouble(descuento);
                    campos.put("Descuento", valor > 0 ? descuento : "");
                } catch (NumberFormatException e) {
                    campos.put("Descuento", descuento);
                }
            } else {
                campos.put("Descuento", "");
            }

            // 2. Extraer impuestos del nodo cfdi:Impuestos (columnas independientes)
            extraerImpuestosCFDI(root, campos);

            // 3. Extracción recursiva (excluyendo Impuestos a nivel comprobante para evitar duplicados)
            extraerElementosRecursivo(root, "", campos, true);

        } catch (Exception ex) {
            System.err.println("Error al parsear XML: " + ex.getMessage());
        }

        return campos;
    }

    /**
     * Extrae impuestos trasladados y retenidos del nodo cfdi:Impuestos en columnas
     * independientes (IVA Trasladado, IVA Retenido, ISR Retenido, IEPS Trasladado, etc.).
     */
    private void extraerImpuestosCFDI(Element root, Map<String, String> campos) {
        NodeList hijos = root.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Node n = hijos.item(i);
            if (n.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            String localName = n.getLocalName() != null ? n.getLocalName() : n.getNodeName();
            if (!"Impuestos".equals(localName)) {
                continue;
            }
            Element impuestos = (Element) n;

            // Retenciones: cada Retencion en su columna (IVA Retenido, ISR Retenido, etc.)
            NodeList retenciones = impuestos.getElementsByTagNameNS("*", "Retenciones");
            if (retenciones.getLength() > 0) {
                Element rets = (Element) retenciones.item(0);
                NodeList listaRet = rets.getElementsByTagNameNS("*", "Retencion");
                for (int j = 0; j < listaRet.getLength(); j++) {
                    Element ret = (Element) listaRet.item(j);
                    String codigo = ret.getAttribute("Impuesto");
                    String importe = ret.getAttribute("Importe");
                    String nombreCol = nombreColumnaImpuesto(codigo, true);
                    if (!nombreCol.isEmpty()) {
                        campos.put(nombreCol, importe != null ? importe : "");
                    }
                }
            }

            // Traslados: cada Traslado en su columna (IVA Trasladado, IEPS Trasladado, etc.)
            NodeList traslados = impuestos.getElementsByTagNameNS("*", "Traslados");
            if (traslados.getLength() > 0) {
                Element tras = (Element) traslados.item(0);
                NodeList listaTras = tras.getElementsByTagNameNS("*", "Traslado");
                for (int j = 0; j < listaTras.getLength(); j++) {
                    Element tr = (Element) listaTras.item(j);
                    String codigo = tr.getAttribute("Impuesto");
                    String importe = tr.getAttribute("Importe");
                    String nombreCol = nombreColumnaImpuesto(codigo, false);
                    if (!nombreCol.isEmpty()) {
                        campos.put(nombreCol, importe != null ? importe : "");
                    }
                }
            }
            break; // Solo el primer Impuestos a nivel comprobante
        }
    }

    private String nombreColumnaImpuesto(String codigo, boolean esRetencion) {
        if (codigo == null || codigo.isEmpty()) {
            return "";
        }
        String nombre = NOMBRES_IMPUESTO.getOrDefault(codigo, "Impuesto_" + codigo);
        return nombre + (esRetencion ? " Retenido" : " Trasladado");
    }

    private void extraerElementosRecursivo(Element elemento, String prefijo, Map<String, String> campos, boolean esRaiz) {
        if (elemento.hasAttributes()) {
            for (int i = 0; i < elemento.getAttributes().getLength(); i++) {
                Node atributo = elemento.getAttributes().item(i);
                String nombreCampo = prefijo.isEmpty()
                        ? "@" + atributo.getNodeName()
                        : prefijo + "@" + atributo.getNodeName();
                campos.put(nombreCampo, atributo.getNodeValue());
            }
        }

        NodeList hijos = elemento.getChildNodes();
        boolean tieneElementosHijos = false;

        for (int i = 0; i < hijos.getLength(); i++) {
            if (hijos.item(i).getNodeType() == Node.ELEMENT_NODE) {
                tieneElementosHijos = true;
                break;
            }
        }

        if (!tieneElementosHijos) {
            String texto = elemento.getTextContent().trim();
            if (!texto.isEmpty()) {
                String nombreCampo = prefijo.isEmpty()
                        ? elemento.getNodeName()
                        : prefijo + "." + elemento.getNodeName();
                campos.put(nombreCampo, texto);
            }
        } else {
            String nuevoPrefijo = prefijo.isEmpty()
                    ? elemento.getNodeName()
                    : prefijo + "." + elemento.getNodeName();

            for (int i = 0; i < hijos.getLength(); i++) {
                Node hijo = hijos.item(i);
                if (hijo.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element elemHijo = (Element) hijo;
                String localName = elemHijo.getLocalName() != null ? elemHijo.getLocalName() : elemHijo.getNodeName();
                // Omitir Impuestos a nivel comprobante (ya procesado en extraerImpuestosCFDI)
                if (esRaiz && "Impuestos".equals(localName)) {
                    continue;
                }
                extraerElementosRecursivo(elemHijo, nuevoPrefijo, campos, false);
            }
        }
    }

    private void ajustarAnchoColumnas() {
        for (int i = 0; i < table.getColumnCount(); i++) {
            int maxWidth = 80;

            int headerWidth = table.getTableHeader()
                    .getFontMetrics(table.getTableHeader().getFont())
                    .stringWidth(table.getColumnName(i)) + 20;

            maxWidth = Math.max(maxWidth, headerWidth);

            int filasARevisar = Math.min(10, table.getRowCount());
            for (int j = 0; j < filasARevisar; j++) {
                Object value = table.getValueAt(j, i);
                if (value != null) {
                    int cellWidth = table.getFontMetrics(table.getFont())
                            .stringWidth(value.toString()) + 20;
                    maxWidth = Math.max(maxWidth, cellWidth);
                }
            }

            maxWidth = Math.min(maxWidth, 350);

            table.getColumnModel().getColumn(i).setPreferredWidth(maxWidth);
        }
    }

    private void crearCheckboxesColumnas() {
        todasLasColumnas.clear();
        todasLasTableColumns.clear();
        checkboxesColumnas.clear();

        panelCheckboxesColumnas.removeAll();
        panelCheckboxesColumnas.add(panelAjustes);

        for (int i = 0; i < table.getColumnCount(); i++) {
            todasLasColumnas.add(table.getColumnName(i));
            todasLasTableColumns.add(table.getColumnModel().getColumn(i));
        }

        LinkedHashMap<String, List<Integer>> categorias = new LinkedHashMap<>();

        for (int i = 0; i < todasLasColumnas.size(); i++) {
            String columna = todasLasColumnas.get(i);
            String categoria = obtenerCategoria(columna);
            categorias.computeIfAbsent(categoria, k -> new ArrayList<>()).add(i);
        }

        panelCheckboxesColumnas.setLayout(new BoxLayout(panelCheckboxesColumnas, BoxLayout.Y_AXIS));
        panelCheckboxesColumnas.setBackground(java.awt.Color.WHITE);

        for (Map.Entry<String, List<Integer>> entry : categorias.entrySet()) {
            String categoria = entry.getKey();
            List<Integer> indices = entry.getValue();

            // Panel de categoría con borde y título
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

            // Encabezado de categoría
            JLabel lblCategoria = new JLabel(categoria.isEmpty() ? "(Raíz)" : categoria);
            lblCategoria.setFont(lblCategoria.getFont().deriveFont(java.awt.Font.BOLD, 11f));
            lblCategoria.setForeground(new java.awt.Color(60, 60, 120));
            lblCategoria.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            lblCategoria.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 3, 0));
            panelCategoria.add(lblCategoria);

            // Separador fino bajo el título
            JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
            sep.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 1));
            sep.setForeground(new java.awt.Color(180, 180, 200));
            panelCategoria.add(sep);
            panelCategoria.add(javax.swing.Box.createVerticalStrut(3));

            // Checkboxes de la categoría
            for (int idx : indices) {
                String nombreColumna = todasLasColumnas.get(idx);
                String etiqueta = obtenerEtiquetaCorta(nombreColumna);

                JCheckBox cb = new JCheckBox(etiqueta, esColumnaPorDefecto(nombreColumna));
                cb.setToolTipText(nombreColumna); // nombre completo en tooltip
                cb.setBackground(new java.awt.Color(240, 240, 245));
                cb.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
                cb.addActionListener(evt -> actualizarColumnasVisibles());

                // Guardar en la posición correcta del índice original
                while (checkboxesColumnas.size() <= idx) {
                    checkboxesColumnas.add(null);
                }
                checkboxesColumnas.set(idx, cb);

                panelCategoria.add(cb);
            }

            panelCategoria.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            panelCheckboxesColumnas.add(panelCategoria);
        }

        jScrollPaneColumnas.setViewportView(panelCheckboxesColumnas);
        jScrollPaneColumnas.revalidate();
        jScrollPaneColumnas.repaint();

        checkPorDefecto.setSelected(true);
        actualizarColumnasVisibles();
    }

    private void crearCheckboxesFilas() {
        panelCheckboxesFilas.removeAll();
        panelCheckboxesFilas.setLayout(new BoxLayout(panelCheckboxesFilas, BoxLayout.Y_AXIS));
        panelCheckboxesFilas.setBackground(java.awt.Color.WHITE);

        List<ItemValor> listaMetodoPago = new ArrayList<>();
        listaMetodoPago.add(new ItemValor("PUE"));
        listaMetodoPago.add(new ItemValor("PPD"));
        FilaItem filaValidacionMetodoPago = new FilaItem("Validacion de Metodos de Pago", "MetodoPago", listaMetodoPago);

        List<ItemValor> listaCFDI = new ArrayList<>();

        listaCFDI.add(new ItemValor("G01", "Adquisición de mercancías"));
        listaCFDI.add(new ItemValor("G02", "Devoluciones, descuentos o bonificaciones"));
        listaCFDI.add(new ItemValor("G03", "Gastos en general"));

        listaCFDI.add(new ItemValor("I01", "Construcciones"));
        listaCFDI.add(new ItemValor("I02", "Mobiliario y equipo de oficina por inversiones"));
        listaCFDI.add(new ItemValor("I03", "Equipo de transporte"));
        listaCFDI.add(new ItemValor("I04", "Equipo de computo y accesorios"));
        listaCFDI.add(new ItemValor("I05", "Dados, troqueles, moldes, matrices y herramental"));
        listaCFDI.add(new ItemValor("I06", "Comunicaciones telefónicas"));
        listaCFDI.add(new ItemValor("I07", "Comunicaciones satelitales"));
        listaCFDI.add(new ItemValor("I08", "Otra maquinaria y equipo"));

        listaCFDI.add(new ItemValor("D01", "Honorarios médicos, dentales y gastos hospitalarios"));
        listaCFDI.add(new ItemValor("D02", "Gastos médicos por incapacidad o discapacidad"));
        listaCFDI.add(new ItemValor("D03", "Gastos funerales"));
        listaCFDI.add(new ItemValor("D04", "Donativos"));
        listaCFDI.add(new ItemValor("D05", "Intereses reales pagados por créditos hipotecarios"));
        listaCFDI.add(new ItemValor("D06", "Aportaciones voluntarias al SAR"));
        listaCFDI.add(new ItemValor("D07", "Primas por seguros de gastos médicos"));
        listaCFDI.add(new ItemValor("D08", "Gastos de transportación escolar obligatoria"));
        listaCFDI.add(new ItemValor("D09", "Depósitos en cuentas para el ahorro"));
        listaCFDI.add(new ItemValor("D10", "Pagos por servicios educativos (colegiaturas)"));

        listaCFDI.add(new ItemValor("P01", "Por definir"));

        FilaItem filaCFDI = new FilaItem("Categorizacion por Uso de CFDI", "UsoCFDI", listaCFDI);

        List<FilaItem> items = new ArrayList<>();
        items.add(filaValidacionMetodoPago);
        items.add(filaCFDI);
        Filas filas = new Filas(items, this::actualizarFilasVisibles);

        panelCheckboxesFilas = filas.generar(panelCheckboxesFilas);
        configFiltrosFilas = filas.getConfigFiltros();
        jScrollPaneFilas.setViewportView(panelCheckboxesFilas);
        jScrollPaneFilas.revalidate();
        jScrollPaneFilas.repaint();
    }

    public void actualizarFilasVisibles() {
        aplicarFiltroCompleto();
    }

    /**
     * Extrae la categoría de una columna: todo excepto el último segmento.
     * Ejemplos: "Archivo" -> "" (raíz) "@Version" -> "" (raíz)
     * "Comprobante.Emisor" -> "Comprobante" "A.B.C@attr" -> "A > B > C" "A.B.C"
     * -> "A > B"
     */
    private String obtenerCategoria(String nombreColumna) {
        // Quitar el segmento de atributo (@...) si existe
        String rutaBase = nombreColumna.contains("@")
                ? nombreColumna.substring(0, nombreColumna.lastIndexOf('@'))
                : nombreColumna;

        // Limpiar punto final residual
        if (rutaBase.endsWith(".")) {
            rutaBase = rutaBase.substring(0, rutaBase.length() - 1);
        }

        // Sin ruta base = atributo de raíz o columna simple
        if (rutaBase.isEmpty()) {
            return "";
        }

        // Quitar último segmento para obtener solo la ruta padre
        int ultimoPunto = rutaBase.lastIndexOf('.');
        if (ultimoPunto < 0) {
            // Un solo segmento: si venía con @ es su propio padre, si no es raíz
            return nombreColumna.contains("@") ? limpiarNamespace(rutaBase) : "";
        }

        // Construir la ruta padre limpiando namespaces (xxx:Nombre -> Nombre)
        return Arrays.stream(rutaBase.substring(0, ultimoPunto).split("\\."))
                .map(this::limpiarNamespace)
                .collect(Collectors.joining(" > "));
    }

    private String limpiarNamespace(String segmento) {
        int colon = segmento.lastIndexOf(':');
        return colon >= 0 ? segmento.substring(colon + 1) : segmento;
    }

    /**
     * Devuelve solo el último segmento legible del nombre de columna. Ejemplos:
     * "Comprobante.Emisor@Rfc" -> "@Rfc" "Comprobante.Emisor" -> "Emisor"
     * "@Version" -> "@Version"
     */
    private String obtenerEtiquetaCorta(String nombreColumna) {
        if (nombreColumna.equals("Archivo")) {
            return "Archivo";
        }

        if (nombreColumna.contains("@")) {
            return nombreColumna.substring(nombreColumna.lastIndexOf('@') + 1);
        }

        int ultimoPunto = nombreColumna.lastIndexOf('.');
        return ultimoPunto >= 0 ? nombreColumna.substring(ultimoPunto + 1) : nombreColumna;
//        return nombreColumna;
    }

    private boolean esColumnaPorDefecto(String nombreColumna) {
        String etiqueta = obtenerEtiquetaCorta(nombreColumna).toLowerCase();
        return COLUMNAS_POR_DEFECTO.contains(etiqueta);
    }

    private void seleccionarPorDefecto() {
        for (int i = 0; i < checkboxesColumnas.size(); i++) {
            JCheckBox cb = checkboxesColumnas.get(i);
            if (cb != null) {
                cb.setSelected(esColumnaPorDefecto(todasLasColumnas.get(i)));
            }
        }
        actualizarColumnasVisibles();
    }

    private void seleccionarTodas() {
        for (JCheckBox cb : checkboxesColumnas) {
            if (cb != null) {
                cb.setSelected(true);
            }
        }
        actualizarColumnasVisibles();
    }

    private void actualizarColumnasVisibles() {
        // Remover todas las columnas de la vista
        while (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().removeColumn(table.getColumnModel().getColumn(0));
        }

        // Re-agregar solo las que están seleccionadas
        for (int i = 0; i < checkboxesColumnas.size(); i++) {
            if (checkboxesColumnas.get(i).isSelected()) {
                table.getColumnModel().addColumn(todasLasTableColumns.get(i));
            }
        }

        table.revalidate();
        table.repaint();
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VistaP().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCER;
    private javax.swing.JButton btnExportExcel;
    private javax.swing.JButton btnFolder;
    private javax.swing.JButton btnKEY;
    private javax.swing.JButton btnSearchSAT;
    private javax.swing.JButton btnZip;
    private com.toedter.calendar.JDateChooser jDateFinish;
    private com.toedter.calendar.JDateChooser jDateInit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneColumnas;
    private javax.swing.JScrollPane jScrollPaneFilas;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JPanel panelOpciones;
    private javax.swing.JTable table;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
