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
    private int id_penyedia;
    private int jumBarang = 0;

    public Penyedia(int id, String nama, String username, String password) {
        super(nama, username, password);
        daftarBarang = new ArrayList<>();
        id_penyedia = id;
    }

    public void setID(int id) {
        id_penyedia = id;
    }

    public int getID() {
        return id_penyedia;
    }

    public Barang getBarang(int n) {
        return daftarBarang.get(n);
    }
    
    public void createBarang(int id, String nama, int jumlah) {
        daftarBarang.add(new Barang(id, nama, jumlah));
        setJumBarang(daftarBarang.size());
    }

    public int findBarang(int id) {
        for(Barang b : daftarBarang){
            if (b.getID()==id)
                return daftarBarang.indexOf(b);
        }
        return -1;
    }
    
    public void view() {
        for (int i = 0; i < getJumBarang(); i++) {
            daftarBarang.get(i).view1();
            System.out.println();
        }
    }

    public void hapusBarang(int id) {
        int j = findBarang(id);
        if (j != -1) {
            daftarBarang.remove(j);
            setJumBarang(daftarBarang.size());
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

    /**
     * @return the jumBarang
     */
    public int getJumBarang() {
        return jumBarang;
    }

    /**
     * @param jumBarang the jumBarang to set
     */
    public void setJumBarang(int jumBarang) {
        this.jumBarang = jumBarang;
    }
    
    public String[] getListDataBarang(){
        String[] s = new String[daftarBarang.size()];
        int j = 0;
        for (Barang i : daftarBarang){
            s[j] = "ID."+String.valueOf(i.getID())+" "+i.getNama();
            j++;
        }
        return s;
    }
    
    public ArrayList<Barang> getListBarang() {
        return daftarBarang;
    }
}
