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
public abstract class Orang{
	private String nama;
	private String username;
	private String password;
	
	public Orang(String nama,String username,String password){
		this.nama = nama;
		this.username = username;
		this.password = password;
	}
	public void setNama(String nama){
		this.nama = nama;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getNama(){
		return nama;
	}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
}