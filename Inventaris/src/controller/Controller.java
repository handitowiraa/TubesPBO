/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import inventaris.Aplikasi;
import inventaris.Barang;
import inventaris.Orang;
import inventaris.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Emp. Elesar II
 */
public class Controller extends MouseAdapter implements ActionListener {
    private Aplikasi model;
    private PanelContainer view;

    private String currentView;
    private JPanel mainPanel;
    
    private Login l;
    private Penyedia py;
    private Petugas pt;
    private Admin ad;
    
    private inventaris.Penyedia p1;
    private inventaris.Petugas p2;
    private inventaris.Barang b;
    private inventaris.Gudang g;
    
    private int id_br_seleksi = -1;
    private int id_or_seleksi = -1;
    private int id_gd_seleksi = -1;
    private ArrayList<Barang> temp = new ArrayList();
    
    public Controller(Aplikasi model) {
        this.model = model;
        //model.readAllGudang();
        //model.readAllOrang();
        this.view = new PanelContainer();
        
        l = new Login();
        py = new Penyedia();
        pt = new Petugas();
        ad = new Admin();
        
        l.addListener(this);
        py.addListener(this);
        py.addAdapter(this);
        pt.addListener(this);
        pt.addAdapter(this);
        ad.addListener(this);
        ad.addAdapter(this);
        
        mainPanel = view.getMainPanel();
        mainPanel.add(l, "0");
        mainPanel.add(py, "1");
        mainPanel.add(pt, "2");
        mainPanel.add(ad, "3");
        currentView = "0";

        view.getCardLayout()
                .show(mainPanel, currentView);
        view.setVisible(true);
        view.hideLogOutButton();
        view.addListener(this);
    }
    
