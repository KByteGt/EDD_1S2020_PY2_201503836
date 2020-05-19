/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt;

import java.awt.HeadlessException;
import static java.lang.Thread.sleep;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import static kbytegt.usac_lib.carnetLogin;

/**
 *
 * @author KByteGt
 */
class ModeloTablaLibros extends AbstractTableModel{

    private ListaLibros lista;
    
    public ModeloTablaLibros(ListaLibros lista){
        System.out.println(" >> Preparando tabla...");
        this.lista = lista;
    }
    @Override
    public int getRowCount() {
        if(this.lista != null)
            return lista.getContador();
        else
            return 0;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = null;
        if(this.lista != null){
            Libro lib =  this.lista.buscarIndex(rowIndex);
            switch(columnIndex){
                case 0:
                    obj = lib.getISBN();
                    break;
                case 1:
                    obj = lib.getTitulo();
                    break;
                case 2:
                    obj = lib.getAutor();
                    break;
                case 3:
                    obj = lib.getEditorial();
                    break;
                case 4:
                    obj = lib.getAño();
                    break;
                case 5:
                    obj = lib.getEdicion();
                    break;
                case 6:
                    obj = lib.getIdioma();
                    break;
                default:
                    obj = null;
                    break;
            }
        }

        return obj;
    }
    
    @Override
    public String getColumnName(int c){
        String nombre = "";
        switch(c){
            case 0:
                nombre = "ISBN";
                break;
            case 1:
                nombre = "Titulo";
                break;
            case 2:
                nombre = "Autor";
                break;
            case 3:
                nombre = "Editorial";
                break;
            case 4:
                nombre = "Año";
                break;
            case 5:
                nombre = "Edición";
                break;
            case 6:
                nombre = "Idioma";
                break;
            default:
                nombre = "";
                break;
        }
        return nombre;
    }
    
}
public final class UIlibrary extends javax.swing.JFrame {
    
    ListaCategorias lcategorias;
    DefaultMutableTreeNode categorias = new DefaultMutableTreeNode("Categorias");
    DefaultTreeModel modelo_tree = new DefaultTreeModel(categorias);
    TableModel modelo_tabla;
    
    String categoria_name = "";
    int categoria_user = 0;
    NodoCategoria categoria_selected = null;
    
    Calendar fecha = new GregorianCalendar();
    
    JFileChooser chooser;
    UIviewimg visor = new UIviewimg();
    Archivo archivo = new Archivo();
    
    String[] listaCategorias;
    
