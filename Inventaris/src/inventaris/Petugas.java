/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, ch oose Tools | Templates
 * and open the template in the editor.
 */
package inventaris;

/**
 *
 * @author Handito
 */
public class Petugas extends Orang {

    private int idPetugas;

    public Petugas(int id, String nama, String username, String password) {
        super(nama, username, password);
        idPetugas = id;
    }

    /**
     * @return the idPetugas
     */
    public int getIdPetugas() {
        return idPetugas;
    }

    /**
     * @param idPetugas the idPetugas to set
     */
    public void setIdPetugas(int idPetugas) {
        this.idPetugas = idPetugas;
    }

    public void tambahBarang(Gudang g, Barang b, String kondisi,int id2) {
        g.addBarang(b, kondisi,id2);
    }

    public void ubahBarang(Gudang g, int n, int jum, String kondisi) {
        g.getBarang(n).updateJumlah(jum);
        g.getBarang(n).updateKondisi(kondisi);
    }

    public void hapusBarang(Gudang g, int id) {
        if (g.getBarang(id).getID() == id) {
            g.deleteBarang(id);
        } else {
            System.out.println("\nBarang dengan id tsb tidak ada");
        }
    }

}