    public int findInTemp(int id){
        for (Barang b : temp) {
            if (b.getID() == id) {
                return temp.indexOf(b);
            }
        }
        return -1;
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.closeButtonPressed())) {
            System.exit(0);
        } else if (source.equals(view.logOutButtonPressed())) {
            currentView = "0";
            view.getCardLayout().show(mainPanel, currentView);
            view.hideLogOutButton();
            id_br_seleksi = -1;
            id_or_seleksi = -1;
            id_gd_seleksi = -1;
            pt.setComboPenyediaMasukNull(model.getIDPenyedia());
            pt.setComboGudangMasukNull(model.getIDGudang());
            pt.setComboGudangCariNull(model.getIDGudang());
            pt.setComboGudangKelolaNull(model.getIDGudang());
            pt.setComboGudangViewNull(model.getIDGudang());
        }
        //------------------------------------------------MENU LOGIN-------------------------------------------------//
        if (currentView.equals("0")) {
            if (source.equals(l.masukButtonPressed())) {
                String user = l.getUsername();
                String pass = l.getPassword();
                l.refresh();
                if (user.equals("")||pass.equals("")){
                    JOptionPane.showMessageDialog(null, "Username atau Password tidak boleh kosong", "Peringatan", JOptionPane.ERROR_MESSAGE);
                } else if (user.equals("admin")&&pass.equals("admin")){
                    currentView = "3";
                    view.getCardLayout().show(mainPanel, currentView);
                    ad.setListBarang(model.getListOrang());
                    ad.setListGudang(model.getListGudang());
                    view.showLogOutButton();
                }
                else { 
                    if (model.login(user,pass) instanceof inventaris.Petugas){
                        p2 = (inventaris.Petugas) model.login(user,pass);
                        currentView = "2";
                        view.getCardLayout().show(mainPanel, currentView);
                        view.showLogOutButton();
                        pt.setComboPenyediaMasuk(model.getIDPenyedia());
                        pt.setComboGudangMasuk(model.getIDGudang());
                        pt.setComboGudangCari(model.getIDGudang());
                        pt.setComboGudangKelola(model.getIDGudang());
                        pt.setComboGudangView(model.getIDGudang());
                        pt.setListKelolaBarangNull();
                        pt.setListCariHasilNull();
                        pt.setListViewNull();
                    } else if (model.login(user,pass) instanceof inventaris.Penyedia){
                        p1 = (inventaris.Penyedia) model.login(user,pass);
                        currentView = "1";
                        view.getCardLayout().show(mainPanel, currentView);
                        view.showLogOutButton();
                        py.setListBarang(p1.getListBarang());
                    } else if (model.login(user,pass) == null){
                        JOptionPane.showMessageDialog(null, "Username atau Password anda salah", "Peringatan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        //----------------------------------------------MENU PENYEDIA------------------------------------------------//
        else if (currentView.equals("1")) {
            boolean a = py.getStatusNama();
            if (source.equals(py.simpanButtonPressed())){
                py.activateNama();
                py.activateID();
                if (py.getID()<=0||py.getNama().equals("")) {
                    JOptionPane.showMessageDialog(null, "ID dan Nama tidak boleh kosong", "Peringatan", JOptionPane.ERROR_MESSAGE);
                } else if (py.getJumlah() < 0){
                    JOptionPane.showMessageDialog(null, "Jumlah tidak boleh negatif", "Peringatan", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (a) {
                        if (p1.findBarang(py.getID())!=-1) 
                            JOptionPane.showMessageDialog(null, "ID barang sudah ada", "Peringatan", JOptionPane.ERROR_MESSAGE);
                        else {
                            model.menuPyTambahBrg(p1,py.getID(),py.getNama(),py.getJumlah());
                            py.setListBarang(p1.getListBarang());
                            py.refresh();
                            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        model.menuPyUbahBrg(p1,py.getID(),py.getJumlah());
                        py.setListBarang(p1.getListBarang());
                        py.refresh();
                        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else if (source.equals(py.editButtonPressed())) {
                b = p1.getBarang(p1.findBarang(id_br_seleksi));
                py.deactivateNama();
                py.deactivateID();
                if (b==null)
                    JOptionPane.showMessageDialog(null, "Pilih Barang terlebih dahulu", "Peringatan", JOptionPane.ERROR_MESSAGE);
                else {
                    py.refreshEdit(b);
                }
            } else if (source.equals(py.deleteButtonPressed())) {
                b = p1.getBarang(p1.findBarang(id_br_seleksi));
                if (b==null)
                    JOptionPane.showMessageDialog(null, "Pilih Barang terlebih dahulu", "Peringatan", JOptionPane.ERROR_MESSAGE);
                else{
                    int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION){
                        model.menuPyDeleteBrg(p1,id_br_seleksi);
                        py.setListBarang(p1.getListBarang());
                        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        id_br_seleksi = -1;
                    }
                }
            } 
        }
        //----------------------------------------------MENU PETUGAS-------------------------------------------------//
        else if (currentView.equals("2")) {
            //========================================INPUT KE GUDANG================================================//
            if (source.equals(pt.getBtnMasukCari())){
                p1 = model.getPenyedia(pt.getMasukPenyedia());
                g = model.getGudang(pt.getMasukIDGudang());
                pt.setListBarangPegawai(p1.getListDataBarang());
            } else if (source.equals(pt.getBtnMasukTambah())) {
                if (id_br_seleksi != -1){
                    int newId = Integer.parseInt(JOptionPane.showInputDialog(null, "ID Baru untuk Barang:"));
                    if (g.findBarang(newId)!=-1||findInTemp(newId)!=-1){
                        JOptionPane.showMessageDialog(null, "ID  Barang sudah ada", "Peringatan", JOptionPane.ERROR_MESSAGE);
                    } else if (newId <= 0){
                        JOptionPane.showMessageDialog(null, "ID  Barang tidak valid", "Peringatan", JOptionPane.ERROR_MESSAGE);
                    } else {
                        b = p1.getBarang(id_br_seleksi);
                        p1.hapusBarang(b.getID());
                        b.setIDLama(b.getID());
                        b.setID(newId);
                        temp.add(b);
                        pt.setListBarangPegawai(p1.getListDataBarang());
                        String[] a = new String[temp.size()]; int i = 0;
                        for (Barang b : temp){
                            a[i] = b.outString();
                            i++;
                        }
                        pt.setListBarangGudang(a);
                        id_br_seleksi = -1;
                    }
                } else 
                    JOptionPane.showMessageDialog(null, "Anda belum memilih data", "Peringatan", JOptionPane.ERROR_MESSAGE);
            } else if (source.equals(pt.getBtnMasukHapus())) {
                if (id_br_seleksi != -1){
                    Barang b = temp.get(id_br_seleksi);
                    temp.remove(id_br_seleksi);
                    b.setID(b.getIDLama());
                    p1.createBarang(b.getID(),b.getNama(),b.getJumlah());
                    pt.setListBarangPegawai(p1.getListDataBarang());
                    String[] a = new String[temp.size()]; int i = 0;
                    for (Barang t : temp){
                        a[i] = t.outString();
                        i++;
                    }
                    pt.setListBarangGudang(a);
                    id_br_seleksi = -1;
                } else 
                    JOptionPane.showMessageDialog(null, "Anda belum memilih data", "Peringatan", JOptionPane.ERROR_MESSAGE);
            } else if (source.equals(pt.getBtnMasukSimpan())) {
                if (temp.size() >= 0){
                    for (Barang b:temp){
                        //p1.deleteBarang(b.getIDLama());
                        model.menuPtInputBrg(g,b);
                    }
                    temp = new ArrayList<Barang>();
                    id_br_seleksi = -1;
                    pt.RefreshInput();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                } else 
                JOptionPane.showMessageDialog(null, "Tidak ada perubahan", "Simpan Data", JOptionPane.INFORMATION_MESSAGE);
            } 
            //==========================================KELOLA GUDANG================================================//
            else if (source.equals(pt.getBtnKelolaCari())){
                g = model.getGudang(pt.getKelolaIDGudang());
                pt.setListKelolaBarang(g.getListBarang());
                id_br_seleksi = -1;
            } else if (source.equals(pt.getBtnKelolaEdit())){
                if (id_br_seleksi != -1){
                    Barang b = g.loadBarang(id_br_seleksi);
                    pt.refreshEditKelola(b);
                } else 
                    JOptionPane.showMessageDialog(null, "Anda belum memilih data", "Peringatan", JOptionPane.ERROR_MESSAGE);
            } else if (source.equals(pt.getBtnKelolaSimpan())){
                if (id_br_seleksi != -1){
                    model.menuPtEditBrg(p2,g,pt.getIdBarangEdit(),pt.getJumlahBarangEdit(),pt.getKondisiBarangEdit());
                    pt.setListKelolaBarang(g.getListBarang());
                    pt.refreshKelola();
                    JOptionPane.showMessageDialog(null, "Data berhasil di-update", "Ubah Data", JOptionPane.INFORMATION_MESSAGE);
                } else if(pt.getJumlahBarangEdit() < 0){
                    JOptionPane.showMessageDialog(null, "Jumlah tidak boleh negatif", "Peringatan", JOptionPane.ERROR_MESSAGE);
                } else 
                    JOptionPane.showMessageDialog(null, "Tidak Ada Perubahan", "Ubah Data", JOptionPane.INFORMATION_MESSAGE);
                id_br_seleksi = -1;
            } else if (source.equals(pt.getBtnKelolaHapus())){
                if (id_br_seleksi != -1){
                    int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION){
                        model.menuPtDeleteBrg(p2,g,g.loadBarang(id_br_seleksi).getID());
                        pt.setListKelolaBarang(g.getListBarang());
                        id_br_seleksi = -1;
                        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else 
                    JOptionPane.showMessageDialog(null, "Anda belum memilih data", "Peringatan", JOptionPane.ERROR_MESSAGE);
            }
            //==================================================CARI GUDANG===================================================//
            else if (source.equals(pt.getBtnCariCari())){
                if(pt.getTxtCari()==null){
                    JOptionPane.showMessageDialog(null, "Anda belum menginput data", "Peringatan", JOptionPane.ERROR_MESSAGE);
                } else {
                    g = model.getGudang(pt.getCariIDGudang());
                    if (pt.getCariKategori().equals("Id Barang")){
                        int id = 0;
                        try {
                            id = Integer.parseInt(pt.getTxtCari());
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Input Harus Berupa Angka", "Peringatan", JOptionPane.ERROR_MESSAGE);
                        }
                        ArrayList<Barang> t = new ArrayList<>();
                        Barang b = g.getBarang(id);
                        if (b != null)
                            t.add(b);
                        if (t.size() > 0) 
                            pt.setListCariHasil(t);
                        else 
                            JOptionPane.showMessageDialog(null, "Data Tidak Ada", "Pencarian", JOptionPane.INFORMATION_MESSAGE);
                    } else if (pt.getCariKategori().equals("Nama Barang")){
                        String nama = pt.getTxtCari();
                        ArrayList<Barang> t = g.cariNama(nama);
                        if (t.size() > 0) 
                            pt.setListCariHasil(t);
                        else 
                            JOptionPane.showMessageDialog(null, "Data Tidak Ada", "Pencarian", JOptionPane.INFORMATION_MESSAGE);
                    } else if (pt.getCariKategori().equals("Kondisi Barang")){
                        String kondisi = pt.getTxtCari();
                        ArrayList<Barang> t = g.cariKondisi(kondisi);
                        if (t.size() > 0) 
                            pt.setListCariHasil(t);
                        else 
                            JOptionPane.showMessageDialog(null, "Data Tidak Ada", "Pencarian", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            //==================================================VIEW GUDANG===================================================//
            else if (source.equals(pt.getBtnCariView())){
                g = model.getGudang(pt.getViewIDGudang());
                if (g.getListBarang() != null)
                    pt.setListView(g.getListBarang());
                else 
                    JOptionPane.showMessageDialog(null, "Tidak Ada Data", "Pencarian", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //------------------------------------------------MENU ADMIN-------------------------------------------------//
        else if (currentView.equals("3")) {
            //==========================================KELOLA PENGGUNA==============================================//
            if(source.equals(ad.simpanButtonPressed())) {
                boolean baru = ad.getIDStatus();
                if ((ad.getIDPenggunaKelola() <= 0)||(ad.getNamaAsliKelola()==null)||(ad.getUsernameKelola()==null)||(ad.getPasswordKelola()==null)){
                    JOptionPane.showMessageDialog(null, "Data tidak boleh kosong", "Peringatan", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (baru) {
                        if (ad.getJenisPengguna().equals("Petugas")){
                            if (model.getPetugas(ad.getIDPenggunaKelola()) == null&&model.getUserPetugas(ad.getUsernameKelola())==null){
                                model.addPetugas(ad.getIDPenggunaKelola(),ad.getNamaAsliKelola(),ad.getUsernameKelola(),ad.getPasswordKelola());
                                //model.savePetugas(ad.getIDPenggunaKelola(),ad.getNamaAsliKelola(),ad.getUsernameKelola(),ad.getPasswordKelola());
                                ad.setListBarang(model.getListOrang());
                                ad.refresh();
                                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "ID atau Username sudan dipakai", "Peringatan", JOptionPane.ERROR_MESSAGE);
                            }
                        } else if (ad.getJenisPengguna().equals("Penyedia")){
                            if (model.getPenyedia(ad.getIDPenggunaKelola()) == null&&model.getUserPenyedia(ad.getUsernameKelola())==null){
                                model.addPenyedia(ad.getIDPenggunaKelola(),ad.getNamaAsliKelola(),ad.getUsernameKelola(),ad.getPasswordKelola());
                                //model.savePenyedia(ad.getIDPenggunaKelola(),ad.getNamaAsliKelola(),ad.getUsernameKelola(),ad.getPasswordKelola());
                                ad.setListBarang(model.getListOrang());
                                ad.refresh();
                                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "ID atau Username sudan dipakai", "Peringatan", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        if (ad.getJenisPengguna().equals("Penyedia")){
                            if (model.getUserPenyedia(ad.getUsernameKelola())==null) {
                                model.ubahPenyedia(ad.getIDPenggunaKelola(),ad.getNamaAsliKelola(),ad.getUsernameKelola(),ad.getPasswordKelola());
                                //model.updatePenyedia(ad.getIDPenggunaKelola(),ad.getNamaAsliKelola(),ad.getUsernameKelola(),ad.getPasswordKelola());
                                ad.setListBarang(model.getListOrang());
                                ad.refresh();
                                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Username sudan dipakai", "Peringatan", JOptionPane.ERROR_MESSAGE);
                            }
                        } else if (ad.getJenisPengguna().equals("Petugas")){
                            if (model.getUserPetugas(ad.getUsernameKelola())==null) {
                                model.ubahPetugas(ad.getIDPenggunaKelola(),ad.getNamaAsliKelola(),ad.getUsernameKelola(),ad.getPasswordKelola());
                                model.updatePetugas(ad.getIDPenggunaKelola(),ad.getNamaAsliKelola(),ad.getUsernameKelola(),ad.getPasswordKelola());
                                ad.setListBarang(model.getListOrang());
                                ad.refresh();
                                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Username sudan dipakai", "Peringatan", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
                id_or_seleksi = -1;
                ad.activateID();
                ad.activateJenis();
            } else if(source.equals(ad.editButtonPressed())) {
                if (id_or_seleksi == -1){
                    JOptionPane.showMessageDialog(null, "Anda belum menginput data", "Peringatan", JOptionPane.ERROR_MESSAGE);
                } else if (id_or_seleksi != -1){
                    ad.refreshEdit(model.getOrang(id_or_seleksi));
                }
                ad.deactivateJenis();
                ad.deactivateID();
                id_or_seleksi = -1;
            } else if(source.equals(ad.deleteButtonPressed())) {
                Orang o = model.getOrang(id_or_seleksi);
                if (o==null)
                    JOptionPane.showMessageDialog(null, "Pilih Barang terlebih dahulu", "Peringatan", JOptionPane.ERROR_MESSAGE);
                else{
                    int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION){
                        if (o instanceof inventaris.Penyedia){
                            model.deletePenyedia((int) ((inventaris.Penyedia) o).getID());
                            //model.delete2Penyedia((int) ((inventaris.Penyedia) o).getID());
                        } else if (o instanceof inventaris.Petugas){
                            model.deletePetugas((int) ((inventaris.Petugas) o).getIdPetugas());
                            //model.delete2Petugas((int) ((inventaris.Petugas) o).getIdPetugas());
                        }
                        ad.setListBarang(model.getListOrang());
                        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        id_or_seleksi = -1;
                    }
                }
            }
            //==========================================KELOLA GUDANG 2==============================================//
            //by admnin
            else if(source.equals(ad.simpanBtnPressed())) {
                boolean baru = ad.getIDGudangStatus();
                if ((ad.getIDGudang() <= 0)||(ad.getNamaGudangKelola()==null)){
                    JOptionPane.showMessageDialog(null, "Data tidak boleh kosong", "Peringatan", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (baru) {
                        if (model.getGudang(ad.getIDGudang())==null) {
                            model.tambahGudang(ad.getIDGudang(),ad.getNamaGudangKelola());
                            //model.saveGudang(ad.getIDGudang(),ad.getNamaGudangKelola());
                            ad.setListGudang(model.getListGudang());
                            ad.refreshGudang();
                            ad.setListGudang(model.getListGudang());
                            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "ID Gudang sudan dipakai", "Peringatan", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        model.ubahGudang(ad.getIDGudang(),ad.getNamaGudangKelola());
                        model.updateGudang(ad.getIDGudang(),ad.getNamaGudangKelola());
                        ad.refreshGudang();
                        ad.setListGudang(model.getListGudang());
                        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                id_gd_seleksi = -1;
                ad.activateIDGudang();
            } else if(source.equals(ad.editBtnPressed())) {
                if (id_gd_seleksi == -1){
                    JOptionPane.showMessageDialog(null, "Anda belum menginput data", "Peringatan", JOptionPane.ERROR_MESSAGE);
                } else if (id_gd_seleksi != -1){
                    ad.refreshEditGudang(model.ambilGudang(id_gd_seleksi));
                    System.out.println(id_gd_seleksi);
                }
                ad.deactivateIDGudang();
                id_or_seleksi = -1;
            } else if(source.equals(ad.deleteBtnPressed())) {
                inventaris.Gudang o = model.ambilGudang(id_gd_seleksi);
                if (o==null)
                    JOptionPane.showMessageDialog(null, "Pilih Barang terlebih dahulu", "Peringatan", JOptionPane.ERROR_MESSAGE);
                else{
                    int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION){
                        model.deleteGudang(o.getID());
                        //model.delete2Gudang(o.getID());
                        ad.setListGudang(model.getListGudang());
                        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        id_gd_seleksi = -1;
                    }
                }
            }
        }
    }
    
    public void mousePressed(MouseEvent e){
        Object source = e.getSource();
        if (currentView.equals("1")) {
            int selectedId = py.getSelectedID();
            ArrayList<Barang> a = p1.getListBarang();
            id_br_seleksi = a.get(selectedId).getID();
        } else if (currentView.equals("2")) {
            if (source.equals(pt.getListBarangPenyedia())){
                id_br_seleksi = pt.getSelectedNamaBarangPenyedia();
            } else if (source.equals(pt.getListBarangGudang())){
                id_br_seleksi = pt.getSelectedNamaBarangGudang();
            } else if (source.equals(pt.listKelolaBarangSelected())){
                id_br_seleksi = pt.getSelectedIDKelola();
            }
        } else if (currentView.equals("3")) {
            if (source.equals(ad.tabelOrangSelected())){
                id_or_seleksi = ad.getSelectedOrang();
            } else if (source.equals(ad.tabelGudangSelected())){
                id_gd_seleksi = ad.getSelectedGudang();
            }
        } 
    }
}
