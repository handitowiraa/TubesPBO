/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris.view;

import inventaris.Barang;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Emp. Elesar II
 */
public class Penyedia extends javax.swing.JPanel {

    /**
     * Creates new form MenuPenyedia
     */
    public Penyedia() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSimpan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPenyedia = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        lblNama = new javax.swing.JLabel();
        btnHapus = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNamaBarang = new javax.swing.JTextField();
        spinJumBarang = new javax.swing.JSpinner();
        spinIDBarang = new javax.swing.JSpinner();

        btnSimpan.setText("SIMPAN");

        tbPenyedia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbPenyedia);

        btnEdit.setText("EDIT");

        lblNama.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblNama.setText("Penyedia");

        btnHapus.setText("HAPUS");

        jLabel1.setText("Id Barang");

        jLabel2.setText("Nama Barang");

        jLabel3.setText("Jumlah");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(btnSimpan))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamaBarang)
                            .addComponent(spinJumBarang)
                            .addComponent(spinIDBarang)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus))
                            .addComponent(lblNama))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spinIDBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinJumBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSimpan)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnHapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNama;
    private javax.swing.JSpinner spinIDBarang;
    private javax.swing.JSpinner spinJumBarang;
    private javax.swing.JTable tbPenyedia;
    private javax.swing.JTextField txtNamaBarang;
    // End of variables declaration//GEN-END:variables

    public int getID(){
        return (Integer) spinIDBarang.getValue();
    }
    
    public void deactivateID(){
        spinIDBarang.setEnabled(false);
    }
    
    public void activateID(){
        spinIDBarang.setEnabled(true);
    }
    
    public String getNama(){
        return txtNamaBarang.getText();
    }
    
    public void deactivateNama(){
        txtNamaBarang.setEnabled(false);
    }
    
    public void activateNama(){
        txtNamaBarang.setEnabled(true);
    }
    
    public boolean getStatusNama(){
        return txtNamaBarang.isEnabled();
    }
    
    public int getJumlah(){
        return (Integer) spinJumBarang.getValue();
    }

    public Object simpanButtonPressed(){
        return btnSimpan;
    }
    
    public Object editButtonPressed(){
        return btnEdit;
    }
    
    public Object deleteButtonPressed(){
        return btnHapus;
    }
    
    public void setLabelPetugas(String s){
        lblNama.setText(s);
    }
    
    public void setListBarang(ArrayList<Barang> list){
        String[] judul = {"ID Barang","Nama Barang","Jumlah"};
        String[][] isi = new String[list.size()][3];
        for (int i = 0; i < list.size(); i++){
            isi[i][0] = String.valueOf(list.get(i).getID());
            isi[i][1] = list.get(i).getNama();
            isi[i][2] = String.valueOf(list.get(i).getJumlah());
        }
        DefaultTableModel tableModel = new DefaultTableModel(isi,judul);
        tbPenyedia.setModel(tableModel);
        tbPenyedia.getColumnModel().getColumn(0).setPreferredWidth(20);
    }
    
    public int getSelectedID(){
        return tbPenyedia.getSelectedRow();
    }
    
    public Object tabelPenyediaSelected(){
        return tbPenyedia;
    }
    
    public void refresh() {
        spinIDBarang.setValue(0);
        txtNamaBarang.setText("");
        spinJumBarang.setValue(0);
    }
    
    public void refreshEdit(Barang b) {
        spinIDBarang.setValue(b.getID());
        txtNamaBarang.setText(b.getNama());
        spinJumBarang.setValue(b.getJumlah());
    }
    
    public void addListener(ActionListener e) {
        btnSimpan.addActionListener(e);
        btnEdit.addActionListener(e);
        btnHapus.addActionListener(e);
    }
    
    public void addAdapter(MouseAdapter e){
        tbPenyedia.addMouseListener(e);
    }
}
