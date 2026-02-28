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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import java.util.Set;
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
            "total", "importe", "descuento", "formadepagop"
    );

    private JPanel panelCheckboxes = new JPanel();
    private JPanel panelAjustes;
    private JCheckBox checkFacturas;
    private ButtonGroup ajustesGrupo = new ButtonGroup();

    private JRadioButton checkSeleccionarTodas;
    private JRadioButton checkPorDefecto;



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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        panelOpciones = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnZip = new javax.swing.JButton();
        btnFolder = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
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
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 50, 0));

        btnZip.setText("Importar Zip");
        btnZip.addActionListener(this::btnZipActionPerformed);
        jPanel6.add(btnZip);

        btnFolder.setText("Importar Carpeta");
        btnFolder.addActionListener(this::btnFolderActionPerformed);
        jPanel6.add(btnFolder);

        jButton3.setText("Exportar a Excel");
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel6.add(jButton3);

        panelOpciones.add(jPanel6);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 50, 0));

        lblEstado.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jPanel3.add(lblEstado);

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

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Columnas"));
        jPanel5.add(jScrollPane2);

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
        checkFacturas.setSelected(true);
        checkFacturas.setVisible(true);
    }//GEN-LAST:event_btnZipActionPerformed

    private void btnFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFolderActionPerformed
        seleccionarYExtraerFolder();
        checkFacturas.setSelected(true);
        checkFacturas.setVisible(true);
    }//GEN-LAST:event_btnFolderActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        exportarAExcel();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void checkPorDefectoItemStateChanged(java.awt.event.MouseEvent evt) {
        seleccionarPorDefecto();
    }

    private void checkSeleccionarTodasItemStateChanged(java.awt.event.MouseEvent evt) {
        seleccionarTodas();
    }
    private void checkFacturasItemStateChanged(java.awt.event.ItemEvent evt) {
        if (rowSorter == null) return;

        if (checkFacturas.isSelected()) {
            rowSorter.setRowFilter(null);
        } else {
            int totalCol = encontrarColumnaTotal();
            if (totalCol >= 0) {
                rowSorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
                    @Override
                    public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                        Object valor = entry.getModel().getValueAt(entry.getIdentifier(), totalCol);
                        if (valor == null) return false;
                        try {
                            return Double.parseDouble(valor.toString()) > 0;
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    }
                });
            }
        }
        ajustarAnchoColumnas();
    }

    private void botonesPorDefecto() {
        panelCheckboxes.setLayout(new BoxLayout(panelCheckboxes, BoxLayout.Y_AXIS));
        panelCheckboxes.setBackground(java.awt.Color.WHITE);
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
        panelCheckboxes.add(panelAjustes);
    }

    private int encontrarColumnaTotal() {
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            if (obtenerEtiquetaCorta(tableModel.getColumnName(i)).equalsIgnoreCase("total")) {
                return i;
            }
        }
        return -1;
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
            File carpeta = fileChooser.getSelectedFile();

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

        poblarTabla(columnas, listaXmlData);
        rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);
        ajustarAnchoColumnas();
        crearCheckboxesColumnas();
        lblEstado.setText(listaXmlData.size() + " archivo(s) XML procesado(s) - "
                + tableModel.getColumnCount() + " columna(s) detectada(s)");
    }

    /**
     * Lee y parsea los XMLs del ZIP en un hilo de fondo (sin tocar la UI).
     * Retorna [List<Map<String,String>>, List<String>] = [datos, columnas]
     */
    private Object[] leerXmlDesdeZip(File archivoZip) throws Exception {
        List<Map<String, String>> listaXmlData = new ArrayList<>();
        LinkedHashSet<String> todosLosCampos = new LinkedHashSet<>();

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(archivoZip))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().toLowerCase().endsWith(".xml")) {

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;

                    while ((len = zis.read(buffer)) > 0) {
                        baos.write(buffer, 0, len);
                    }

                    byte[] xmlBytes = baos.toByteArray();

                    Map<String, String> datosXml = extraerCamposXml(xmlBytes);

                    if (!datosXml.isEmpty()) {
                        String nombreArchivo = new File(entry.getName()).getName();
                        datosXml.put("_NOMBRE_ARCHIVO", nombreArchivo);

                        listaXmlData.add(datosXml);
                        todosLosCampos.addAll(datosXml.keySet());
                    }
                }

                zis.closeEntry();
            }
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
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xmlBytes));

            Element root = doc.getDocumentElement();

            extraerElementosRecursivo(root, "", campos);

        } catch (Exception ex) {
            System.err.println("Error al parsear XML: " + ex.getMessage());
        }

        return campos;
    }

    private void extraerElementosRecursivo(Element elemento, String prefijo, Map<String, String> campos) {
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
                if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                    extraerElementosRecursivo((Element) hijo, nuevoPrefijo, campos);
                }
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

        for (int i = 0; i < table.getColumnCount(); i++) {
            todasLasColumnas.add(table.getColumnName(i));
            todasLasTableColumns.add(table.getColumnModel().getColumn(i));
        }

        // Agrupar columnas por categoría (ruta padre)
        LinkedHashMap<String, List<Integer>> categorias = new LinkedHashMap<>();

        for (int i = 0; i < todasLasColumnas.size(); i++) {
            String columna = todasLasColumnas.get(i);
            String categoria = obtenerCategoria(columna);
            categorias.computeIfAbsent(categoria, k -> new ArrayList<>()).add(i);
        }

        // Panel principal con BoxLayout vertical
        panelCheckboxes.setLayout(new BoxLayout(panelCheckboxes, BoxLayout.Y_AXIS));
        panelCheckboxes.setBackground(java.awt.Color.WHITE);

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
            panelCheckboxes.add(panelCategoria);
        }

        jScrollPane2.setViewportView(panelCheckboxes);
        jScrollPane2.revalidate();
        jScrollPane2.repaint();

        checkPorDefecto.setSelected(true);
        actualizarColumnasVisibles();
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
    private javax.swing.JButton btnFolder;
    private javax.swing.JButton btnZip;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JPanel panelOpciones;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
