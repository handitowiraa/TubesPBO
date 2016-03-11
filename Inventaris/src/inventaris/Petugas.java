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
public class Petugas extends Orang {
    
    private long idPetugas;
    
    public Petugas(String nama, String username, String password) {
        super(nama, username, password);
    }

    /**
     * @return the idPetugas
     */
    public long getIdPetugas() {
        return idPetugas;
    }

    /**
     * @param idPetugas the idPetugas to set
     */
    public void setIdPetugas(long idPetugas) {
        this.idPetugas = idPetugas;
    }
    
    public void tambahBarang(Gudang g, Barang b, String kondisi) {
        g.addBarang(b, kondisi);
    }
    
    public void ubahBarang(Gudang g, int n, int jum, String kondisi) {
        g.getBarang(n).updateJumlah(jum);
        g.getBarang(n).updateKondisi(kondisi);
    }
    
    public void hapusBarang(Gudang g, int id) {
        if (g.getBarang(id).getID() == id) {
            g.deleteBarang(id);
        } else{
            System.out.println("\nBarang dengan id tsb tidak ada");
        }
    }
    
}
