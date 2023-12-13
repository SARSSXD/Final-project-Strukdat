/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiket_bed;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kautsar Quraisy <220605110162@student.uin-malang.ac.id>
 */
public class Corder {

    private int id_order, id_user, jml_pengunjung;
    private String tanggal, paket, metodbyr, total, status;
    private Connection CRUDkoneksi;
    private PreparedStatement CRUDpsmt;
    private String CRUDquery;

    public Corder() {
        try {
            KoneksiMysql connection = new KoneksiMysql();
            CRUDkoneksi = connection.getKoneksi();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getJml_pengunjung() {
        return jml_pengunjung;
    }

    public void setJml_pengunjung(int jml_pengunjung) {
        this.jml_pengunjung = jml_pengunjung;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public String getMetodbyr() {
        return metodbyr;
    }

    public void setMetodbyr(String metodbyr) {
        this.metodbyr = metodbyr;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String addData(int id_user, int jml_pengunjung, String tanggal, String paket, String metodbyr, String buktibyr, String status, String total) {
        CRUDquery = "INSERT INTO `order` (id_user, jml_pengunjung, tanggal, paket, metodbyr, buktibyr, status, total) VALUES (?,?,?,?,?,?,?,?)";
        String message = "";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery, Statement.RETURN_GENERATED_KEYS);
            CRUDpsmt.setInt(1, id_user);
            CRUDpsmt.setInt(2, jml_pengunjung);
            CRUDpsmt.setString(3, tanggal);
            CRUDpsmt.setString(4, paket);
            CRUDpsmt.setString(5, metodbyr);
            CRUDpsmt.setString(6, buktibyr);
            CRUDpsmt.setString(7, status);
            CRUDpsmt.setString(8, total);
            CRUDpsmt.executeUpdate();
            message = " Berhasil Dilakukan, Menunggu Pembayaran";
            JOptionPane.showMessageDialog(null, "Booking Tiket" + message);
            ResultSet generatedKeys = CRUDpsmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idOrder = generatedKeys.getInt(1);
                JOptionPane.showMessageDialog(null, "id_order anda adalah " + idOrder);
            }
            CRUDpsmt.close();
        } catch (HeadlessException | SQLException e) {
            message = " Gagal Dilakukan";
            JOptionPane.showMessageDialog(null, "Penambahan Data Order" + message);
            System.out.println(e);
        }
        return null;
    }

    public String uploadBukti(int id_order, String buktibyr) {
        CRUDquery = "update `order` set buktibyr=? where id_order=?";
        String message = "";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, buktibyr);
            CRUDpsmt.setInt(2, id_order);
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

    public String ubahData(int id_order, String status) {
        CRUDquery = "update `order` set status=? where id_order=?";
        String message = "";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, status);
            CRUDpsmt.setInt(2, id_order);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
            message = " Berhasil Dilakukan";
            JOptionPane.showMessageDialog(null, "Ubah Status" + message);
        } catch (Exception e) {
            message = " Gagal Dilakukan";
            JOptionPane.showMessageDialog(null, "Ubah Data" + message);
            System.out.println(e);
        }
        return null;
    }

    public ResultSet CariData(int id_order) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM `order` WHERE id_order = ?";
            PreparedStatement preparedStatement = CRUDkoneksi.prepareStatement(query);
            preparedStatement.setInt(1, id_order);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet Tampilhome(int id_user) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM `order` WHERE id_user = ?";
            PreparedStatement preparedStatement = CRUDkoneksi.prepareStatement(query);
            preparedStatement.setInt(1, id_order);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
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

    public ResultSet CariDataBook(int iduser) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM user WHERE id_user = ?";
            PreparedStatement preparedStatement = CRUDkoneksi.prepareStatement(query);
            preparedStatement.setInt(1, iduser);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet CariDatametod(String metode,String status) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM `order` WHERE metodbyr = ? AND status = ?";
            PreparedStatement preparedStatement = CRUDkoneksi.prepareStatement(query);
            preparedStatement.setString(1, metode);
            preparedStatement.setString(2, status);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet tampilData(int id_user) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM `order` WHERE id_user = ?";
            PreparedStatement preparedStatement = CRUDkoneksi.prepareStatement(query);
            preparedStatement.setInt(1, id_user);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet tampilData() {
        ResultSet hasil = null;
        String CRUDquery = "SELECT * FROM `order`";
        try {
            Statement CRUDstat = CRUDkoneksi.createStatement();
            hasil = CRUDstat.executeQuery(CRUDquery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasil;
    }

    public static void bubbleSortForOrders(JTable table, int columnIndex) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();

        for (int i = 0; i < rowCount - 1; i++) {
            for (int j = 0; j < rowCount - i - 1; j++) {
                Comparable<Object> value1 = (Comparable<Object>) model.getValueAt(j, columnIndex);
                Comparable<Object> value2 = (Comparable<Object>) model.getValueAt(j + 1, columnIndex);

                if (value1.compareTo(value2) > 0) {
                    // Swap rows if needed
                    for (int k = 0; k < model.getColumnCount(); k++) {
                        Object temp = model.getValueAt(j, k);
                        model.setValueAt(model.getValueAt(j + 1, k), j, k);
                        model.setValueAt(temp, j + 1, k);
                    }
                }
            }
        }
    }
}
