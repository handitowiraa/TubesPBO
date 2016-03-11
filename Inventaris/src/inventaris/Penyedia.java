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
public class Penyedia extends Orang{
	private Barang[] daftarBarang;
	private long id_penyedia;
	protected int jumBarang = 0;
	
	public Penyedia(){
		daftarBarang = new Barang[100];
	}
	public void setID(long id){
		id_penyedia = id;
	}
	public long getID(){
		return id_penyedia;
	}
	public Barang getBarang(int n){
		return daftarBarang[n];
	}
	public void createBarang(int id, String nama, int jumlah){
		if (jumBarang < daftarBarang.length){
			if (findBarang(id) == -1){
				daftarBarang[jumBarang] = new Barang(id,nama,jumlah);
				jumBarang++;
			} else System.out.println("Maaf, kode barang sudah ada");
		}
	}
	public int findBarang(int id){
		boolean x = false; int j = 0;
		for (int i = 0;i < jumBarang;i++){
			if(daftarBarang[i].getID() == id){
				x = true; 
				j = i;
			}
		}
		if (x) return j;
		else return -1;
	}
	public void view(){
		for (int i = 0;i < jumBarang;i++){
			daftarBarang[i].view1();
			System.out.println();
		}
	}
	public void deleteBarang(int j){
		for (int i = j;i < jumBarang-1;i++){
			daftarBarang[i] = daftarBarang[i+1];
		}
		jumBarang--;
	}
        @Override
	public String toString(){
		return "ID\t: "+id_penyedia+"\nNama\t: "+super.getNama();
	}
}