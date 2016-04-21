/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris;

import java.util.ArrayList;

/**
 *
 * @author Kelompok 8
 */
public class Gudang {

    private ArrayList<Barang> daftarBarang;
    private int jumBarang = 0;
    private int id_gudang;
    private String nama_gudang;

    public Gudang(int id) {
        id_gudang = id;
        this.daftarBarang = new ArrayList<>();
    }

    public Gudang(int id, String nama) {
        id_gudang = id;
        nama_gudang = nama;
        this.daftarBarang = new ArrayList<>();
    }

    public void setID(int id) {
        id_gudang = id;
    }

    public int getID() {
        return id_gudang;
    }

    public void addBarang(Barang b, int id2) {
        b.setIDLama(b.getID());
        b.setID(id2);
        daftarBarang.add(b);
        jumBarang = daftarBarang.size();
    }

    public void addBarangFromDatabase(Barang b, int baik) {
        b.setKondisiBaik(baik);
        b.setKondisiBuruk();
        daftarBarang.add(b);
        jumBarang = daftarBarang.size();
    }

    public ArrayList<Barang> getListBarang() {
        return daftarBarang;
    }

    public String[] getDataDaftarBarang() {
        String[] s = new String[daftarBarang.size()];
        int j = 0;
        for (Barang i : daftarBarang) {
            s[j] = "ID." + String.valueOf(i.getID()) + " " + i.getNama();
            j++;
        }
        return s;
    }

    public int findBarang(int id) {
        for (Barang b : daftarBarang) {
            if (b.getID() == id) {
                return daftarBarang.indexOf(b);
            }
        }
        return -1;
    }

    public void view() {
        for (int i = 0; i < jumBarang; i++) {
            daftarBarang.get(i).view2();
        }
    }

    public Barang getBarang(int id) {
        if (findBarang(id) != -1) {
            return daftarBarang.get(findBarang(id));
        } else {
            return null;
        }
    }

    public ArrayList<Barang> cariNama(String nama) {
        ArrayList<Barang> t = new ArrayList<>();
        for (Barang b : daftarBarang) {
            if (b.getNama().equalsIgnoreCase(nama)) {
                t.add(b);
            }
        }
        return t;
    }

    /*
    public ArrayList<Barang> cariKondisi(String kondisi){
        ArrayList<Barang> t = new ArrayList<>();
        for (Barang b : daftarBarang){
            if (b.getKondisi().equalsIgnoreCase(kondisi))
                t.add(b);
        }
        return t;
    }
     */
    public Barang loadBarang(int n) {
        return daftarBarang.get(n);
    }

    public void deleteBarang(int id) {
        int idx = findBarang(id);
        if (idx != -1) {
            daftarBarang.remove(idx);
            jumBarang = daftarBarang.size();
            System.out.println("\nData barang berhasil dihapus");
        } else {
            System.out.println("\nData barang tidak ada");
        }
    }

    /**
     * @return the nama_gudang
     */
    public String getNama_gudang() {
        return nama_gudang;
    }

    /**
     * @param nama_gudang the nama_gudang to set
     */
    public void setNama_gudang(String nama_gudang) {
        this.nama_gudang = nama_gudang;
    }
}
