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
    private String kondisi;

    public Barang(int id, String nama, int jumlah) {
        idBarang = id;
        namaBarang = nama;
        this.jumlah = jumlah;
    }

    public void setID(int id) {
        idBarang = id;
    }

    public void setKondisi(String k) {
        kondisi = k;
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

    public String getKondisi() {
        return kondisi;
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
        System.out.println("Kondisi\t\t: " + kondisi);
    }

    public void updateJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void updateKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    @Override
    public String toString() {
        return "Id Barang : " + idBarang + "Nama Barang : " + namaBarang + "Jumlah : " + jumlah + "Kondisi : " + kondisi;
    }
}
