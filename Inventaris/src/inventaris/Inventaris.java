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
        p2.createBarang(1, "Meja", 10);
        p2.createBarang(2, "Kursi", 20);

        System.out.println("Barang di Petugas");
        p2.view();
    }

}
