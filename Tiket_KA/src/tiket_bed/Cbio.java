/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiket_bed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Kautsar Quraisy <220605110162@student.uin-malang.ac.id>
 */
public class Cbio {

    private int id_user;
    private String nik;
    private String no_tlp;
    private String norek;
    private String tanggal_lahir;
    private String jk;
    private String alamat;
    private Connection CRUDkoneksi;
    private PreparedStatement CRUDpsmt;
    private String CRUDquery;

    public Cbio() {
        try {
            KoneksiMysql connection = new KoneksiMysql();
            CRUDkoneksi = connection.getKoneksi();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNo_tlp() {
        return no_tlp;
    }

    public void setNo_tlp(String no_tlp) {
        this.no_tlp = no_tlp;
    }

    public String getNorek() {
        return norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String addData(int id_user, int nik, int no_tlp, int norek, String tanggal_lahir, String jk, String alamat) {
        CRUDquery = "INSERT INTO data_user (id_user, nik, no_tlp, norek, tanggal_lahir, jk, alamat) VALUES (?,?,?,?,?,?,?)";
        String message = "";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, id_user);
            CRUDpsmt.setInt(2, nik);
            CRUDpsmt.setInt(3, no_tlp);
            CRUDpsmt.setInt(4, norek);
            CRUDpsmt.setString(5, tanggal_lahir);
            CRUDpsmt.setString(6, jk);
            CRUDpsmt.setString(7, alamat);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
            message = " Berhasil Dilakukan";
        } catch (Exception e) {
            message = " Gagal Dilakukan";
            JOptionPane.showMessageDialog(null, "Penambahan Data Bio" + message);
            System.out.println(e);
        }
        return null;
    }
    public String ubahData(int id_user, String nik, String no_tlp, String norek, String tanggal_lahir, String jk, String alamat) {
        CRUDquery = "update data_user set nik=?, no_tlp=?, norek=?, tanggal_lahir=?, jk=?, alamat=? where id_user=?";
        String message = "";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, nik);
            CRUDpsmt.setString(2, no_tlp);
            CRUDpsmt.setString(3, norek);
            CRUDpsmt.setString(4, tanggal_lahir);
            CRUDpsmt.setString(5, jk);
            CRUDpsmt.setString(6, alamat);
            CRUDpsmt.setInt(7, id_user);
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

    public ResultSet CariData(String email) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM user WHERE email = ?";
            PreparedStatement preparedStatement = CRUDkoneksi.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public ResultSet CariData(int id_user) {
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

}
