/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewConsole;

import inventaris.Aplikasi;
import inventaris.Penyedia;
import inventaris.Petugas;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Emp. Elesar II
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
                        if (user.equals("admin")&&(pass.equals("admin")))
                            model.menuAdmin();
                        else
                            if(model.login(user,pass) instanceof Petugas)
                                model.menuPetugas((Petugas) model.login(user,pass));
                            else if(model.login(user,pass) instanceof Penyedia)
                                model.menuPenyedia((Penyedia) model.login(user,pass));
                            else if(model.login(user,pass) == null)
                                System.out.println("Login gagal");
                        break;
                    }
                }
            } catch(Exception e) {
                System.out.println("Error : " + e.getMessage());
            } finally {
                sInt = new Scanner(System.in);
                sStr = new Scanner(System.in);
            }
        }
    }
}
