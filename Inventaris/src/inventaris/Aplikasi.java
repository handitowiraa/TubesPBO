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
    private Database db;
    private String[] angka = {"1","2","3","4","5","6","7","8","9","0"};

    public Aplikasi() {
        db = new Database();
        //daftarOrang = new ArrayList();
        daftarOrang = db.readAllOrang();
        //daftarGudang = new ArrayList();
        daftarGudang = db.readAllGudang();
        jumOrang = daftarOrang.size();
        jumGudang = daftarGudang.size();
    }
    
    public boolean cekNama(String name){
        boolean benar = true;
        for (String a : angka){
            if (name.contains(a))
                    benar = false;
        }
        return benar;
    }
    
    public void addPenyedia(int id, String nama, String username, String password) {
        if (jumOrang < 100) {
            if (getPenyedia(id) == null) {
                if (!(cariUsername(username))) {
                    if (cekNama(nama)){
                        daftarOrang.add(new Penyedia(id, nama, username, password));
                        db.savePenyedia(id, nama, username, password);
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
                        db.savePetugas(id, nama, username, password);
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
    
    public void ubahPenyedia(int id, String nama, String username, String password) {
        Penyedia p = getPenyedia(id);
        p.setNama(nama);
        p.setUsername(username);
        p.setPassword(password);
        db.updatePenyedia(id, nama, username, password);
    }
    
    public void ubahPetugas(int id, String nama, String username, String password) {
        Petugas p = getPetugas(id);
        p.setNama(nama);
        p.setUsername(username);
        p.setPassword(password);
        db.updatePetugas(id, nama, username, password);
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
                    db.deletePenyedia(id);
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
                    db.deletePetugas(id);
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
        db.saveGudang(id, nama);
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
        db.updateGudang(id, nama);
        Gudang g = getGudang(id);
        g.setNama_gudang(nama);
    }

    public void deleteGudang(int id) {
        int j = -1;
        for (int i = 0; i < jumGudang; i++) {
            if (daftarGudang.get(i).getID() == id) {
                daftarGudang.remove(i);
                db.deleteGudang(id);
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
            db.saveBarang(py, id, nama, jumlah);
            System.out.println("Barang berhasil ditambahkan");
        } else {
            System.out.println("Maaf, kode barang sudah ada");
        }
    }

    public void menuPyUbahBrg(Penyedia py, int id, int jumlah) {
        int j = py.findBarang(id);
        if (j != -1) {
            py.ubahBarang(id, jumlah);
            db.updateBarang(py, id, jumlah);
            System.out.println("Data barang telah diubah");
        } else {
            System.out.println("Data barang tidak ada");
        }
    }

    public void menuPyDeleteBrg(Penyedia py, int id) {
        int j = py.findBarang(id);
        if (j != -1) {
            py.hapusBarang(id);
            db.deleteBarang(py, id);
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
        db.saveBarang(g,b,b.getID());
    }
    
    public void menuPtTambahBrg(Petugas pt, Penyedia py, Gudang g, int id, int id2) {
        if (py.findBarang(id) != -1) {
            if (g.findBarang(id2) == -1){
                String k = "Baik";
                Barang b = py.getBarang(py.findBarang(id));
                py.hapusBarang(id);
                menuPyDeleteBrg(py,id);
                pt.tambahBarang(g, b, k, id2);
                db.saveBarang(g,py.getBarang(py.findBarang(id)),id2);
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
            db.updateBarang(g, id, jum, kondisi);
            System.out.println("Data sudah di-update");
        } else {
            System.out.println("Data barang tidak ada");
        }
    }

    public void menuPtDeleteBrg(Petugas pt, Gudang g, int id) {
        pt.hapusBarang(g,id);
        db.deleteDBBarang(g,id);
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
