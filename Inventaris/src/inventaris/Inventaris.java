/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris;

/**
 *
 * @author YouuKey
 */
public class Inventaris {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Penyedia p2 = new Penyedia("Budi", "boedy", "B03D4");

        //Petugas membuat barang baru
        p2.createBarang(0, "Meja", 10);
        p2.createBarang(1, "Kursi", 20);
        p2.createBarang(2, "Proyektor", 30);
        p2.view();
        
        p2.ubahBarang(2,15);
        p2.view();
//inisialisasi gudang baru
        Gudang g1 = new Gudang();

        //inisialisasi petugas baru
        Petugas p1 = new Petugas("Sansan", "sansan", "s4ns4n");

        //petugas menambah barang ke gudang
        p1.tambahBarang(g1, p2.getBarang(0), "baik");
        p1.tambahBarang(g1, p2.getBarang(1), "baik");
        p1.tambahBarang(g1, p2.getBarang(2), "baik");

        p2.ubahBarang(2,15);
        //cek isi gudang
        g1.view();
    }
}