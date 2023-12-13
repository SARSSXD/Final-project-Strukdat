/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tiket_bed;

import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import raven.cell.TabelBuktiCellRender;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;

/**
 *
 * @author Kautsar Quraisy <220605110162@student.uin-malang.ac.id>
 */
public final class DashboardAdmin extends javax.swing.JFrame {

    /**
     * Creates new form DashboardAdmin
     */
    public DashboardAdmin() {
        initComponents();
        tampil_database();
        event();
    }

    public void event() {
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row, JTable table) {
                if (table == usertable) {
                    // Proses edit untuk tabel user (usertable)
                    Cregister reg = new Cregister();
                    reg.setId_user(Integer.parseInt(usertable.getValueAt(row, 0).toString()));
                    reg.setNama(usertable.getValueAt(row, 1).toString());
                    reg.setEmail(usertable.getValueAt(row, 2).toString());
                    reg.setUsername(usertable.getValueAt(row, 3).toString());
                    reg.setPassword(usertable.getValueAt(row, 4).toString());
                    reg.ubahData(reg.getId_user(), reg.getNama(), reg.getEmail(),
                            reg.getUsername(), reg.getPassword());
                } else if (table == Ordertable) {
                    // Proses edit untuk tabel order (Ordertable)
                    Corder o = new Corder();
                    o.setId_order(Integer.parseInt(Ordertable.getValueAt(row, 0).toString()));
                    o.setStatus(Ordertable.getValueAt(row, 7).toString());
                    o.ubahData(o.getId_order(), o.getStatus());
                }
            }

            @Override
            public void onDelete(int row, JTable table) {
                if (Ordertable.isEditing()) {
                    Ordertable.getCellEditor().stopCellEditing();
                }
                if (usertable.isEditing()) {
                    usertable.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) Ordertable.getModel();
                DefaultTableModel model1 = (DefaultTableModel) usertable.getModel();
                model.removeRow(row);
                model1.removeRow(row);
            }

            @Override
            public void onView(int row, JTable table) {
                if (table == Ordertable) {
                    Corder o = new Corder();
                    o.setId_order(Integer.parseInt(Ordertable.getValueAt(row, 0).toString()));
                    ResultSet hasil = o.CariData(o.getId_order());
                    try {
                        if (hasil.next()) {
                            String path = hasil.getString("buktibyr");
                            ImageIcon imageIcon = new ImageIcon(new ImageIcon(path).getImage().
                                    getScaledInstance(800, 600, Image.SCALE_DEFAULT));

                            // Tampilkan gambar dalam dialog atau panel khusus
                            javax.swing.JOptionPane.showMessageDialog(null, imageIcon,
                                    "Bukti Pembayaran", JOptionPane.PLAIN_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(DashboardAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (table == usertable) {
                    // Get the user details from the selected row
                    String idUser = usertable.getValueAt(row, 0).toString();

                    // Create a dialog to display user details in a table
                    JDialog userDialog = new JDialog();
                    userDialog.setTitle("User Details");
                    userDialog.setSize(800, 100);
                    userDialog.setLocationRelativeTo(null);

                    // Define the columns for the user details table
                    String[] columns = {"ID", "NIK", "No Tlp", "No Rekening", "Tanggal Lahir", "Jenis Kelamin", "Alamat"};

                    // Create a table model with no data initially
                    DefaultTableModel userModel = new DefaultTableModel(null, columns);

                    // Populate the table model with data based on the selected user
                    try {
                        Cbio register = new Cbio();
                        ResultSet userResult = register.CariData(Integer.parseInt(idUser));

                        if (userResult.next()) {
                            // Add the user details to the table model
                            String[] rowData = {
                                userResult.getString("id_user"),
                                userResult.getString("nik"),
                                userResult.getString("no_tlp"),
                                userResult.getString("norek"),
                                userResult.getString("tanggal_lahir"),
                                userResult.getString("jk"),
                                userResult.getString("alamat")
                            };
                            userModel.addRow(rowData);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(DashboardAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Create a JTable with the populated table model
                    JTable userDetailsTable = new JTable(userModel);

                    // Add the table to a JScrollPane for scrollability
                    JScrollPane scrollPane = new JScrollPane(userDetailsTable);

                    // Add the JScrollPane to the dialog
                    userDialog.add(scrollPane);

                    // Set the dialog to be visible
                    userDialog.setVisible(true);
                }
            }
        };
        Ordertable.getColumnModel().getColumn(6).setCellRenderer(new TabelBuktiCellRender());
        Ordertable.getColumnModel().getColumn(9).setCellRenderer(new TableActionCellRender());
        Ordertable.getColumnModel().getColumn(9).setCellEditor(new TableActionCellEditor(event));
        usertable.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        usertable.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
        usertable.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usertableHeaderMouseClicked(evt);
            }
        });
        Ordertable.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordertableHeaderMouseClicked(evt);
            }
        });
    }

