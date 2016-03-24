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
    private int id_gudang;
    
    public Gudang(int id){
	id_gudang = id;
	this.daftarBarang = new Barang[100];
    }

    public void setID(int id){
	id_gudang = id;
    }
	
    public int getID(){
	return id_gudang;
    }
    
    public void addBarang(Barang b, String kondisi) {
        if (jumBarang < daftarBarang.length) {
            daftarBarang[jumBarang] = b;
            daftarBarang[jumBarang].setKondisi(kondisi);
            jumBarang++;
        }
    }

    public int findBarang(int id) {
        for (int i = 0; i < jumBarang; i++) {
            if (daftarBarang[i].getID() == id) {
                return i;
            }
        }
        return -1;
    }

    public void view() {
        for (int i = 0; i < jumBarang; i++) {
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
        int idx = findBarang(id);
        if (idx != -1) {
            for (int i = idx; i < jumBarang - 1; i++) {
                jumBarang = jumBarang - 1;
                daftarBarang[i] = daftarBarang[i + 1];
            }
            System.out.println("\nData barang berhasil dihapus");
        } else {
            System.out.println("\nData barang tidak ada");
        }
    }
}
