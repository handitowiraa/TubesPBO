/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris;

/**
 *
 * @author Emp. Elesar II
 */
import database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Aplikasi {

    private ArrayList<Orang> daftarOrang;
    private ArrayList<Gudang> daftarGudang;
    int jumOrang = 0;
    int jumGudang = 0;
    private String[] angka = {"1","2","3","4","5","6","7","8","9","0"};

    public Aplikasi() {
        daftarOrang = new ArrayList<>();
        daftarGudang = new ArrayList<>();
    }
    
    public boolean cekNama(String name){
        boolean benar = true;
        for (String a : angka){
            if (name.contains(a))
                    benar = false;
        }
        return benar;
    }
    
    
    /*public void updatePenyedia(int id, String nama, String user, String pass){
        Database db = new Database();
        String s = "update penyedia set nama = '"+nama+"', username = '"+user
                +"', password = '"+pass+"' where id_penyedia = "+id;
        try {
            db.query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void readAllGudang(){
        Database db = new Database();
        String s = "Select id_gudang, nama_gudang from gudang";
        ResultSet rs = db.getData(s);
        try {
            while(rs.next()){
                Gudang g = new Gudang(rs.getInt("id_gudang"),rs.getString("nama_gudang"));
                s = "Select id_barang, nama_barang, jumlah, kondisi from barang_gudang where id_gudang = "+rs.getInt("id_gudang");
                ResultSet rs2 = db.getData(s);
                while(rs2.next()){
                    Barang b = new Barang(rs2.getInt("id_barang"),rs2.getString("nama_barang"),rs2.getInt("jumlah"));
                    g.addBarangFromDatabase(b,rs2.getString("kondisi"));
                }
                daftarGudang.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Aplikasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        jumGudang = daftarGudang.size();
    }
    
    public void readAllOrang(){
        Database db = new Database();
        String s = "Select id_petugas, nama, username, password from petugas";
        ResultSet rs = db.getData(s);
        try {
            while(rs.next()){
                Petugas pt = new Petugas(rs.getInt("id_petugas"),rs.getString("nama"),rs.getString("username"),rs.getString("password"));
                daftarOrang.add(pt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Aplikasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        s = "Select id_penyedia, nama, username, password from penyedia";
        rs = db.getData(s);
        try {
            while(rs.next()){
                Penyedia py = new Penyedia(rs.getInt("id_penyedia"),rs.getString("nama"),rs.getString("username"),rs.getString("password"));
                s = "Select id_barang, nama_barang, jumlah, kondisi from barang_penyedia where id_penyedia = "+rs.getInt("id_penyedia");
                ResultSet rs2 = db.getData(s);
                while(rs2.next()){
                    py.createBarang(rs2.getInt("id_barang"),rs2.getString("nama_barang"),rs2.getInt("jumlah"));
                }
                daftarOrang.add(py);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        jumOrang = daftarOrang.size();
    }
    
    public void savePetugas(int id, String nama, String user, String pass){
        Database db = new Database();
        String s = "insert into petugas values("+id+",'"+nama
                +"','"+user+"','"+pass+"')";
        try {
            db.query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void savePenyedia(int id, String nama, String user, String pass){
        Database db = new Database();
        String s = "insert into penyedia values("+id+",'"+nama
                +"','"+user+"','"+pass+"')";
        try {
            db.query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void saveGudang(int id){
        Database db = new Database();
        String s = "insert into gudang values("+id+",null)";
        try {
            db.query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void saveGudang(int id, String nama){
        Database db = new Database();
        String s = "insert into gudang values("+id+",'"+nama+"')";
        try {
            db.query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void delete2Petugas(int id){
        Database db = new Database();
        String s = "delete from petugas where id_petugas = "+id;
        try {
            db.query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void delete2Penyedia(int id){
        Database db = new Database();
        String s = "delete from penyedia where id_penyedia= "+id;
        try {
            db.query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void delete2Gudang(int id){
        Database db = new Database();
        String s = "delete from gudang where id_gudang = "+id;
        try {
            db.query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    */
    public void addPenyedia(int id, String nama, String username, String password) {
        if (jumOrang < 100) {
            if (getPenyedia(id) == null) {
                if (!(cariUsername(username))) {
                    if (cekNama(nama)){
                        daftarOrang.add(new Penyedia(id, nama, username, password));
                        jumOrang=daftarOrang.size();
                        System.out.println("Data berhasil disimpan");
                    } else {
                        System.out.println("Nama tidak valid");
                    }
                } else {
                    System.out.println("Username sudah digunakan");
                }
            } else {
                System.out.println("ID sudah digunakan");
            }
        }
    }
    
    public void addPetugas(int id, String nama, String username, String password) {
        if (jumOrang < 100) {
            if (getPetugas(id) == null) {
                if (!(cariUsername(username))) {
                    if (cekNama(nama)){
                        daftarOrang.add(new Petugas(id, nama, username, password));
                        jumOrang = daftarOrang.size();
                        System.out.println("Data berhasil disimpan");
                    } else {
                        System.out.println("Nama tidak valid");
                    }
                } else {
                    System.out.println("Username sudah digunakan");
                }
            } else {
                System.out.println("ID sudah digunakan");
            }
        }
    }

    public void updatePetugas(int id, String nama, String user, String pass){
        Database db = new Database();
        String s = "update Petugas set nama = '"+nama+"', username = '"+user
                +"', password = '"+pass+"' where id_Petugas = "+id;
        try {
            db.query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void updateGudang(int id, String nama){
        Database db = new Database();
        String s = "update gudang set nama_gudang = '"+nama+"' where id_Gudang = "+id;
        try {
            db.query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void ubahPenyedia(int id, String nama, String username, String password) {
        Penyedia p = getPenyedia(id);
        p.setNama(nama);
        p.setUsername(username);
        p.setPassword(password);
    }
    
    public Gudang ambilGudang(int n){
        return daftarGudang.get(n);
    }
    
    public ArrayList<Gudang> getListGudang(){
        return daftarGudang;
    }
    
    public Orang getOrang(int n){
        return daftarOrang.get(n);
    }
    
    public ArrayList<Orang> getListOrang(){
        return daftarOrang;
    }
    
    public void ubahPetugas(int id, String nama, String username, String password) {
        Petugas p = getPetugas(id);
        p.setNama(nama);
        p.setUsername(username);
        p.setPassword(password);
    }
    
    public Petugas getPetugas(int id) {
        for (int i = 0; i < jumOrang; i++) {
            if (daftarOrang.get(i) instanceof Petugas) {
                Petugas pt = (Petugas) daftarOrang.get(i);
                if (pt.getIdPetugas() == id) {
                    return pt;
                }
            }
        }
        return null;
    }
    
    public Petugas getUserPetugas(String user) {
        for (int i = 0; i < jumOrang; i++) {
            if (daftarOrang.get(i) instanceof Petugas) {
                Petugas pt = (Petugas) daftarOrang.get(i);
                if (pt.getUsername().equals(user)) {
                    return pt;
                }
            }
        }
        return null;
    }
    
    public int hitungPenyedia(){
        int jumlah = 0;
        for (Orang i : daftarOrang){
            if (i instanceof Penyedia){
                jumlah++;
            }
        }
        return jumlah;
    }
    
    public String[] getIDPenyedia(){
        String[] s = new String[hitungPenyedia()];
        int j = 0;
        for (Orang i : daftarOrang){
            if (i instanceof Penyedia){
                s[j] = String.valueOf(((Penyedia) i).getID());
                j++;
            }
        }
        return s;
    }
    
    public String[] getIDGudang(){
        String[] s = new String[daftarGudang.size()];
        int j = 0;
        for (Gudang i : daftarGudang){
            s[j] = String.valueOf(i.getID());
            j++;
        }
        return s;
    }

    public Penyedia getPenyedia(int id) {
        for (int i = 0; i < jumOrang; i++) {
            if (daftarOrang.get(i) instanceof Penyedia) {
                Penyedia py = (Penyedia) daftarOrang.get(i);
                if (py.getID() == id) {
                    return py;
                }
            }
        }
        return null;
    }
    
    public Penyedia getUserPenyedia(String user) {
        for (int i = 0; i < jumOrang; i++) {
            if (daftarOrang.get(i) instanceof Penyedia) {
                Penyedia py = (Penyedia) daftarOrang.get(i);
                if (py.getUsername().equals(user)) {
                    return py;
                }
            }
        }
        return null;
    }

    public void deletePenyedia(int id) {
        int j = -1;
        for (int i = 0; i < jumOrang; i++) {
            if (daftarOrang.get(i) instanceof Penyedia) {
                Penyedia py = (Penyedia) daftarOrang.get(i);
                if (py.getID() == id) {
                    daftarOrang.remove(i);
                    jumOrang = daftarOrang.size();
                    break;
                }
            }
        }
    }

    public void deletePetugas(int id) {
        int j = -1;
        for (int i = 0; i < jumOrang; i++) {
            if (daftarOrang.get(i) instanceof Petugas) {
                Petugas pt = (Petugas) daftarOrang.get(i);
                if (pt.getIdPetugas() == id) {
                    daftarOrang.remove(i);
                    jumOrang = daftarOrang.size();
                    break;
                }
            }
        }
    }
    
    
    
    public int findUsername(String user){
        for (Orang p : daftarOrang){
            if (p.getUsername().equals(user))
                return daftarOrang.indexOf(p);
        }
        return -1;
    }

    public void addGudang(int id) {
        daftarGudang.add(new Gudang(id));
        jumGudang = daftarGudang.size();
    }
    
    public void tambahGudang(int id, String nama) {
        daftarGudang.add(new Gudang(id,nama));
        jumGudang = daftarGudang.size();
    }

    public Gudang getGudang(int id) {
        for (int i = 0; i < jumGudang; i++) {
            if (daftarGudang.get(i).getID() == id) {
                return daftarGudang.get(i);
            }
        }
        return null;
    }
    
    public void ubahGudang(int id, String nama){
        Gudang g = getGudang(id);
        g.setNama_gudang(nama);
    }

    public void deleteGudang(int id) {
        int j = -1;
        for (int i = 0; i < jumGudang; i++) {
            if (daftarGudang.get(i).getID() == id) {
                daftarGudang.remove(i);
                jumGudang = daftarGudang.size();
                break;
            }
        }
    }

    private boolean cariUsername(String user) {
        for (int i = 0; i < jumOrang; i++) {
            if (daftarOrang.get(i).getUsername().equals(user)) {
                return true;
            }
        }
        return false;
    }

    public void menuPyTambahBrg(Penyedia py, int id, String nama, int jumlah) {
        if (py.findBarang(id)==-1){
            py.createBarang(id, nama, jumlah);
            //py.saveBarang(id, nama, jumlah);
            System.out.println("Barang berhasil ditambahkan");
        } else {
            System.out.println("Maaf, kode barang sudah ada");
        }
    }

    public void menuPyUbahBrg(Penyedia py, int id, int jumlah) {
        int j = py.findBarang(id);
        if (j != -1) {
            py.ubahBarang(id, jumlah);
            //py.updateBarang(id, jumlah);
            System.out.println("Data barang telah diubah");
        } else {
            System.out.println("Data barang tidak ada");
        }
    }

    public void menuPyDeleteBrg(Penyedia py, int id) {
        int j = py.findBarang(id);
        if (j != -1) {
            py.hapusBarang(id);
            //py.deleteBarang(id);
            System.out.println("Data barang telah dihapus");
        } else {
            System.out.println("Data barang tidak ada");
        }
    }

    public void menuPyView(Penyedia py) {
        py.view();
    }

    public void menuPtInputBrg(Gudang g, Barang b){
        b.setKondisi("Baik");
        g.addBarang(b,b.getKondisi(), b.getID());
        //g.saveBarang(b,b.getID());
    }
    
    public void menuPtTambahBrg(Petugas pt, Penyedia py, Gudang g, int id, int id2) {
        if (py.findBarang(id) != -1) {
            if (g.findBarang(id2) == -1){
                String k = "Baik";
                Barang b = py.getBarang(py.findBarang(id));
                py.hapusBarang(id);
                //py.deleteBarang(id);
                pt.tambahBarang(g, b, k, id2);
                //g.saveBarang(py.getBarang(py.findBarang(id)),id2);
                System.out.println("Barang berhasil ditambahkan");
            } else System.out.println("ID Baru sudah digunakan");
        } else {
            System.out.println("Barang tidak ada");
        }
    }

    public void menuPtEditBrg(Petugas pt, Gudang g, int id, int jum, String kondisi) {
        int i = g.findBarang(id);
        if (i != -1) {
            pt.ubahBarang(g, id, jum, kondisi);
            //g.updateBarang(id, jum, kondisi);
            System.out.println("Data sudah di-update");
        } else {
            System.out.println("Data barang tidak ada");
        }
    }

    public void menuPtDeleteBrg(Petugas pt, Gudang g, int id) {
        pt.hapusBarang(g,id);
        //g.deleteDBBarang(id);
    }
   
    public void menuPtViewGudang(int id) {
        Gudang g = getGudang(id);
        if (g != null) {
            g.view();
        } else {
            System.out.println("Gudang tidak ada");
        }
    }

    public Orang login(String user, String pass){
        boolean masuk = false;
        int i = 0;
        while (i < jumOrang) {
            if ((daftarOrang.get(i).getUsername()).equals(user)) {
                if ((daftarOrang.get(i).getPassword()).equals(pass)) {
                    return daftarOrang.get(i);
                }
            }
            i++;
        }
        return null;
    }
}
