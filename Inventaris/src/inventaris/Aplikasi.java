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
import java.util.Scanner;

public class Aplikasi{
    private Orang[] daftarOrang;
    private Gudang[] daftarGudang;
    int jumOrang = 0;
    int jumGudang = 0;
	
    public Aplikasi(){
	daftarOrang = new Orang[100];
	daftarGudang = new Gudang[10];
    }
	
    public void addPenyedia(long id, String nama, String username, String password){
	if (jumOrang < 100){
            if (getPenyedia(id) == null){
		if (!(cariUsername(username))){
                    daftarOrang[jumOrang] = new Penyedia(id,nama,username,password);
                    jumOrang++;
		} else System.out.println("Username sudah digunakan");
            } else System.out.println("ID sudah digunakan");
	}
    }
	
    public void addPetugas(long id, String nama, String username, String password){
	if (jumOrang < 100){
            if (getPetugas(id) == null){
		if (!(cariUsername(username))){
                    daftarOrang[jumOrang] = new Petugas(id,nama,username,password);
                    jumOrang++;
		} else System.out.println("Username sudah digunakan");
            } else System.out.println("ID sudah digunakan");
	}
    }
	
    public Petugas getPetugas(long id){
        for (int i = 0; i < jumOrang; i++){
            if (daftarOrang[i] instanceof Petugas){
                Petugas pt = (Petugas) daftarOrang[i];
                if (pt.getIdPetugas() == id)
                    return pt;
            }
        }
        return null;
    }
	
    public Penyedia getPenyedia(long id){
        for (int i = 0; i < jumOrang; i++){
            if (daftarOrang[i] instanceof Penyedia){
                Penyedia py = (Penyedia) daftarOrang[i];
                if (py.getID() == id)
                    return py;
            }
        }
        return null;
    }
	
    public void deletePenyedia(int id){
        int j = -1;
        for (int i = 0;i < jumOrang;i++){
            if (daftarOrang[i] instanceof Penyedia){
                Penyedia py = (Penyedia) daftarOrang[i];
                if (py.getID() == id){
                    j = i; break;
                }
            }
        }
        if (j != -1){
            for (int i = j;i < jumOrang-1;j++){
                daftarOrang[i] = daftarOrang[i+1];
            }
        }
    }
	
    public void deletePetugas(int id){
        int j = -1;
        for (int i = 0;i < jumOrang;i++){
            if (daftarOrang[i] instanceof Petugas){
                Petugas pt = (Petugas) daftarOrang[i];
                if (pt.getIdPetugas() == id){
                    j = i; break;
                }
            }
        }
        if (j != -1){
            for (int i = j;i < jumOrang-1;j++){
                daftarOrang[i] = daftarOrang[i+1];
            }
        }
    }
	
    public void addGudang(int id){
        if (jumGudang < 10){
            daftarGudang[jumGudang] = new Gudang(id);
            jumGudang++;
        }
    }
	
    public Gudang getGudang(int id){
        for (int i = 0;i < jumGudang;i++){
            if (daftarGudang[i].getID() == id)
                return daftarGudang[i];
        }
        return null;
    }
	
    public void deleteGudang(int id){
        int j = -1;
        for (int i = 0;i < jumGudang;i++){
            if (daftarGudang[i].getID() == id){
                j = i; break;
            }
        }
        if (j != -1){
            for (int i = j;i < jumGudang-1;i++){
                daftarGudang[i] = daftarGudang[i+1];
            }
        }
    }
	
    private boolean cariUsername(String user){
        for(int i = 0;i < jumOrang;i++){
            if (daftarOrang[i].getUsername().equals(user))
                return true;
        }
        return false;
    }
	
    public void menuPyTambahBrg(Penyedia py, int id, String nama, int jumlah){
        py.createBarang(id,nama,jumlah);
    }
	
    public void menuPyUbahBrg(Penyedia py, int id, int jumlah){
        int j = py.findBarang(id);
        if (j != -1){
            py.ubahBarang(j,jumlah);
            System.out.println("Data barang telah diubah");
        } else System.out.println("Data barang tidak ada");
    }

    public void menuPyDeleteBrg(Penyedia py, int id){
        int j = py.findBarang(id);
        if (j != -1){
            py.deleteBarang(j);
            System.out.println("Data barang telah dihapus");
        } else System.out.println("Data barang tidak ada");
    }
	
    public void menuPyView(Penyedia py){
        py.view();
    }
	