    private void ordertableHeaderMouseClicked(java.awt.event.MouseEvent evt) {
        int columnIndex1 = Ordertable.columnAtPoint(evt.getPoint());
        if (columnIndex1 == getColumnIndexByName1("ID Order")
                || columnIndex1 == getColumnIndexByName1("ID User")
                || columnIndex1 == getColumnIndexByName1("Jumlah Pengunjung")
                || columnIndex1 == getColumnIndexByName1("Tanggal")
                || columnIndex1 == getColumnIndexByName1("Paket")
                || columnIndex1 == getColumnIndexByName1("Metode Pembayaran")
                || columnIndex1 == getColumnIndexByName1("Status")) {
            // Sort the Ordertable data by the clicked column using bubble sort from Corder class
            Cregister.bubbleSort(Ordertable, columnIndex1);
        }
    }

    private void usertableHeaderMouseClicked(java.awt.event.MouseEvent evt) {
        int columnIndex = usertable.columnAtPoint(evt.getPoint());
        if (columnIndex == getColumnIndexByName("Nama")
                || columnIndex == getColumnIndexByName("Email")
                || columnIndex == getColumnIndexByName("Password")
                || columnIndex == getColumnIndexByName("Username")
                || columnIndex == getColumnIndexByName("ID User")) {

            // Sort the table data by the clicked column using bubble sort from Cregister class
            Cregister.bubbleSort(usertable, columnIndex);
        }
    }

    // Helper method to get the column index by column name
    private int getColumnIndexByName(String columnName) {
        for (int i = 0; i < usertable.getColumnCount(); i++) {
            if (usertable.getColumnName(i).equals(columnName)) {
                return i;
            }
        }
        return -1; // Column not found
    }

    private int getColumnIndexByName1(String columnName) {
        for (int i = 0; i < Ordertable.getColumnCount(); i++) {
            if (Ordertable.getColumnName(i).equals(columnName)) {
                return i;
            }
        }
        return -1; // Column not found
    }

    Corder aa = new Corder();
    Cregister bb = new Cregister();

    public void tampil_database() {
        //tabel user
        Object[] baris1 = {"ID User", "Nama", "Email", "Username", "Password", "Action"};
        DefaultTableModel tabmode1 = new DefaultTableModel(null, baris1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the action column editable, and others non-editable
                return column >= 1; // Assuming "Action" column is at index 9
            }
        };
        usertable.setModel(tabmode1);

