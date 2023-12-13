/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tiket_bed;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kautsar Quraisy <220605110162@student.uin-malang.ac.id>
 */
public class Antrian extends javax.swing.JFrame {

    private Queue<String> antrian = new LinkedList<>();
    private int processedCount = 0;
    Corder aa = new Corder();

    /**
     * Creates new form Antrian
     */
    public Antrian() {
        initComponents();
        tambahdaftar();
        tampilkanAntrian();
        setset();
    }

    private void setset() {
        jTextPane1.setEditable(false);
        jTextPane2.setEditable(false);
        jTextPane1.setFocusable(false);
        jTextPane2.setFocusable(false);
        jmlAntrianLabel.setText(Integer.toString(antrian.size()));
    }

    private void tambahdaftar() {
        ResultSet hasil = aa.CariDatametod("Tunai", "Belum Lunas");
        try {
            while (hasil.next()) {
                antrian.add(hasil.getString("id_order"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Antrian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void tampilkanAntrian() {
        // Tampilkan antrian pada jTextPane1
        StringBuilder antrianText = new StringBuilder("Antrian:\n");
        for (String idOrder : antrian) {
            antrianText.append(idOrder).append("\n");
        }
        jTextPane1.setText(antrianText.toString());
    }

    private void prosesAntrian() {
        // Proses satu id order dari antrian
        String idOrder = antrian.poll();
        int idO = Integer.parseInt(idOrder);
        if (idOrder != null) {
            // Increment the processed count
            processedCount++;

            // Lakukan sesuatu dengan id order yang diambil
            nomorpangLabel.setText(idOrder);
            jTextPane2.setText("ID Order yang diproses: " + idOrder + "\n\nHarap segera ke loket \ndan melakukan pembayaran\nNomor Antrian:\n" + idOrder + "\nSemoga Liburan Anda Menyenangkan ^.^");
            // Update the text of jmlSelesaiLabel
            jmlSelesaiLabel.setText(Integer.toString(processedCount));
            ResultSet hasil = aa.CariData(idO);
            try {
                while (hasil.next()) {
                    aa.ubahData(idO, "Lunas");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Antrian.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            jTextPane2.setText("Antrian kosong.");
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jmlSelesaiLabel = new javax.swing.JLabel();
        nomorpangLabel = new javax.swing.JLabel();
        jmlAntrianLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 250, 300));

        jButton1.setBackground(new java.awt.Color(255, 102, 255));
        jButton1.setText("Proses");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 460, 100, 40));

        jScrollPane2.setViewportView(jTextPane2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 330, 240, 170));

        jmlSelesaiLabel.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jmlSelesaiLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jmlSelesaiLabel.setText("0");
        getContentPane().add(jmlSelesaiLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 60, 50));

        nomorpangLabel.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        nomorpangLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomorpangLabel.setText("0");
        getContentPane().add(nomorpangLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 260, 60, 50));

        jmlAntrianLabel.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jmlAntrianLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jmlAntrianLabel.setText("0");
        getContentPane().add(jmlAntrianLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 60, 50));

        jButton2.setText("Log Out");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 535, 80, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bed7.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        prosesAntrian();
        setset();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        login l = new login();
        l.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Antrian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Antrian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Antrian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Antrian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Antrian().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JLabel jmlAntrianLabel;
    private javax.swing.JLabel jmlSelesaiLabel;
    private javax.swing.JLabel nomorpangLabel;
    // End of variables declaration//GEN-END:variables
}
