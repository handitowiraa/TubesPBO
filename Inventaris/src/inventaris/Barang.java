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
public class Barang {

    private int idBarang;
    private String namaBarang;
    private int jumlah;
    private int kondisiBaik;
    private int kondisiBuruk;
    private int idLama;

    public Barang(int id, String nama, int jumlah) {
        idBarang = id;
        namaBarang = nama;
        this.jumlah = jumlah;
        kondisiBaik = jumlah;
        kondisiBuruk = 0;
        this.idLama = id;
    }

    public void setID(int id) {
        idBarang = id;
    }
    
    public void setIDLama(int id) {
        idLama = id;
    }
    
    public int getIDLama() {
        return idLama;
    }

    public void setKondisiBaik(int baik) {
        kondisiBaik = baik;
    }
    
    public void setKondisiBuruk() {
        kondisiBuruk = jumlah - kondisiBaik;
    }

    public int getID() {
        return idBarang;
    }

    public String getNama() {
        return namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public int getKondisiBaik() {
        return kondisiBaik;
    }
    
    public int getKondisiBuruk() {
        return kondisiBuruk;
    }

    public void view1() {
        System.out.println("ID\t\t: " + idBarang);
        System.out.println("Nama\t\t: " + namaBarang);
        System.out.println("Jumlah\t\t: " + jumlah);
    }

    public void view2() {
        System.out.println("ID\t\t: " + idBarang);
        System.out.println("Nama\t\t: " + namaBarang);
        System.out.println("Jumlah\t\t: " + jumlah);
        System.out.println("Jumlah Baik\t: " + kondisiBaik);
        System.out.println("Jumlah Buruk\t: " + kondisiBuruk);
    }

    public void updateJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void updateKondisi(int baik) {
        this.kondisiBaik = baik;
        kondisiBuruk = jumlah - kondisiBaik;
    }
    
    public String outString(){
        return "ID."+idBarang+" "+namaBarang;
    }
    
}
