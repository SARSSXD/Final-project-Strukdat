/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiket_bed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, nama);
            CRUDpsmt.setString(2, email);
            CRUDpsmt.setString(3, username);
            CRUDpsmt.setString(4, password);
            CRUDpsmt.executeUpdate();
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
}
