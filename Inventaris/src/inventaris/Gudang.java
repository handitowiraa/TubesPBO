/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris;

/**
 *
 * @author Handito
 */
public class Gudang {
    
    private Barang[] daftarBarang;
    private int jumBarang = 0;
    
    public Gudang() {
        this.daftarBarang = new Barang[100];
    }
    
    public void addBarang(Barang b, String kondisi) {
        if (jumBarang < daftarBarang.length) {
            daftarBarang[jumBarang] = b;
            daftarBarang[jumBarang].setKondisi(kondisi);
            jumBarang++;
        }
    }
    
    public int findBarang(int id) {
        if (daftarBarang[id].getID() == id) {
            return id;
        } else {
            return 0;
        }
    }
    
    public void view() {
        for (int i = 0; i < daftarBarang.length; i++) {
            daftarBarang[i].view2();
        }
    }
    
    public Barang getBarang(int id) {
        if (daftarBarang[id].getID() == id) {
            return daftarBarang[id];
        } else {
            return daftarBarang[0];
        }
    }
    
    public void deleteBarang(int id) {
        if (daftarBarang[id].getID() == id) {
            getBarang(id).updateJumlah(0);
            System.out.println("\nData barang dengan id: " + daftarBarang[id].getID() + " berhasil dihapus");
        }
    }
}
