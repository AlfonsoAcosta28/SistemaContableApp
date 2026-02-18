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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
        jPanel6.setLayout(new java.awt.GridBagLayout());

        btnZip.setText("Importar Zip");
        btnZip.addActionListener(this::btnZipActionPerformed);
        jPanel6.add(btnZip, new java.awt.GridBagConstraints());

        btnFolder.setText("Importar Carpeta");
        btnFolder.addActionListener(this::btnFolderActionPerformed);
        jPanel6.add(btnFolder, new java.awt.GridBagConstraints());

        jButton3.setText("Exportar a Excel");
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel6.add(jButton3, new java.awt.GridBagConstraints());

        panelOpciones.add(jPanel6);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
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
        jPanel4.setLayout(new java.awt.GridLayout());

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
        jPanel5.setLayout(new java.awt.GridLayout());

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
    }//GEN-LAST:event_btnZipActionPerformed

    private void btnFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFolderActionPerformed
        seleccionarYExtraerFolder();
    }//GEN-LAST:event_btnFolderActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        exportarAExcel();
    }//GEN-LAST:event_jButton3ActionPerformed

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

    /**
     * Crea un JCheckBox por cada columna de la tabla dentro de jScrollPane2. Al
     * desmarcar un checkbox, la columna se oculta de la tabla.
     */
    private void crearCheckboxesColumnas() {
        // Guardar referencia a todas las columnas
        todasLasColumnas.clear();
        todasLasTableColumns.clear();
        checkboxesColumnas.clear();

        for (int i = 0; i < table.getColumnCount(); i++) {
            todasLasColumnas.add(table.getColumnName(i));
            todasLasTableColumns.add(table.getColumnModel().getColumn(i));
        }

        // Crear panel con BoxLayout vertical para los checkboxes
        JPanel panelCheckboxes = new JPanel();
        panelCheckboxes.setLayout(new BoxLayout(panelCheckboxes, BoxLayout.Y_AXIS));
        panelCheckboxes.setBackground(java.awt.Color.WHITE);

        for (int i = 0; i < todasLasColumnas.size(); i++) {
            JCheckBox cb = new JCheckBox(todasLasColumnas.get(i), true);
            cb.setBackground(java.awt.Color.WHITE);
            cb.addActionListener(evt -> actualizarColumnasVisibles());
            checkboxesColumnas.add(cb);
            panelCheckboxes.add(cb);
        }

        jScrollPane2.setViewportView(panelCheckboxes);
        jScrollPane2.revalidate();
        jScrollPane2.repaint();
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
