/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import inventaris.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emp. Elesar II
 */
public class Database {

    private String dbUser = "root";
    private String dbPass = "@nim@ri@n142434";
    private Statement stmt = null;
    private Connection con = null;
    private ResultSet rs = null;

    public Database() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/dbTubesPBO", dbUser, dbPass);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet getData(String SQLString) {
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQLString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public void query(String SQLString) throws SQLException {
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(SQLString);
        } catch (SQLException c) {
            throw new SQLException("Error eksekusi query");
        }
    }
    
    public ArrayList<Gudang> readAllGudang(){
        ArrayList<Gudang> daftarGudang = new ArrayList();
        String s = "Select id_gudang, nama_gudang from gudang";
        ResultSet rs = getData(s);
        try {
            while(rs.next()){
                Gudang g = new Gudang(rs.getInt("id_gudang"),rs.getString("nama_gudang"));
                s = "Select id_barang, nama_barang, jumlah, kondisi from barang_gudang where id_gudang = "+rs.getInt("id_gudang");
                ResultSet rs2 = getData(s);
                while(rs2.next()){
                    Barang b = new Barang(rs2.getInt("id_barang"),rs2.getString("nama_barang"),rs2.getInt("jumlah"));
                    g.addBarangFromDatabase(b,rs2.getString("kondisi"));
                }
                daftarGudang.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Aplikasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return daftarGudang;
    }
    
    public ArrayList<Orang> readAllOrang(){
        ArrayList<Orang> daftarOrang = new ArrayList();
        String s = "Select id_petugas, nama, username, password from petugas";
        ResultSet rs = getData(s);
        try {
            while(rs.next()){
                Petugas pt = new Petugas(rs.getInt("id_petugas"),rs.getString("nama"),rs.getString("username"),rs.getString("password"));
                daftarOrang.add(pt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Aplikasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        s = "Select id_penyedia, nama, username, password from penyedia";
        rs = getData(s);
        try {
            while(rs.next()){
                Penyedia py = new Penyedia(rs.getInt("id_penyedia"),rs.getString("nama"),rs.getString("username"),rs.getString("password"));
                s = "Select id_barang, nama_barang, jumlah, kondisi from barang_penyedia where id_penyedia = "+rs.getInt("id_penyedia");
                ResultSet rs2 = getData(s);
                while(rs2.next()){
                    py.createBarang(rs2.getInt("id_barang"),rs2.getString("nama_barang"),rs2.getInt("jumlah"));
                }
                daftarOrang.add(py);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return daftarOrang;
    }
    
    public void savePetugas(int id, String nama, String user, String pass){
        String s = "insert into petugas values("+id+",'"+nama
                +"','"+user+"','"+pass+"')";
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void savePenyedia(int id, String nama, String user, String pass){
        String s = "insert into penyedia values("+id+",'"+nama
                +"','"+user+"','"+pass+"')";
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void saveGudang(int id){
        String s = "insert into gudang values("+id+",null)";
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void saveGudang(int id, String nama){
        String s = "insert into gudang values("+id+",'"+nama+"')";
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void deletePetugas(int id){
        String s = "delete from petugas where id_petugas = "+id;
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void deletePenyedia(int id){
        String s = "delete from penyedia where id_penyedia= "+id;
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void deleteGudang(int id){
        String s = "delete from gudang where id_gudang = "+id;
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void updatePenyedia(int id, String nama, String user, String pass){
        String s = "update penyedia set nama = '"+nama+"', username = '"+user
                +"', password = '"+pass+"' where id_penyedia = "+id;
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void updatePetugas(int id, String nama, String user, String pass){
        String s = "update Petugas set nama = '"+nama+"', username = '"+user
                +"', password = '"+pass+"' where id_Petugas = "+id;
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void updateGudang(int id, String nama){
        String s = "update gudang set nama_gudang = '"+nama+"' where id_Gudang = "+id;
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void saveBarang(Penyedia py, int id, String nama, int jumlah){
        String s = "insert into barang_penyedia values("+py.getID()+","+id
                +",'"+nama+"',"+jumlah+",null)";
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void updateBarang(Penyedia py, int id, int jumlah){
        String s = "update barang_penyedia set jumlah = "+jumlah+" where id_barang = "+id+" and id_penyedia = "+py.getID();
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void deleteBarang(Penyedia py, int id){
        String s = "delete from barang_penyedia where id_barang = "+id+" and id_penyedia = "+py.getID();
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void saveBarang(Gudang g, Barang b, int id2){
        String s = "insert into barang_gudang values("+g.getID()+","+id2
                +",'"+b.getNama()+"',"+b.getJumlah()+",'"+b.getKondisi()+"')";
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void updateBarang(Gudang g, int id, int jumlah,String kondisi){
        String s = "update barang_gudang set jumlah = "+jumlah+", kondisi = '"+kondisi
                +"' where id_barang = "+id+" and id_gudang = "+g.getID();
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void deleteDBBarang(Gudang g, int id){
        String s = "delete from barang_gudang where id_barang = "+id+" and id_gudang = "+g.getID();
        try {
            query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
