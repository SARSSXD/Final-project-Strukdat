/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiket_bed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kautsar Quraisy <220605110162@student.uin-malang.ac.id>
 */
public class Cregister {

    private int id_user;
    private String nama;
    private String email;
    private String username;
    private String password;
    private Connection CRUDkoneksi;
    private PreparedStatement CRUDpsmt;
    private String CRUDquery;

    public Cregister() {
        try {
            KoneksiMysql connection = new KoneksiMysql();
            CRUDkoneksi = connection.getKoneksi();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String regisData(String nama, String email, String username, String password) {
        CRUDquery = "INSERT INTO user (nama, email, username, password) VALUES (?,?,?,?)";
        String message = "";
        try {
            register reg = new register();
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery, Statement.RETURN_GENERATED_KEYS);
            CRUDpsmt.setString(1, nama);
            CRUDpsmt.setString(2, email);
            CRUDpsmt.setString(3, username);
            CRUDpsmt.setString(4, password);
            CRUDpsmt.executeUpdate();
            ResultSet generatedKeys = CRUDpsmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idUser = generatedKeys.getInt(1);
                setId_user(idUser);
            }
            CRUDpsmt.close();
            message = " Berhasil Dilakukan";
            JOptionPane.showMessageDialog(null, "Register" + message);
            reg.setVisible(false);
            login log = new login();
            log.setVisible(true);
        } catch (Exception e) {
            message = " Gagal Dilakukan";
            JOptionPane.showMessageDialog(null, "Simpan Data" + message);
            System.out.println(e);
        }
        return null;
    }

    public String ubahData(int id_user, String nama, String email, String username, String password) {
        CRUDquery = "update user set nama=?, email=?, username=?, password=? where id_user=?";
        String message = "";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, nama);
            CRUDpsmt.setString(2, email);
            CRUDpsmt.setString(3, username);
            CRUDpsmt.setString(4, password);
            CRUDpsmt.setInt(5, id_user);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
            message = " Berhasil Dilakukan";
            JOptionPane.showMessageDialog(null, "Ubah Data" + message);
        } catch (Exception e) {
            message = " Gagal Dilakukan";
            JOptionPane.showMessageDialog(null, "Ubah Data" + message);
            System.out.println(e);
        }
        return null;
    }

    public ResultSet tampilData() {
        ResultSet hasil = null;
        String CRUDquery = "SELECT * FROM user";
        try {
            Statement CRUDstat = CRUDkoneksi.createStatement();
            hasil = CRUDstat.executeQuery(CRUDquery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasil;
    }

    public ResultSet CariData(int id_user) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM user WHERE id_user = ?";
            PreparedStatement preparedStatement = CRUDkoneksi.prepareStatement(query);
            preparedStatement.setInt(1, id_user);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet CariDataUser(int id_user) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM data_user WHERE id_user = ?";
            PreparedStatement preparedStatement = CRUDkoneksi.prepareStatement(query);
            preparedStatement.setInt(1, id_user);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void bubbleSort(JTable table, int columnIndex) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int n = model.getRowCount();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                String current = (String) model.getValueAt(i, columnIndex);
                String next = (String) model.getValueAt(i + 1, columnIndex);
                if (current.compareTo(next) > 0) { //ascending jika current lebih besar dari next maka swap
                    // Swap rows if needed
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object temp = model.getValueAt(i, j);
                        model.setValueAt(model.getValueAt(i + 1, j), i, j);
                        model.setValueAt(temp, i + 1, j);
                    }
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }
}