    public void menuPtTambahBrg(Petugas pt, Penyedia py, Gudang g, int id){
        if(py.findBarang(id) != -1){
            String k = "Baik";
            pt.tambahBarang(g,py.getBarang(py.findBarang(id)),k);
        } else System.out.println("Barang tidak ada");
    }
	
    public void menuPtEditBrg(Petugas pt, Gudang g, int id, int jum, String kondisi){
        int i = g.findBarang(id);
        if (i != -1){
            pt.ubahBarang(g,i,jum,kondisi);
            System.out.println("Data sudah di-update");
        } else System.out.println("Data barang tidak ada");
    }
	
    public void menuPtDeleteBrg(Petugas pt, Gudang g, int id){
        pt.hapusBarang(g,id);
    }
	
    public void menuPtViewGudang(int id){
        Gudang g = getGudang(id);
        if (g != null){
                g.view();
        } else System.out.println("Gudang tidak ada");
    }
	
    public void menuPenyedia(Penyedia py){
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        int pil = 0;
        while(pil != 5){
            System.out.println("\nMENU PENYEDIA\n1. Tambah Barang\n2. Edit Data Barang\n3. Hapus Barang");
            System.out.print("4. View Barang\n5. Keluar\nPilihan\t: ");
            pil = s1.nextInt();
            switch (pil){
                case 1:{
                    System.out.print("ID Barang\t: ");
                    int id = s1.nextInt();
                    System.out.print("Nama Barang\t: ");
                    String nama = s2.nextLine();
                    System.out.print("Jumlah\t\t: ");
                    int jum = s1.nextInt();
                    menuPyTambahBrg(py,id,nama,jum); break;
                }
                case 2:{
                    System.out.print("ID barang diubah\t: ");
                    int id = s1.nextInt();
                    System.out.print("Jumlah\t\t: ");
                    int jum = s1.nextInt();
                    menuPyUbahBrg(py,id,jum); break;
                }
                case 3:{
                    System.out.print("ID barang dihapus\t: ");
                    int id = s1.nextInt();
                    menuPyDeleteBrg(py,id); break;
                }
                case 4:{
                    menuPyView(py); break;
                }
            }
        }
    }
	
    public void menuPetugas(Petugas pt){
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        int pil = 0;
        while (pil != 5){
            System.out.println("\nMENU PETUGAS\n1. Tambah Barang\n2. Edit Data Barang\n3. Hapus Barang");
            System.out.print("4. View Barang\n5. Keluar\nPilihan\t: ");
            pil = s1.nextInt();
            switch (pil){
                case 1:{
                    System.out.println("Daftar Penyedia");
                    for (int i = 0;i < jumOrang;i++){
                        if (daftarOrang[i] instanceof Penyedia){
                            System.out.println(daftarOrang[i].toString());
                        }
                    }
                    System.out.print("\nID Penyedia\t: ");
                    int id = s1.nextInt();
                    Penyedia py = getPenyedia(id);
                    System.out.println("\nDaftar Barang");
                    for (int i = 0;i < py.jumBarang;i++){
                        py.getBarang(i).view1();
                        System.out.println();
                    }
                    String lagi = "y";
                    do {
                        System.out.print("ID Barang\t: ");
                        id = s1.nextInt();
                        System.out.print("ID Gudang\t: ");
                        int idGudang = s1.nextInt();
                        Gudang g = getGudang(idGudang);
                        menuPtTambahBrg(pt,py,g,id);
                        System.out.print("Lagi? [Y/N] ");
                        lagi = s2.nextLine();
                    } while (lagi.equalsIgnoreCase("y"));
                    break;
                }
                case 2:{
                    System.out.print("ID Gudang\t\t: ");
                    int idGudang = s1.nextInt();
                    Gudang g = getGudang(idGudang);
                    if (g != null){
                        System.out.print("ID barang diubah\t: ");
                        int id = s1.nextInt();
                        System.out.print("Jumlah\t\t: ");
                        int jum = s1.nextInt();
                        System.out.print("Kondisi\t\t: ");
                        String k = s1.nextLine();
                        menuPtEditBrg(pt,g,id,jum,k);
                    } else System.out.println("Gudang tidak ada");
                    break;
                }
                case 3:{
                    System.out.print("ID Gudang\t\t: ");
                    int idGudang = s1.nextInt();
                    Gudang g = getGudang(idGudang);
                    if (g != null){
                        System.out.println("ID barang dihapus\t: ");
                        int id = s1.nextInt();
                        menuPtDeleteBrg(pt,g,id);
                    } else System.out.println("Gudang tidak ada");
                    break;
                }
                case 4:{
                    System.out.print("ID Gudang\t\t: ");
                    int idGudang = s1.nextInt();
                    menuPtViewGudang(idGudang);
                    break;
                }
            }
        }
    }
	
