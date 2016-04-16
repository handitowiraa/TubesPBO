/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import controller.Controller;
import inventaris.Aplikasi;
import viewConsole.Console;

/**
 *
 * @author Emp. Elesar II
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Aplikasi app = new Aplikasi();
        //new Controller(app);
        
        //Aplikasi Console
        Console view = new Console(app);
        view.menuUtama();
    }
    
}