    /*
     *  Actualizar JTree
     */
    public void obtenerCarpeta(){
        chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar carpeta para el programa");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        chooser.showSaveDialog(null);
        chooser.setAcceptAllFileFilterUsed(false);
        
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            usac_lib.carpeta = chooser.getSelectedFile().getPath();
            System.out.println("Ruta de la carpeta: " + usac_lib.carpeta);
        }
    }
    /*
     *  Actualizar JTree
     */
    public void actualizarJTree(ListaCategorias lista){
        
        jTree1.setModel(modelo_tree);
        
        //Limpiar JTree
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)modelo_tree.getRoot();
        root.removeAllChildren();
        modelo_tree.reload(root);
        
        //Insertar en JTree
        DefaultMutableTreeNode cat;
        jTree1.expandRow(lista.getContador());
        System.out.println(" [JTre] contador: "+lista.getContador());
        if(lista != null){
            int n = lista.getContador();
            listaCategorias = new String[n];
            int i = 0;
            
            NodoLC temp = lista.getInicio();
            listaCategorias[i] = temp.getCategoria();
            i++;
        
            while(temp.getSiguiente() != null){
                //Agregar categoria a jTree
                System.out.println(" -> "+temp.getCategoria());
                cat = new DefaultMutableTreeNode(temp.getCategoria());
                modelo_tree.insertNodeInto(cat, categorias, 0);
                temp = temp.getSiguiente();
                
                if(i < n){
                    listaCategorias[i] = temp.getCategoria();
                    i++;
                }
            }
            System.out.println(" -> "+temp.getCategoria());
            cat = new DefaultMutableTreeNode(temp.getCategoria());
            modelo_tree.insertNodeInto(cat, categorias, 0);
            modelo_tree.reload();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo obtener las categorias del Árbol AVL");
        }
    }
    
    /*
     *  Actualizar JTable
     */
    public void actualizarJTable(ListaLibros lista){
        
        modelo_tabla = new ModeloTablaLibros(lista);
        jTable1.setModel(modelo_tabla);
        //modelo_tabla.setValueAt("Hola", 1, 1);
    }
    
    /*
     *  Actualizar JTable
     */
    public void setMenuCarnet(String carnet){
        jMenu3.setText(carnet);
    }
    /**
     * Creates new form UIlibrary
     */
    public UIlibrary() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        in_serch = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        label_categoria = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca virtual");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Buscar libro:");

        in_serch.setText("ISBN / Titulo");

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(in_serch, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(in_serch))
                    .addComponent(jButton2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton1.setText("Cargar archivo JSON - libros");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Agregar libro");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton1)
                .addComponent(jButton3))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Biblioteca"));

        jSplitPane1.setDividerLocation(115);
        jSplitPane1.setDividerSize(8);

        label_categoria.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        label_categoria.setText("Categoria");

        jButton4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(204, 0, 0));
        jButton4.setText("Eliminar categoria");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_categoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_categoria)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ISBN", "Titulo", "Autor", "Editorial", "Año", "Edición", "Idioma"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel4);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Biblioteca");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(jPanel7);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jMenu3.setText("Usuario");

        jMenuItem1.setText("Editar usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);
        jMenu3.add(jSeparator1);

        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Reportes");

        jMenuItem3.setText("Árbol AVL de categorias");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Árbol B por Categoria");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Tabla de dispersión de usuarios");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("AVL - preorden");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("AVL - inorden");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("AVL - postorden");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);
        jMenu2.add(jSeparator2);

        jMenuItem9.setText("Listado de Nodos en la red");
        jMenu2.add(jMenuItem9);

        jMenuItem10.setText("Log de operaciones");
        jMenu2.add(jMenuItem10);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Configuración");

        jMenuItem11.setText("Definir carpeta");
        jMenu1.add(jMenuItem11);

        jMenuItem12.setText("IP y puerto");
        jMenu1.add(jMenuItem12);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // BOTON BUSCAR LIBRO POR ISBN
        String valorBuscar = in_serch.getText();
        System.out.println(" Buscar: "+valorBuscar);
        ListaLibros lista = new ListaLibros();
        usac_lib.biblioteca.buscarNombreLibro(lista, valorBuscar);
        
        if(lista.getInicio() != null){
            System.out.println(" Contador de la lista: "+lista.getContador());
            this.actualizarJTable(lista);
        } else {
            System.out.println("Problemas");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // BOTON CARGAR JSON DE LIBROS
        
        // LEER ARCHIVO JSON EN RUTA
        Archivo a = new Archivo();
        String txt;
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JavaScript Object Notation", "json");
        jFileChooser1.setFileFilter(filter);
        int returnVal = jFileChooser1.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println(jFileChooser1.getSelectedFile().getAbsolutePath());
            txt = a.getJson(jFileChooser1.getSelectedFile().getAbsolutePath());
           
            boolean flag = usac_lib.ingresarLibros(txt);
             if(flag){
                 
                JOptionPane.showMessageDialog(this,"Carga de libros exitosa","JSON", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,"No se pudieron cargar los libros","JSON", JOptionPane.ERROR_MESSAGE);
            }
            //System.out.println(txt);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // BOTON AGREGAR LIBRO
        UIlibroregistro registro = new UIlibroregistro();
        registro.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // METODO DE SALIR
        usac_lib.LogOut();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // OBTENER LIBRO DE TABLA
//        DefaultTableModel moelo = (DefaultTableModel) jTable1.getModel();
//        if(evt.getClickCount() == 1){
//            for (int i = 0; i < jTable1.getRowCount(); i++) {
//                
//            }
//        }
        //Obtener ISBN de la tabla al hacer doble click
        if(evt.getClickCount() == 2){
            BigInteger temp_isbn = BigInteger.ZERO;
            int fila = jTable1.rowAtPoint(evt.getPoint());
            if(fila > -1){
                System.out.println(" -> Creando instancia de UIlibroview");
                UIlibroview view = new UIlibroview();
                try {
                    String temp = (String) jTable1.getValueAt(fila, 0).toString();
                    temp_isbn = new BigInteger(temp);
                    view.setInformacion(temp_isbn, categoria_name);
                    view.setVisible(true);
//                    JOptionPane.showMessageDialog(this, String.valueOf(temp_isbn));
                } catch (HeadlessException e) {
                    JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE );
                    System.out.println(" **Error: "+e.getMessage());
                }

            }
        }
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
         // METODO QUE OBTIENE LA CATEGORIA A MOSTRAR EN LA TABLA
        DefaultMutableTreeNode categoria = (DefaultMutableTreeNode)jTree1.getLastSelectedPathComponent();
        try {
            categoria_name = categoria.getPath()[1].toString();
            categoria_selected = usac_lib.biblioteca.buscar(categoria_name);
            categoria_user = categoria_selected.getUsuario();
            ListaLibros listaLib = categoria_selected.getListaLibros();
            actualizarJTable(listaLib);
            this.label_categoria.setText(categoria_name);
            //JOptionPane.showMessageDialog(this, categoria.getPath()[1].toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTree1ValueChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // ELIMINAR CATEGORIA
        if(categoria_user == usac_lib.carnetLogin){
            //Eliminar la categoria
            usac_lib.biblioteca.eliminar(categoria_name, usac_lib.carnetLogin);
            actualizarJTree(usac_lib.biblioteca.getListaCategorias());
            JOptionPane.showMessageDialog(this,"Categoria "+categoria_name+" eliminada con éxito.","Categoria",JOptionPane.INFORMATION_MESSAGE );
        } else {
            JOptionPane.showMessageDialog(this,"No tienes permisos para eliminar la categoria "+categoria_user+".","Categoria",JOptionPane.ERROR_MESSAGE );
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // EDITAR USUARIO
        UIusuarioview view = new UIusuarioview();
        view.llenarCampos();
        view.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // Reporte AVL 
        // MOSTRAR NOMBRE DE LA CATEGORIA Y EL TOTAL DE LIBROS
        
        if(usac_lib.carpeta.length() < 2){
            //Pedir ruta de la carpeta
            obtenerCarpeta();
        }
        
        String dot = usac_lib.biblioteca.getGraphvizArbol("Árbol AVL");
        String nombreDot = "reporte1_AVL";
//        String nombreImg = "reporte_"+
//                fecha.get(Calendar.YEAR)+
//                fecha.get(Calendar.MONTH)+
//                fecha.get(Calendar.DAY_OF_MONTH)+
//                fecha.get(Calendar.HOUR_OF_DAY)+
//                fecha.get(Calendar.MINUTE)+
//                fecha.get(Calendar.SECOND);
        String nombreImg = "reporte1";
        boolean flag = archivo.crearDot(dot, nombreDot);
        if(flag){
            flag = archivo.crearImagen(nombreImg,nombreDot);
            if(flag){
                //visor = new UIviewimg();
                
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UIlibrary.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                visor.setTitle("Reporte 1");
                visor.mostrarIMG(usac_lib.carpeta+"\\"+nombreImg+".jpg", "Ábol AVL de categorias");
                visor.setVisible(true);
            }
        } else{
            System.out.println("No se pugo gener el del de: "+nombreImg);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // Reporte TABLA DE DISPERCIÓN
        // MOSTRAR NOMBRE DE LA CATEGORIA Y EL TOTAL DE LIBROS
        
        if(usac_lib.carpeta.length() < 2){
            //Pedir ruta de la carpeta
            obtenerCarpeta();
        }
        
        String dot = usac_lib.usuarios.getGraphviz(".: Usuarios Registrados :.");
        String nombreDot = "reporte1_tabla_dispercion";
//        String nombreImg = "reporte_"+
//                fecha.get(Calendar.YEAR)+
//                fecha.get(Calendar.MONTH)+
//                fecha.get(Calendar.DAY_OF_MONTH)+
//                fecha.get(Calendar.HOUR_OF_DAY)+
//                fecha.get(Calendar.MINUTE)+
//                fecha.get(Calendar.SECOND);
        String nombreImg = "reporte3";
        boolean flag = archivo.crearDot(dot, nombreDot);
        if(flag){
            flag = archivo.crearImagen(nombreImg,nombreDot);
            if(flag){
                //visor = new UIviewimg();
                
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UIlibrary.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                visor.setTitle("Reportes");
                visor.mostrarIMG(usac_lib.carpeta+"\\"+nombreImg+".jpg", "Tabla de disperción");
                visor.setVisible(true);
            }
        } else{
            System.out.println("No se pugo gener el del de: "+nombreImg);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // Reporte RECORRIDO PREORDEN AVL
        // MOSTRAR NOMBRE DE LA CATEGORIA Y EL TOTAL DE LIBROS
        
        if(usac_lib.carpeta.length() < 2){
            //Pedir ruta de la carpeta
            obtenerCarpeta();
        }
        
        String dot = usac_lib.biblioteca.getGraphvizOrdenado("PreOrden");
        System.out.println("--- PreOrden");
        System.out.println(dot);
        String nombreDot = "reporte4_AVL_preorden";
        String nombreImg = "reporte4";
        boolean flag = archivo.crearDot(dot, nombreDot);
        if(flag){
            flag = archivo.crearImagen(nombreImg,nombreDot);
            if(flag){
                //visor = new UIviewimg();
                
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UIlibrary.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                visor.setTitle("Reportes");
                visor.mostrarIMG(usac_lib.carpeta+"\\"+nombreImg+".jpg", "AVL categorias - PreOrden");
                visor.setVisible(true);
            }
        } else{
            System.out.println("No se pugo gener el del de: "+nombreImg);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // REPORTE DE LIBROS POR CATEGORIA
        String tempCategoria = (String) JOptionPane.showInputDialog(this,"Categoria:","Arbol B",JOptionPane.QUESTION_MESSAGE,null,listaCategorias,listaCategorias[0]);
        
        if(tempCategoria != null){
            if(usac_lib.carpeta.length() < 2){
                //Pedir ruta de la carpeta
                obtenerCarpeta();
            }

            String dot = usac_lib.biblioteca.getGraphvizArbolB(tempCategoria);
            String nombreDot = "reporte2_B_"+tempCategoria;
            String nombreImg = "reporte2_"+tempCategoria;
            boolean flag = archivo.crearDot(dot, nombreDot);
            if(flag){
                flag = archivo.crearImagen(nombreImg,nombreDot);
                if(flag){
                    //visor = new UIviewimg();

                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UIlibrary.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    visor.setTitle("Reportes");
                    visor.mostrarIMG(usac_lib.carpeta+"\\"+nombreImg+".jpg", "Árbol B - "+tempCategoria);
                    visor.setVisible(true);
                }
            } else{
                System.out.println("No se pugo generar la imagen de: "+nombreImg);
            }
        }
         
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // Reporte RECORRIDO INORDEN AVL
        // MOSTRAR NOMBRE DE LA CATEGORIA Y EL TOTAL DE LIBROS
        
        if(usac_lib.carpeta.length() < 2){
            //Pedir ruta de la carpeta
            obtenerCarpeta();
        }
        
        String dot = usac_lib.biblioteca.getGraphvizOrdenado("InOrden");
        String nombreDot = "reporte5_AVL_inorden";
        String nombreImg = "reporte5";
        boolean flag = archivo.crearDot(dot, nombreDot);
        if(flag){
            flag = archivo.crearImagen(nombreImg,nombreDot);
            if(flag){
                //visor = new UIviewimg();
                
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UIlibrary.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                visor.setTitle("Reportes");
                visor.mostrarIMG(usac_lib.carpeta+"\\"+nombreImg+".jpg", "AVL categorias - InOrden");
                visor.setVisible(true);
            }
        } else{
            System.out.println("No se pugo gener el del de: "+nombreImg);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // Reporte RECORRIDO POSTORDEN AVL
        // MOSTRAR NOMBRE DE LA CATEGORIA Y EL TOTAL DE LIBROS
        
        if(usac_lib.carpeta.length() < 2){
            //Pedir ruta de la carpeta
            obtenerCarpeta();
        }
        
        String dot = usac_lib.biblioteca.getGraphvizOrdenado("PostOrden");
        String nombreDot = "reporte6_AVL_postorden";
        String nombreImg = "reporte6";
        boolean flag = archivo.crearDot(dot, nombreDot);
        if(flag){
            flag = archivo.crearImagen(nombreImg,nombreDot);
            if(flag){
                //visor = new UIviewimg();
                
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UIlibrary.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                visor.setTitle("Reportes");
                visor.mostrarIMG(usac_lib.carpeta+"\\"+nombreImg+".jpg", "AVL categorias - PostOrden");
                visor.setVisible(true);
            }
        } else{
            System.out.println("No se pugo gener el del de: "+nombreImg);
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    /**
     * @param args the command line arguments
     */
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIlibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIlibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIlibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIlibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIlibrary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField in_serch;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel label_categoria;
    // End of variables declaration//GEN-END:variables
}