    public void menuUtama(){
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        int pil = 0;
        while (pil != 3){
            System.out.println("\nMENU\n1. Login Admin\n2. Login Pegawai");
            System.out.print("3. Keluar\nPilihan: ");
            pil = s1.nextInt();
            switch (pil){
                case 1:{
                    String user,pass,coba = "Y";
                    while (coba.equalsIgnoreCase("Y")){
                        System.out.print("Username : ");
                        user = s2.nextLine();
                        System.out.print("Password : ");
                        pass = s2.nextLine();
                        if ((user.equals("admin"))&&(pass.equals("admin"))){
                            int pil2 = 0; coba = "N";
                            while (pil2 != 7){							
                                System.out.println("\nMENU ADMIN\n1. Tambah Petugas\n2. Hapus Petugas");
                                System.out.println("3. Tambah Penyedia\n4. Hapus Penyedia\n5. Tambah Gudang");
                                System.out.print("6. Hapus Gudang\n7. Keluar\nPilihan : ");
                                pil2 = s1.nextInt();
                                switch (pil2){
                                    case 1:{
                                        System.out.print("ID Petugas\t: ");
                                        int id = s1.nextInt();
                                        System.out.print("Nama Petugas\t: ");
                                        String nama = s2.nextLine();
                                        System.out.print("Username\t: ");
                                        user = s2.nextLine();
                                        System.out.print("Password\t: ");
                                        pass = s2.nextLine();
                                        addPetugas(id,nama,user,pass);
                                        break;
                                    }
                                    case 2:{
                                        System.out.print("ID Petugas dihapus\t: ");
                                        int id = s1.nextInt();
                                        deletePetugas(id); break;
                                    }
                                    case 3:{
                                        System.out.print("ID Penyedia\t: ");
                                        int id = s1.nextInt();
                                        System.out.print("Nama Penyedia\t: ");
                                        String nama = s2.nextLine();
                                        System.out.print("Username\t: ");
                                        user = s2.nextLine();
                                        System.out.print("Password\t: ");
                                        pass = s2.nextLine();
                                        addPenyedia(id,nama,user,pass);
                                        break;
                                    }
                                    case 4:{
                                        System.out.print("ID Penyedia dihapus\t: ");
                                        int id = s1.nextInt();
                                        deletePenyedia(id); break;
                                    }
                                    case 5:{
                                        System.out.print("ID Gudang\t: ");
                                        int id = s1.nextInt();
                                        addGudang(id); break;
                                    }
                                    case 6:{
                                        System.out.print("ID Gudang dihapus\t: ");
                                        int id = s1.nextInt();
                                        deleteGudang(id); break;
                                    }
                                }
                            }
                        } else {
                            System.out.println("LOGIN GAGAL");
                            System.out.print("Login Lagi?[Y/N] ");
                            coba = s2.nextLine();
                        }
                    } 
                    break;
                }
                case 2:{
                    String user,pass,coba = "Y";
                    while ((coba.equals("Y"))||(coba.equals("y"))){
                        System.out.print("Username : ");
                        user = s2.nextLine();
                        System.out.print("Password : ");
                        pass = s2.nextLine();
                        int i = 0; boolean masuk = false;
                        while (i < jumOrang){
                            if ((daftarOrang[i].getUsername()).equals(user)){
                                if ((daftarOrang[i].getPassword()).equals(pass)){
                                    masuk = true; 
                                    break;
                                }
                            } i++;
                        }
                        if (masuk){
                            if (daftarOrang[i] instanceof Penyedia){
                                Penyedia py = (Penyedia) daftarOrang[i];
                                menuPenyedia(py);
                            } else {
                                Petugas pt = (Petugas) daftarOrang[i];
                                menuPetugas(pt);
                            }
                            coba = "N";
                        } else {
                            System.out.println("LOGIN GAGAL");
                            System.out.print("Login Lagi?[Y/N] ");
                            coba = s2.nextLine();
                        }
                    }
                }
            }
        }
    }
}
