/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewConsole;

import inventaris.Aplikasi;
import inventaris.Gudang;
import inventaris.Penyedia;
import inventaris.Petugas;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Kelompok 8
 */
public class Console {

    private Aplikasi model;
    private Scanner sInt;
    private Scanner sStr;

    public Console(Aplikasi model) {
        this.model = model;
        sInt = new Scanner(System.in);
        sStr = new Scanner(System.in);
    }

    public int inputInteger() {
        try {
            return sInt.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Input Harus Berupa Angka");
        } finally {
            sInt = new Scanner(System.in);
        }
    }

    public void menuPetugas(Petugas pt) {
        int pil = 0;
        while (pil != 5) {
            try {
                System.out.println("\nMENU PETUGAS\n1. Tambah Barang\n2. Edit Data Barang\n3. Hapus Barang");
                System.out.print("4. View Barang\n5. Keluar\nPilihan\t: ");
                pil = inputInteger();
                switch (pil) {
                    case 1: {
                        System.out.println("Daftar Penyedia");
                        for (int i = 0; i < model.getListOrang().size(); i++) {
                            if (model.getListOrang().get(i) instanceof Penyedia) {
                                System.out.println(model.getListOrang().get(i).toString());
                            }
                        }
                        System.out.print("\nID Penyedia\t: ");
                        int id = inputInteger();
                        Penyedia py = model.getPenyedia(id);
                        if (py != null) {
                            String lagi = "y";
                            do {
                                System.out.println("\nDaftar Barang");
                                for (int i = 0; i < py.getJumBarang(); i++) {
                                    py.getBarang(i).view1();
                                    System.out.println();
                                }
                                System.out.print("ID Barang\t: ");
                                id = inputInteger();
                                System.out.print("ID Gudang\t: ");
                                int idGudang = inputInteger();
                                if (model.getGudang(idGudang) != null) {
                                    System.out.print("ID Barang Baru\t: ");
                                    int id2 = inputInteger();
                                    Gudang g = model.getGudang(idGudang);
                                    model.menuPtTambahBrg(pt, py, g, id, id2);
                                } else {
                                    System.out.println("Gudang tidak ada");
                                }
                                System.out.print("Lagi? [Y/N] ");
                                lagi = sStr.nextLine();
                            } while (lagi.equalsIgnoreCase("y"));
                        } else {
                            System.out.println("Penyedia tidak ada");
                        }
                        break;
                    }
                    case 2: {
                        System.out.print("ID Gudang\t\t: ");
                        int idGudang = inputInteger();
                        Gudang g = model.getGudang(idGudang);
                        if (g != null) {
                            System.out.print("ID barang diubah\t: ");
                            int id = inputInteger();
                            if (g.findBarang(id) != -1) {
                                System.out.print("Barang Baik\t\t: ");
                                int k = inputInteger();
                                model.menuPtEditBrg(pt, g, id, k);
                            } else {
                                System.out.println("Barang tidak ada");
                            }
                        } else {
                            System.out.println("Gudang tidak ada");
                        }
                        break;
                    }
                    case 3: {
                        System.out.print("ID Gudang\t\t: ");
                        int idGudang = inputInteger();
                        Gudang g = model.getGudang(idGudang);
                        if (g != null) {
                            System.out.print("ID barang dihapus\t: ");
                            int id = inputInteger();
                            if (g.findBarang(id) != -1) {
                                model.menuPtDeleteBrg(pt, g, id);
                            } else {
                                System.out.println("Barang tidak ada");
                            }
                        } else {
                            System.out.println("Gudang tidak ada");
                        }
                        break;
                    }
                    case 4: {
                        System.out.print("ID Gudang\t\t: ");
                        int idGudang = inputInteger();
                        model.menuPtViewGudang(idGudang);
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            } finally {
                sInt = new Scanner(System.in);
                sStr = new Scanner(System.in);
            }
        }
    }

    public void menuPenyedia(Penyedia py) {
        int pil = 0;
        while (pil != 5) {
            try {
                System.out.println("\nMENU PENYEDIA\n1. Tambah Barang\n2. Edit Data Barang\n3. Hapus Barang");
                System.out.print("4. View Barang\n5. Keluar\nPilihan\t: ");
                pil = inputInteger();
                switch (pil) {
                    case 1: {
                        System.out.print("ID Barang\t: ");
                        int id = inputInteger();
                        System.out.print("Nama Barang\t: ");
                        String nama = sStr.nextLine();
                        System.out.print("Jumlah\t\t: ");
                        int jum = inputInteger();
                        model.menuPyTambahBrg(py, id, nama, jum);
                        break;
                    }
                    case 2: {
                        System.out.print("ID barang diubah\t: ");
                        int id = inputInteger();
                        System.out.print("Jumlah\t\t: ");
                        int jum = inputInteger();
                        model.menuPyUbahBrg(py, id, jum);
                        break;
                    }
                    case 3: {
                        System.out.print("ID barang dihapus\t: ");
                        int id = inputInteger();
                        model.menuPyDeleteBrg(py, id);
                        break;
                    }
                    case 4: {
                        model.menuPyView(py);
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            } finally {
                sInt = new Scanner(System.in);
                sStr = new Scanner(System.in);
            }
        }
    }

    public void menuAdmin() {
        int pil2 = 0;
        String coba = "N";
        while (pil2 != 7) {
            try {
                System.out.println("\nMENU ADMIN\n1. Tambah Petugas\n2. Hapus Petugas");
                System.out.println("3. Tambah Penyedia\n4. Hapus Penyedia\n5. Tambah Gudang");
                System.out.print("6. Hapus Gudang\n7. Keluar\nPilihan : ");
                pil2 = inputInteger();
                switch (pil2) {
                    case 1: {
                        System.out.print("ID Petugas\t: ");
                        int id = inputInteger();
                        System.out.print("Nama Petugas\t: ");
                        String nama = sStr.nextLine();
                        System.out.print("Username\t: ");
                        String user = sStr.nextLine();
                        System.out.print("Password\t: ");
                        String pass = sStr.nextLine();
                        if (model.getPetugas(id) == null) {
                            if (model.getUserPenyedia(user) == null && model.getUserPetugas(user) == null) {
                                model.addPetugas(id, nama, user, pass);
                                //savePetugas(id,nama,user,pass);
                            } else {
                                System.out.println("Username sudah ada");
                            }
                        } else {
                            System.out.println("ID sudah digunakan");
                        }
                        break;
                    }
                    case 2: {
                        System.out.print("ID Petugas dihapus\t: ");
                        int id = inputInteger();
                        if (model.getPetugas(id) == null) {
                            System.out.println("Data tidak ada");
                        } else {
                            model.deletePetugas(id);
                            //delete2Petugas(id);
                            System.out.println("Data berhasil dihapus");
                        }
                        break;
                    }
                    case 3: {
                        System.out.print("ID Penyedia\t: ");
                        int id = inputInteger();
                        System.out.print("Nama Penyedia\t: ");
                        String nama = sStr.nextLine();
                        System.out.print("Username\t: ");
                        String user = sStr.nextLine();
                        System.out.print("Password\t: ");
                        String pass = sStr.nextLine();
                        if (model.getPenyedia(id) == null) {
                            if (model.getUserPenyedia(user) == null && model.getUserPetugas(user) == null) {
                                model.addPenyedia(id, nama, user, pass);
                                //savePenyedia(id,nama,user,pass);
                            } else {
                                System.out.println("Username sudah ada");
                            }
                        } else {
                            System.out.println("ID sudah digunakan");
                        }
                        break;
                    }
                    case 4: {
                        System.out.print("ID Penyedia dihapus\t: ");
                        int id = inputInteger();
                        if (model.getPenyedia(id) == null) {
                            System.out.println("Data tidak ada");
                        } else {
                            model.deletePenyedia(id);
                            //delete2Penyedia(id);
                            System.out.println("Data berhasil dihapus");
                        }
                        break;
                    }
                    case 5: {
                        System.out.print("ID Gudang\t: ");
                        int id = inputInteger();
                        if (model.getGudang(id) != null) {
                            System.out.println("ID sudah digunakan");
                        } else {
                            model.addGudang(id);
                            //saveGudang(id);
                        }
                        break;
                    }
                    case 6: {
                        System.out.print("ID Gudang dihapus\t: ");
                        int id = inputInteger();
                        if (model.getGudang(id) == null) {
                            System.out.println("Data tidak ada");
                        } else {
                            model.deleteGudang(id);
                            //delete2Gudang(id);
                            System.out.println("Data berhasil dihapus");
                        }
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            } finally {
                sInt = new Scanner(System.in);
                sStr = new Scanner(System.in);
            }
        }
    }

    public void menuUtama() {
        int pil = 0;
        //readAllOrang();
        //readAllGudang();          
        while (pil != 2) {
            try {
                System.out.println("\nMENU\n1. Login");
                System.out.print("2. Keluar\nPilihan: ");
                pil = inputInteger();
                switch (pil) {
                    case 1: {
                        String user, pass;
                        System.out.print("Username : ");
                        user = sStr.nextLine();
                        System.out.print("Password : ");
                        pass = sStr.nextLine();
                        if (user.equals("admin") && (pass.equals("admin"))) {
                            menuAdmin();
                        } else {
                            if (model.login(user, pass) instanceof Petugas) {
                                menuPetugas((Petugas) model.login(user, pass));
                            } else if (model.login(user, pass) instanceof Penyedia) {
                                menuPenyedia((Penyedia) model.login(user, pass));
                            } else if (model.login(user, pass) == null) {
                                System.out.println("Login gagal");
                            }
                        }
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            } finally {
                sInt = new Scanner(System.in);
                sStr = new Scanner(System.in);
            }
        }
    }
}