        //tabel order
        Object[] baris = {"ID Order", "ID User", "Jumlah Pengunjung", "Tanggal", "Paket", "Metode Pembayaran", "Bukti", "Status", "Total", "Action"};
        DefaultTableModel tabmode = new DefaultTableModel(null, baris) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the action column editable, and others non-editable
                return column == 7 || column == 9; // Assuming "Action" column is at index 9
            }
        };
        Ordertable.setModel(tabmode);
        try {
            ResultSet hasil = aa.tampilData();
            ResultSet hasil1 = bb.tampilData();
            while (hasil.next()) {
                // Get the file path of the image
                String imagePath = hasil.getString("buktibyr");
                // Load the image and create an ImageIcon
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().
                        getScaledInstance(50, 50, Image.SCALE_DEFAULT));
                // Set the ImageIcon as the value for the "Bukti" column
                //id_nilai, NISN, id_matpel, id_ustad, UAS, UTS
                String idOrder = hasil.getString("id_order");
                String idUser = hasil.getString("id_user");
                String jmlP = hasil.getString("jml_pengunjung");
                String tgl = hasil.getString("tanggal");
                String paket = hasil.getString("paket");
                String metod = hasil.getString("metodbyr");
                String status = hasil.getString("status");
                String total = hasil.getString("total");
                // ... (your existing code)

                String act = "";
                // Set the ImageIcon as the value for the "Bukti" column (at index 6)
                String[] data = {idOrder, idUser, jmlP, tgl, paket, metod, imagePath, status, total, act};
                tabmode.addRow(data);
                int rowIndex = tabmode.getRowCount() - 1;
                Ordertable.setValueAt(imageIcon, rowIndex, 6); // Assuming "Bukti" is at index 6
            }
            while (hasil1.next()) {
                String idUser = hasil1.getString("id_user");
                String nama = hasil1.getString("nama");
                String email = hasil1.getString("email");
                String username = hasil1.getString("username");
                String password = hasil1.getString("password");
                String[] data = {idUser, nama, email, username, password};
                tabmode1.addRow(data);
            }
        } catch (SQLException e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Ordertable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        usertable = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ordertable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Ordertable.setMinimumSize(new java.awt.Dimension(60, 70));
        Ordertable.setRowHeight(30);
        jScrollPane1.setViewportView(Ordertable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 287, 976, 280));

        usertable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        usertable.setRowHeight(30);
        jScrollPane2.setViewportView(usertable);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 45, 976, 199));

        jTextField1.setText("...................Isi ID User Yang Ingin Dicari.....................");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 17, 250, 30));

        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 15, -1, -1));

        jButton2.setText("Cari");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 260, -1, -1));

        jTextField2.setText("...................Isi ID Order Yang Ingin Dicari.................");
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 260, 250, 30));

        jButton3.setText("Log Out");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 570, -1, -1));

        jButton4.setText("Antrian");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 570, -1, -1));

        jLabel1.setFont(new java.awt.Font("Elephant", 0, 18)); // NOI18N
        jLabel1.setText("DASHBOARD ADMIN");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 250, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
        login l = new login();
        l.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        jTextField2.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2MouseClicked

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        jTextField1.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        //tabel order
        Object[] baris = {"ID Order", "ID User", "Jumlah Pengunjung", "Tanggal", "Paket", "Metode Pembayaran", "Bukti", "Status", "Total", "Action"};
        DefaultTableModel tabmode = new DefaultTableModel(null, baris) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the action column editable, and others non-editable
                return column == 7 || column == 9; // Assuming "Action" column is at index 9
            }
        };
        Ordertable.setModel(tabmode);
        try {
            ResultSet hasil;
            if (!"".equals(jTextField2.getText())) {
                hasil = aa.CariData(Integer.parseInt(jTextField2.getText()));
            } else {
                hasil = aa.tampilData();
            }

            while (hasil.next()) {
                // Get the file path of the image
                String imagePath = hasil.getString("buktibyr");
                // Load the image and create an ImageIcon
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().
                        getScaledInstance(50, 50, Image.SCALE_DEFAULT));
                // Set the ImageIcon as the value for the "Bukti" column
                //id_nilai, NISN, id_matpel, id_ustad, UAS, UTS
                String idOrder = hasil.getString("id_order");
                String idUser = hasil.getString("id_user");
                String jmlP = hasil.getString("jml_pengunjung");
                String tgl = hasil.getString("tanggal");
                String paket = hasil.getString("paket");
                String metod = hasil.getString("metodbyr");
                String status = hasil.getString("status");
                String total = hasil.getString("total");
                // ... (your existing code)

                String act = "";
                // Set the ImageIcon as the value for the "Bukti" column (at index 6)
                String[] data = {idOrder, idUser, jmlP, tgl, paket, metod, imagePath, status, total, act};
                tabmode.addRow(data);
                int rowIndex = tabmode.getRowCount() - 1;
                Ordertable.setValueAt(imageIcon, rowIndex, 6); // Assuming "Bukti" is at index 6
                event();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //tabel user
        Object[] baris1 = {"ID User", "Nama", "Email", "Username", "Password", "Action"};
        DefaultTableModel tabmode1 = new DefaultTableModel(null, baris1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the action column editable, and others non-editable
                return column >= 1; // Assuming "Action" column is at index 9
            }
        };
        usertable.setModel(tabmode1);
        try {
            ResultSet hasil1 = bb.CariData(Integer.parseInt(jTextField1.getText()));
            while (hasil1.next()) {
                String idUser = hasil1.getString("id_user");
                String nama = hasil1.getString("nama");
                String email = hasil1.getString("email");
                String username = hasil1.getString("username");
                String password = hasil1.getString("password");
                String[] data = {idUser, nama, email, username, password};
                tabmode1.addRow(data);
                event();
            }
        } catch (SQLException e) {
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
        Antrian a = new Antrian();
        a.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new DashboardAdmin().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Ordertable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable usertable;
    // End of variables declaration//GEN-END:variables
}
