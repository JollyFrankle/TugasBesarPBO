/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.github.lgooddatepicker.components.DatePicker;
import control.PegawaiControl;
import exception.InputKosongException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.Pegawai;
import table.TablePegawai;

/**
 *
 * @author Captbay
 */
public class PegawaiView2 extends javax.swing.JFrame {
    private String action = null;
    private String selectedId;
    private final PegawaiControl pc = new PegawaiControl();
    /**
     * Creates new form
     */
    public PegawaiView2() {
        initComponents();
        initDInput(inputTglLahir);
        
        setComponent(false);
        setEditDeleteBtn(false);
        getTableData("", false);
        
        scrollPanel.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    private void setEditDeleteBtn(boolean value){
        editBtn.setEnabled(value);
        deleteBtn.setEnabled(value);
    }
    
    private void clearText(){
        idInput.setText("");
        namaInput.setText("");
        inputTglLahir.setDate(null);
        nohpInput.setText("");
        searchInput.setText("");
        dropdownJobdesc.setSelectedIndex(-1);
    }
    
    private void setComponent(boolean value){
        idInput.setEnabled(value);
        namaInput.setEnabled(value);
        inputTglLahir.setEnabled(value);
        nohpInput.setEnabled(value);
        saveBtn.setEnabled(value);
        cancelBtn.setEnabled(value);
        dropdownJobdesc.setEnabled(value);
    }
    
    private Object getTableSelectedObject(javax.swing.JTable table){
        if(table.getSelectedRow() != -1){
            return table.getModel().getValueAt(table.getSelectedRow(), 99);
        } else
            return null;
    }
    
    private void inputKosongException() throws InputKosongException{
        if(
                idInput.getText().isEmpty() ||
                namaInput.getText().isEmpty() ||
                getFullDate(inputTglLahir) == null ||
                nohpInput.getText().isEmpty()
        ) {
            throw new InputKosongException();
        }
    }
    
    private String getFullDate(DatePicker input) {
        try {
            // getDateTimeStrinct() kemudian ubah ke format "yyyy-MM-dd HH:mm:ss"
            return input.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            // Input date atau time belum diisi lengkap
            return null;
        }
    }
    
    private void initDInput(DatePicker input) {
        
        // Java passing by reference, jadi dengan melakukan ini, kita mendapatkan settings dari masing2 DatePicker dan TimePickernya, kemudian memodifikasinya kemudian.
        com.github.lgooddatepicker.components.DatePickerSettings thisDPs = input.getSettings();
        
        // Set settings:
        thisDPs.setLocale(new java.util.Locale("id"));
        
        // Set font:
        java.awt.Font elementFont = input.getFont();
        thisDPs.setFontVetoedDate(elementFont);
        thisDPs.setFontValidDate(elementFont);
        thisDPs.setFontInvalidDate(elementFont);
    }
    
    private void getTableData(String query, boolean strict) {
        /*
         * boolean strict:
         * IF true: digunakan dalam melakukan pencarian: return "data tidak ditemukan"
         * kalau tidak ada row yang didapat
         * IF false: digunakan dalam inisialisasi awal
         */
        TablePegawai tbl = pc.getTablePegawai(query);
        if (tbl.getRowCount() > 0 || strict == false) {
            tabelView.setModel(tbl);
            // Set width
            int colWidth[] = {75, 175, 175, 200, 300};
            int minWidth[] = {50, 150, 150, 175, 200};
            int maxWidth[] = {75, 0, 225, 300, 0};
            for(int i=0; i<colWidth.length; i++) {
                if(colWidth[i] > 0)
                    tabelView.getColumnModel().getColumn(i).setPreferredWidth(colWidth[i]);
                if(minWidth[i] > 0)
                    tabelView.getColumnModel().getColumn(i).setMinWidth(minWidth[i]);
                if(maxWidth[i] > 0)
                    tabelView.getColumnModel().getColumn(i).setMaxWidth(maxWidth[i]);
            }
            
            // reset user input
            clearText();
            
            // disable edit, delete, save, and cancel button in case user had viewed/edited something
            setEditDeleteBtn(false);
            
            // disable user input
            setComponent(false);
        } else {
            JOptionPane.showMessageDialog(this, "Data berdasarkan kueri pencarian tidak ditemukan!", "CFL - Notification", JOptionPane.WARNING_MESSAGE);
        }
    }

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
        jPanel1 = new javax.swing.JPanel();
        idInput = new javax.swing.JTextField();
        nohpInput = new javax.swing.JTextField();
        nohpLabel = new javax.swing.JLabel();
        dropdownJobdesc = new javax.swing.JComboBox<>();
        addBtn = new javax.swing.JButton();
        namaLabel = new javax.swing.JLabel();
        inputTglLahir = new com.github.lgooddatepicker.components.DatePicker();
        tanggallahirLabel = new javax.swing.JLabel();
        jobdecsLabel = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        editBtn = new javax.swing.JButton();
        namaInput = new javax.swing.JTextField();
        deleteBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        scrollTabelPanel = new javax.swing.JScrollPane();
        tabelView = new javax.swing.JTable();
        footer = new javax.swing.JPanel();
        namaFooter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1100, 700));

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
        customerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerBtnActionPerformed(evt);
            }
        });

        pegawaiBtn.setBackground(new java.awt.Color(90, 98, 108));
        pegawaiBtn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        pegawaiBtn.setForeground(new java.awt.Color(255, 255, 255));
        pegawaiBtn.setText("Pegawai");
        pegawaiBtn.setBorder(null);
        pegawaiBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pegawaiBtn.setEnabled(false);

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

        scrollPanel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setBorder(null);

        manuBarDetailPanel.setBackground(new java.awt.Color(255, 255, 255));
        manuBarDetailPanel.setMinimumSize(new java.awt.Dimension(400, 875));
        manuBarDetailPanel.setPreferredSize(new java.awt.Dimension(400, 875));

        headerPanel.setBackground(new java.awt.Color(125, 135, 147));

        namaView.setOpaque(false);

        namaDetailView.setText("Pegawai");
        namaDetailView.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        namaDetailView.setForeground(new java.awt.Color(255, 255, 255));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(namaView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        inputPanel.setOpaque(false);
        inputPanel.setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setOpaque(false);

        idInput.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        idInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nohpInput.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        nohpInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        nohpInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nohpInputActionPerformed(evt);
            }
        });

        nohpLabel.setText("Nomor Handphone");
        nohpLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        dropdownJobdesc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pencuci Pakaian", "Penyetrika Pakaian", "Packing Pakaian" }));
        dropdownJobdesc.setSelectedIndex(-1);
        dropdownJobdesc.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

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

        namaLabel.setText("Nama");
        namaLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        inputTglLahir.setBorder(null);
        inputTglLahir.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputTglLahir.setOpaque(false);

        tanggallahirLabel.setText("Tanggal Lahir");
        tanggallahirLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jobdecsLabel.setText("Job Description");
        jobdecsLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        idLabel.setText("ID Pegawai");
        idLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-edit.png"))); // NOI18N
        editBtn.setText("Ubah");
        editBtn.setBackground(new java.awt.Color(241, 196, 15));
        editBtn.setBorder(null);
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editBtn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        namaInput.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        namaInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        namaInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaInputActionPerformed(evt);
            }
        });

        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-delete.png"))); // NOI18N
        deleteBtn.setText("Hapus");
        deleteBtn.setBackground(new java.awt.Color(220, 53, 69));
        deleteBtn.setBorder(null);
        deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-save.png"))); // NOI18N
        saveBtn.setText("Simpan");
        saveBtn.setBackground(new java.awt.Color(13, 110, 253));
        saveBtn.setBorder(null);
        saveBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveBtn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        cancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-cancel.png"))); // NOI18N
        cancelBtn.setText("Batal");
        cancelBtn.setBackground(new java.awt.Color(220, 53, 69));
        cancelBtn.setBorder(null);
        cancelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelBtn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dropdownJobdesc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jobdecsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nohpLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nohpInput)
                            .addComponent(inputTglLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(namaInput)
                            .addComponent(tanggallahirLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idInput)
                            .addComponent(namaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(idLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(idLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(namaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namaInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(tanggallahirLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(nohpLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nohpInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jobdecsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dropdownJobdesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        inputPanel.add(jPanel1);

        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );

        inputPanel.add(jPanel2);

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
        tabelView.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tabelView.setRowHeight(32);
        tabelView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelViewMouseClicked(evt);
            }
        });
        scrollTabelPanel.setViewportView(tabelView);

        footer.setBackground(new java.awt.Color(125, 135, 147));

        namaFooter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        namaFooter.setText("Made with ♥ by Kuli IT Clean Fresh Laundry");
        namaFooter.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        namaFooter.setForeground(new java.awt.Color(255, 255, 255));

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
                .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTabelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
        );
        menuBarLayout.setVerticalGroup(
            menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
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
        // TODO add your handling code here:
        setEditDeleteBtn(false);
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

    private void transaksiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaksiBtnActionPerformed
        // TODO add your handling code here:
        TransaksiView tv = new TransaksiView();
        this.dispose();
        tv.setVisible(true);
    }//GEN-LAST:event_transaksiBtnActionPerformed

    private void nohpInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nohpInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nohpInputActionPerformed

    private void namaInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaInputActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        setComponent(true);
        idInput.setEnabled(false);
        action = "Ubah";
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        int getAnswer = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin ingin menghapus data?\r\nMenghapus data Pegawai berarti menghapus data job historynya juga.", "CFL - Confirmation", JOptionPane.YES_NO_OPTION);
        if(getAnswer == 0){
            try{
                pc.deleteDataPegawai(selectedId);
                clearText();
                getTableData("", false);
                setComponent(false);
                setEditDeleteBtn(false);
            } catch(Exception e){
                System.out.println("Error deleting data...");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        try{
            inputKosongException();
            
            Pegawai p = new Pegawai(idInput.getText(), namaInput.getText(), getFullDate(inputTglLahir), nohpInput.getText(), dropdownJobdesc.getSelectedItem().toString());
            if(action.equalsIgnoreCase("Tambah")){
                pc.InsertDataPegawai(p);
            } else
                pc.updateDataPegawai(p);
        } catch(InputKosongException e){
            JOptionPane.showConfirmDialog(null, "Input tidak boleh kosong", "CFL - Warning", JOptionPane.DEFAULT_OPTION);
            System.out.println("Error: " + e.toString());
        }
       clearText();
       getTableData("", false);
       setComponent(false);
       setEditDeleteBtn(false);
    }//GEN-LAST:event_saveBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        setComponent(false);
        setEditDeleteBtn(false);
        clearText();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        getTableData(searchInput.getText(), true);
        searchInput.setText("");
    }//GEN-LAST:event_searchBtnActionPerformed

    private void customerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerBtnActionPerformed
        // TODO add your handling code here:
        CustomerView cv = new CustomerView();
        this.dispose();
        cv.setVisible(true);
    }//GEN-LAST:event_customerBtnActionPerformed

    private void tabelViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelViewMouseClicked
        // TODO add your handling code here:
        setEditDeleteBtn(true);
        setComponent(false);
        
        Pegawai selectedP = (Pegawai) getTableSelectedObject(tabelView);
        selectedId = selectedP.getId();
        
        idInput.setText(selectedP.getId());
        namaInput.setText(selectedP.getNama());
        inputTglLahir.setDate(LocalDate.parse(selectedP.getTglLahir(), DateTimeFormatter.ISO_LOCAL_DATE));
        nohpInput.setText(selectedP.getNoHP());
        dropdownJobdesc.setSelectedItem(selectedP.getJobDesc());
    }//GEN-LAST:event_tabelViewMouseClicked

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
            java.util.logging.Logger.getLogger(PegawaiView2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PegawaiView2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PegawaiView2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PegawaiView2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PegawaiView2().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton customerBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JComboBox<String> dropdownJobdesc;
    private javax.swing.JButton editBtn;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JTextField idInput;
    private javax.swing.JLabel idLabel;
    private javax.swing.JPanel inputPanel;
    private com.github.lgooddatepicker.components.DatePicker inputTglLahir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jobdecsLabel;
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
    private javax.swing.JLabel tanggallahirLabel;
    private javax.swing.JButton transaksiBtn;
    // End of variables declaration//GEN-END:variables
}
