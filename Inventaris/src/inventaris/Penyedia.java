/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris;

import database.Database;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Emp. Elesar II
 */
public class Penyedia extends Orang {

    private ArrayList<Barang> daftarBarang;
    private long id_penyedia;
    protected int jumBarang = 0;

    public Penyedia(long id, String nama, String username, String password) {
        super(nama, username, password);
        daftarBarang = new ArrayList<>();
        id_penyedia = id;
    }

    public void setID(long id) {
        id_penyedia = id;
    }

    public long getID() {
        return id_penyedia;
    }

    public Barang getBarang(int n) {
        return daftarBarang.get(n);
    }

    public void createBarang(int id, String nama, int jumlah) {
        daftarBarang.add(new Barang(id, nama, jumlah));
        jumBarang = daftarBarang.size();
    }

    public int findBarang(int id) {
        for (int i = 0; i < jumBarang; i++) {
            if (daftarBarang.get(i).getID() == id) {
                return i;
            }
        }
        return -1;
    }
    /*
    public void saveBarang(int id, String nama, int jumlah){
        Database db = new Database();
        String s = "insert into barang_penyedia values("+getID()+","+id
                +",'"+nama+"',"+jumlah+",null)";
        try {
            db.query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void updateBarang(int id, int jumlah){
        Database db = new Database();
        String s = "update barang_penyedia set jumlah = "+jumlah+" where id_barang = "+id+" and id_penyedia = "+getID();
        try {
            db.query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void deleteBarang(int id){
        Database db = new Database();
        String s = "delete from barang_penyedia where id_barang = "+id+" and id_penyedia = "+getID();
        try {
            db.query(s);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    */
    public void view() {
        for (int i = 0; i < jumBarang; i++) {
            daftarBarang.get(i).view1();
            System.out.println();
        }
    }

    public void hapusBarang(int id) {
        int j = findBarang(id);
        if (j != -1) {
            daftarBarang.remove(j);
            jumBarang = daftarBarang.size();
        } else System.out.println("Data barang tidak ada");
    }

    public void ubahBarang(int id, int jum) {
        int i = findBarang(id);
        if (i != -1) {
            daftarBarang.get(i).updateJumlah(jum);
        } else System.out.println("Data barang tidak ada");
    }

    public String toString() {
        return "ID\t: " + id_penyedia + "\nNama\t: " + super.getNama();
    }
}
