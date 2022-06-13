/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import control.CustomerControl;
import exception.InputKosongException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import model.Customer;
import table.TableCustomer;

/**
 *
 * @author Captbay
 */
public class CustomerView extends javax.swing.JFrame {
    String action = null;
    int selected;
    CustomerControl cCTRL = new CustomerControl();
    List<Customer> listCustomer;
    /**
     * Creates new form test
     */
    public CustomerView() {
        initComponents();
        setComponent(false);
        setEditDeleteBtn(false);
        showCustomer();
        
//        initTable();
    }
    
    
    
    public void setEditDeleteBtn(boolean value){
        editBtn.setEnabled(value);
        deleteBtn.setEnabled(value);
    }
    
    public void showCustomer(){
        tabelView.setModel(cCTRL.getTableCustomer(""));
    }
    
    public void clearText(){
        namaInput.setText("");
        alamatInput.setText("");
        nohpInput.setText("");
        searchInput.setText("");
    }
    
    public void setComponent(boolean value){
        namaInput.setEnabled(value);
        alamatInput.setEnabled(value);
        nohpInput.setEnabled(value);
        
        saveBtn.setEnabled(value);
        cancelBtn.setEnabled(value);
    }
    
    private Object getTableSelectedObject(javax.swing.JTable table) {
        if(table.getSelectedRow() != -1) {
            return table.getModel().getValueAt(table.getSelectedRow(), 99);
        } else {
            return null;
        }
    }
    
//    private void tblUtamaMouseClicked(java.awt.event.MouseEvent evt) {                                      
//        Customer C = (Customer) getTableSelectedObject(tblUtama);
//        // on selected, display ke inputannya
//        // tetapkan ID:
//        this.selectedId = C.getId();
//        
//        // tetapkan input:
//        namaInput.setText(C.getNama());
//        alamatInput.setText(C.getAlamat());
//        nohpInput.setText(C.getNoHP());
//    }  
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navBar = new javax.swing.JPanel();
        logoArea = new javax.swing.JLabel();
        customerBtn = new javax.swing.JButton();
        pegawaiBtn = new javax.swing.JButton();
        transaksiBtn = new javax.swing.JButton();
        menuBar = new javax.swing.JPanel();
        scrollPanel = new javax.swing.JScrollPane();
        manuBarDetailPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        namaView = new javax.swing.JPanel();
        namaDetailView = new javax.swing.JLabel();
        searchPanel = new javax.swing.JPanel();
        searchInput = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        inputPanel = new javax.swing.JPanel();
        saveBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        namaLabel = new javax.swing.JLabel();
        namaInput = new javax.swing.JTextField();
        alamatLabel = new javax.swing.JLabel();
        alamatInput = new javax.swing.JTextField();
        nohpLabel = new javax.swing.JLabel();
        nohpInput = new javax.swing.JTextField();
        scrollTabelPanel = new javax.swing.JScrollPane();
        tabelView = new javax.swing.JTable();
        footer = new javax.swing.JPanel();
        namaFooter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 900));

        navBar.setBackground(new java.awt.Color(35, 45, 59));

        logoArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo-new.png"))); // NOI18N
        logoArea.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoAreaMouseClicked(evt);
            }
        });

        customerBtn.setBackground(new java.awt.Color(90, 98, 108));
        customerBtn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        customerBtn.setForeground(new java.awt.Color(255, 255, 255));
        customerBtn.setText("Customer");
        customerBtn.setBorder(null);
        customerBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        customerBtn.setEnabled(false);

        pegawaiBtn.setBackground(new java.awt.Color(90, 98, 108));
        pegawaiBtn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        pegawaiBtn.setForeground(new java.awt.Color(255, 255, 255));
        pegawaiBtn.setText("Pegawai");
        pegawaiBtn.setBorder(null);
        pegawaiBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pegawaiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pegawaiBtnActionPerformed(evt);
            }
        });

        transaksiBtn.setBackground(new java.awt.Color(90, 98, 108));
        transaksiBtn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        transaksiBtn.setForeground(new java.awt.Color(255, 255, 255));
        transaksiBtn.setText("Transaksi");
        transaksiBtn.setBorder(null);
        transaksiBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        transaksiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transaksiBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout navBarLayout = new javax.swing.GroupLayout(navBar);
        navBar.setLayout(navBarLayout);
        navBarLayout.setHorizontalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navBarLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logoArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pegawaiBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(transaksiBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        navBarLayout.setVerticalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navBarLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(logoArea)
                .addGap(28, 28, 28)
                .addComponent(customerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pegawaiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(transaksiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuBar.setBackground(new java.awt.Color(241, 239, 239));

        scrollPanel.setBorder(null);
        scrollPanel.setMinimumSize(new java.awt.Dimension(550, 750));
        scrollPanel.setPreferredSize(new java.awt.Dimension(550, 750));
        scrollPanel.setRequestFocusEnabled(false);

        manuBarDetailPanel.setBackground(new java.awt.Color(255, 255, 255));
        manuBarDetailPanel.setPreferredSize(new java.awt.Dimension(550, 550));

        headerPanel.setBackground(new java.awt.Color(125, 135, 147));

        namaView.setOpaque(false);

        namaDetailView.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        namaDetailView.setForeground(new java.awt.Color(255, 255, 255));
        namaDetailView.setText("Customer");

        javax.swing.GroupLayout namaViewLayout = new javax.swing.GroupLayout(namaView);
        namaView.setLayout(namaViewLayout);
        namaViewLayout.setHorizontalGroup(
            namaViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namaViewLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(namaDetailView, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        namaViewLayout.setVerticalGroup(
            namaViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namaViewLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(namaDetailView)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        searchPanel.setOpaque(false);

        searchInput.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        searchBtn.setBackground(new java.awt.Color(35, 45, 59));
        searchBtn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-search.png"))); // NOI18N
        searchBtn.setBorder(null);
        searchBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addComponent(searchInput, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchInput, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addComponent(namaView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(namaView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        inputPanel.setOpaque(false);

        saveBtn.setBackground(new java.awt.Color(13, 110, 253));
        saveBtn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-save.png"))); // NOI18N
        saveBtn.setText("Simpan");
        saveBtn.setBorder(null);
        saveBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        editBtn.setBackground(new java.awt.Color(241, 196, 15));
        editBtn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-edit.png"))); // NOI18N
        editBtn.setText("Ubah");
        editBtn.setBorder(null);
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        cancelBtn.setBackground(new java.awt.Color(220, 53, 69));
        cancelBtn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-cancel.png"))); // NOI18N
        cancelBtn.setText("Batal");
        cancelBtn.setBorder(null);
        cancelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        addBtn.setBackground(new java.awt.Color(25, 135, 84));
        addBtn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-add.png"))); // NOI18N
        addBtn.setText("Tambah");
        addBtn.setBorder(null);
        addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(220, 53, 69));
        deleteBtn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-delete.png"))); // NOI18N
        deleteBtn.setText("Hapus");
        deleteBtn.setBorder(null);
        deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        namaLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        namaLabel.setText("Nama");

        namaInput.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        namaInput.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(125, 135, 147)));

        alamatLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        alamatLabel.setText("Alamat");

        alamatInput.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        alamatInput.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(125, 135, 147)));

        nohpLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        nohpLabel.setText("Nomor Handphone");

        nohpInput.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        nohpInput.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(125, 135, 147)));
        nohpInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nohpInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(alamatLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namaLabel)
                            .addComponent(nohpLabel)
                            .addComponent(namaInput, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                            .addComponent(alamatInput)
                            .addComponent(nohpInput))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(inputPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(369, Short.MAX_VALUE)))
        );
        inputPanelLayout.setVerticalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(namaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namaInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(alamatLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alamatInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nohpLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nohpInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(inputPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(268, Short.MAX_VALUE)))
        );

        tabelView.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tabelView.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelView.setRowHeight(32);
        tabelView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelViewMouseClicked(evt);
            }
        });
        scrollTabelPanel.setViewportView(tabelView);

        footer.setBackground(new java.awt.Color(125, 135, 147));

        namaFooter.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        namaFooter.setForeground(new java.awt.Color(255, 255, 255));
        namaFooter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        namaFooter.setText("Made with ♥ by Kuli IT Clean Fresh Laundry");

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(namaFooter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(namaFooter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollTabelPanel)
                .addContainerGap())
            .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollTabelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(footer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout manuBarDetailPanelLayout = new javax.swing.GroupLayout(manuBarDetailPanel);
        manuBarDetailPanel.setLayout(manuBarDetailPanelLayout);
        manuBarDetailPanelLayout.setHorizontalGroup(
            manuBarDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manuBarDetailPanelLayout.createSequentialGroup()
                .addGroup(manuBarDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        manuBarDetailPanelLayout.setVerticalGroup(
            manuBarDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manuBarDetailPanelLayout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollPanel.setViewportView(manuBarDetailPanel);

        javax.swing.GroupLayout menuBarLayout = new javax.swing.GroupLayout(menuBar);
        menuBar.setLayout(menuBarLayout);
        menuBarLayout.setHorizontalGroup(
            menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        menuBarLayout.setVerticalGroup(
            menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        setComponent(true);
        clearText();
        searchInput.setText("");
        action = "Tambah";
    }//GEN-LAST:event_addBtnActionPerformed

    private void logoAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoAreaMouseClicked
        MainMenuView MMV = new MainMenuView();
        this.dispose();
        MMV.setVisible(true);
    }//GEN-LAST:event_logoAreaMouseClicked

    public void inputKosongException() throws InputKosongException{
        if(namaInput.getText().isEmpty() || alamatInput.getText().isEmpty() || nohpInput.getText().isEmpty()){
            throw new InputKosongException();
        }
    }
    
    private void transaksiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaksiBtnActionPerformed
        TransaksiView tv = new TransaksiView();
        this.dispose();
        tv.setVisible(true);
    }//GEN-LAST:event_transaksiBtnActionPerformed

    private void nohpInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nohpInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nohpInputActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        setEditDeleteBtn(false);
        setComponent(false);
        
        try{
            TableCustomer tabel = cCTRL.getTableCustomer(searchInput.getText());
            
            if(tabel.getRowCount() != 0){
                tabelView.setModel(tabel);
            } else{
                clearText();
                JOptionPane.showConfirmDialog(null, "Data tidak ditemukan", "Warning", JOptionPane.DEFAULT_OPTION);
                setEditDeleteBtn(false);
            }
        } catch(Exception e){
            System.out.println("Error searching...");
            System.out.println(e);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try{
            inputKosongException();
            
            Customer c = new Customer(selected, namaInput.getText(), alamatInput.getText(), nohpInput.getText());
            if(action.equalsIgnoreCase("Tambah")){
                cCTRL.insertDataCustomer(c);
            } else{
                cCTRL.updateDataCustomer(c);
            }
        } catch(InputKosongException e){
            System.out.println("Error: " + e.getMessage());
            JOptionPane.showConfirmDialog(null, "Data tidak boleh kosong", "Warning", JOptionPane.DEFAULT_OPTION);
        } 
       clearText();
       showCustomer();
       setComponent(false);
       setEditDeleteBtn(false);
    }//GEN-LAST:event_saveBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        setComponent(false);
        setEditDeleteBtn(false);
        clearText();    
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void tabelViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelViewMouseClicked
        setEditDeleteBtn(true);
        setComponent(false);
        int indexCustomer = -1;
        TableModel tableModel = tabelView.getModel();
        
        selected = Integer.parseInt(tableModel.getValueAt(tabelView.getSelectedRow(), 0).toString());
        namaInput.setText(tableModel.getValueAt(tabelView.getSelectedRow(), 1).toString());
        alamatInput.setText(tableModel.getValueAt(tabelView.getSelectedRow(), 2).toString());
        nohpInput.setText(tableModel.getValueAt(tabelView.getSelectedRow(), 3).toString());
        
        listCustomer = cCTRL.showListAllCustomer();
        int customer_id = (int) tableModel.getValueAt(tabelView.getSelectedRow(), 0);
        for(Customer c : listCustomer){
            if(c.getId() == customer_id){
                indexCustomer = listCustomer.indexOf(c);
            }
        }
        
//        dropdownComputer.setSelectedIndex(indexComputer);
        
//        int pembeli_id = Integer.parseInt(tableModel.getValueAt(tabelView.getSelectedRow(),10).toString());
        
//        dropdownPembeli.setSelectedIndex(indexPembeli);
    }//GEN-LAST:event_tabelViewMouseClicked

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int getAnswer = JOptionPane.showConfirmDialog(rootPane, "Apakah anda yakin untuk menghapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        switch(getAnswer){
            case 0:
                try{
                    cCTRL.deleteDataCustomer(selected);
                    clearText();
                    showCustomer();
                    setComponent(false);
                    setEditDeleteBtn(false);
                } catch(Exception e){
                    System.out.println("Error deleting data...");
                    System.out.println(e);
                }
                break;
                
            case 1:
                break;
        }
        
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        setComponent(true);
        action = "Ubah";
    }//GEN-LAST:event_editBtnActionPerformed

    private void pegawaiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pegawaiBtnActionPerformed
        PegawaiView2 pv = new PegawaiView2();
        this.dispose();
        pv.setVisible(true);
    }//GEN-LAST:event_pegawaiBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CustomerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField alamatInput;
    private javax.swing.JLabel alamatLabel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton customerBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JLabel logoArea;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel manuBarDetailPanel;
    private javax.swing.JPanel menuBar;
    private javax.swing.JLabel namaDetailView;
    private javax.swing.JLabel namaFooter;
    private javax.swing.JTextField namaInput;
    private javax.swing.JLabel namaLabel;
    private javax.swing.JPanel namaView;
    private javax.swing.JPanel navBar;
    private javax.swing.JTextField nohpInput;
    private javax.swing.JLabel nohpLabel;
    private javax.swing.JButton pegawaiBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JScrollPane scrollTabelPanel;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchInput;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTable tabelView;
    private javax.swing.JButton transaksiBtn;
    // End of variables declaration//GEN-END:variables
}
