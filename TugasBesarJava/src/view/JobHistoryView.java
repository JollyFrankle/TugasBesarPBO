/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.github.lgooddatepicker.components.DateTimePicker;
import connection.DbConnection;
import control.CustomerControl;
import control.JobHistoryControl;
import control.PegawaiControl;
import dao.JobHistoryPreparedDAO;
import exception.InputKosongException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import model.Customer;
import model.JobHistory;
import model.Pegawai;
import model.Transaksi;
import table.TableCustomer;
import table.TableTransaksi;

/**
 *
 * @author Captbay
 */
public class JobHistoryView extends javax.swing.JFrame {
    String action = null;
    int selectedId = 0;
    Transaksi selectedT;
    
    CustomerControl cCTRL = new CustomerControl();
    PegawaiControl pCTRL = new PegawaiControl();
    JobHistoryControl jhCTRL = new JobHistoryControl();
    JobHistoryPreparedDAO DAO = new JobHistoryPreparedDAO();
    
    List<Pegawai> listP = pCTRL.showListAllPegawai();
    List<JobHistory> listJH;
    
    /**
     * Creates new form test
     * @param selectedT
     * @param parentView
     */
    public JobHistoryView(Transaksi selectedT, TransaksiView parentView) {
        initComponents();
        this.selectedT = selectedT;
        
        listJH = DAO.getJobHistory(selectedT.getId());
        
        // on dispose: refresh parent view's data
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                parentView.publicDataReload();
                System.out.println(DbConnection.ANSI_GREEN + "[OK] Data reloaded on Transaksi View, initated by JobHistoryView for Transaksi.id = " + selectedT.getId());
            }
        });

        
        initDTInput(inputTanggal, LocalDate.now().minusMonths(1), LocalDate.now().plusMonths(3));
        
        // Disable save and cancel button - as theres no data displayed yet
        setSaveCancelBtn(false);
        // Disable all user inputs
        setUserInputComponents(false);
        // Display data to the table --> no search necessary in job history
        getTableData();
        // Display customer dan pegawai to dropdown:
        displayToDD();
        // Clear all user input
        clearUserInput();
    }
    
    private void displayToDD() {
        listP = pCTRL.showListAllPegawai();
        for(int i = 0; i < listP.size(); i++){
            ddPegawai.addItem(listP.get(i));
        }
    }
    
    private void setUserInputComponents(boolean v) {
        ddPegawai.setEnabled(v);
        inputTanggal.setEnabled(v);
        inputAktivitas.setEnabled(v);
    }
    
    private void clearUserInput() {
        ddPegawai.setSelectedIndex(-1);
        inputTanggal.setDateTimeStrict(null);
        inputAktivitas.setText("");
    }
    
    private void setSaveCancelBtn(boolean v) {
        saveBtn.setEnabled(v);
        cancelBtn.setEnabled(v);
    }

    private void getTableData() {
        /*
         * boolean strict:
         * IF true: digunakan dalam melakukan pencarian: return "data tidak ditemukan"
         * kalau tidak ada row yang didapat
         * IF false: digunakan dalam inisialisasi awal
         */
//        TableTransaksi tblTx = tCTRL.searchTransaksi(query);
//        if (tblTx.getRowCount() > 0 || strict == false) {
//            tableJobHistory.setModel(tblTx);
//            // Set width
//            int colWidth[] = {175, 175, 175, 175, 120, 75, 250};
//            int minWidth[] = {150, 150, 150, 150, 120, 75, 250};
//            int maxWidth[] = {300, 300, 300, 300, 200, 100, 0};
//            for(int i=0; i<colWidth.length; i++) {
//                if(colWidth[i] > 0)
//                    tableJobHistory.getColumnModel().getColumn(i).setPreferredWidth(colWidth[i]);
//                if(minWidth[i] > 0)
//                    tableJobHistory.getColumnModel().getColumn(i).setMinWidth(minWidth[i]);
//                if(maxWidth[i] > 0)
//                    tableJobHistory.getColumnModel().getColumn(i).setMaxWidth(maxWidth[i]);
//            }
//            
//            // reset user input
//            clearUserInput();
//            
//            // disable edit, delete, save, and cancel button in case user had viewed/edited something
//            setEditDeleteBtn(false);
//            setSaveCancelBtn(false);
//            
//            // disable user input
//            setUserInputComponents(false);
//        } else {
//            JOptionPane.showMessageDialog(this, "Data berdasarkan kueri pencarian tidak ditemukan!", "CFL - Notification", JOptionPane.WARNING_MESSAGE);
//        }
    }

    private Object getTableSelectedObject(javax.swing.JTable table) {
        if (table.getSelectedRow() != -1) {
            return table.getModel().getValueAt(table.getSelectedRow(), 99);
        } else {
            return null;
        }
    }
    
    private String getFullDateTime(DateTimePicker input) {
        try {
            // getDateTimeStrinct() kemudian ubah ke format "yyyy-MM-dd HH:mm:ss"
            return input.getDateTimeStrict().format(Transaksi.DEFAULT_DTF);
        } catch (Exception e) {
            // Input date atau time belum diisi lengkap
            return null;
        }
    }
    
    private void initDTInput(DateTimePicker input, LocalDate min, LocalDate max) {
        com.github.lgooddatepicker.components.DatePicker DP = input.getDatePicker();
        com.github.lgooddatepicker.components.TimePicker TP = input.getTimePicker();
        
        TP.setOpaque(false);
        DP.setOpaque(false);
        
        // Java passing by reference, jadi dengan melakukan ini, kita mendapatkan settings dari masing2 DatePicker dan TimePickernya, kemudian memodifikasinya kemudian.
        com.github.lgooddatepicker.components.DatePickerSettings thisDPs = DP.getSettings();
        com.github.lgooddatepicker.components.TimePickerSettings thisTPs = TP.getSettings();
        
        // Set settings:
        thisDPs.setLocale(new java.util.Locale("id"));
        thisDPs.setDateRangeLimits(min, max);
        thisTPs.use24HourClockFormat();
        
        // Set font:
        java.awt.Font elementFont = input.getFont();
        thisDPs.setFontVetoedDate(elementFont);
        thisDPs.setFontValidDate(elementFont);
        thisDPs.setFontInvalidDate(elementFont);
        thisTPs.fontValidTime = elementFont;
        thisTPs.fontInvalidTime = elementFont;
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
    
    private void inputExceptionCheck() throws InputKosongException {
        if(
                ddPegawai.getSelectedIndex() == -1 ||
                getFullDateTime(inputTanggal) == null ||
                inputAktivitas.getText().isBlank()
        ) {
            throw new InputKosongException();
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

        menuBar = new javax.swing.JPanel();
        scrollPanel = new javax.swing.JScrollPane();
        manuBarDetailPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        namaView = new javax.swing.JPanel();
        namaDetailView = new javax.swing.JLabel();
        namaDetailView1 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        inputPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        nohpLabel = new javax.swing.JLabel();
        alamatLabel = new javax.swing.JLabel();
        ddPegawai = new javax.swing.JComboBox<>();
        namaLabel = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        inputTanggal = new com.github.lgooddatepicker.components.DateTimePicker();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputAktivitas = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        scrollTabelPanel = new javax.swing.JScrollPane();
        tableJobHistory = new javax.swing.JTable();
        footer = new javax.swing.JPanel();
        namaFooter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 900));

        menuBar.setBackground(new java.awt.Color(241, 239, 239));

        scrollPanel.setBorder(null);
        scrollPanel.setMinimumSize(new java.awt.Dimension(550, 750));
        scrollPanel.setPreferredSize(new java.awt.Dimension(550, 750));
        scrollPanel.setRequestFocusEnabled(false);

        manuBarDetailPanel.setBackground(new java.awt.Color(255, 255, 255));
        manuBarDetailPanel.setPreferredSize(new java.awt.Dimension(550, 550));

        headerPanel.setBackground(new java.awt.Color(125, 135, 147));

        namaView.setOpaque(false);

        namaDetailView.setText("Job History");
        namaDetailView.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        namaDetailView.setForeground(new java.awt.Color(255, 255, 255));

        namaDetailView1.setText("Transaksi #13");
        namaDetailView1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        namaDetailView1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout namaViewLayout = new javax.swing.GroupLayout(namaView);
        namaView.setLayout(namaViewLayout);
        namaViewLayout.setHorizontalGroup(
            namaViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namaViewLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(namaViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namaDetailView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(namaDetailView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        namaViewLayout.setVerticalGroup(
            namaViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, namaViewLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(namaDetailView1)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(namaDetailView)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(namaView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(namaView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        inputPanel.setOpaque(false);
        inputPanel.setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setOpaque(false);

        nohpLabel.setText("Aktivitas");
        nohpLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        alamatLabel.setText("Tanggal");
        alamatLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        namaLabel.setText("Pegawai");
        namaLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

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

        inputTanggal.setOpaque(false);

        jPanel3.setBackground(new java.awt.Color(207, 244, 252));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(182, 239, 251), 1, true));

        jLabel1.setText("Perhatian!");
        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N

        jLabel2.setText("Job history tidak dapat diubah atau dihapus");
        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel3.setText("setelah ditambahkan.");
        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addGap(16, 16, 16))
        );

        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-add.png"))); // NOI18N
        addBtn.setText("Tambah");
        addBtn.setBackground(new java.awt.Color(25, 135, 84));
        addBtn.setBorder(null);
        addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBtn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        inputAktivitas.setColumns(20);
        inputAktivitas.setLineWrap(true);
        inputAktivitas.setRows(2);
        inputAktivitas.setTabSize(4);
        inputAktivitas.setWrapStyleWord(true);
        inputAktivitas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(inputAktivitas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(alamatLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ddPegawai, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nohpLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(namaLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(namaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ddPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(alamatLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(nohpLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        inputPanel.add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 461, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 475, Short.MAX_VALUE)
        );

        inputPanel.add(jPanel2);

        tableJobHistory.setModel(new javax.swing.table.DefaultTableModel(
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
        tableJobHistory.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tableJobHistory.setRowHeight(32);
        tableJobHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableJobHistoryMouseClicked(evt);
            }
        });
        scrollTabelPanel.setViewportView(tableJobHistory);

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
            .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(scrollTabelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                .addGap(16, 16, 16))
            .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(scrollTabelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addGap(16, 16, 16)
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
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollPanel.setViewportView(manuBarDetailPanel);

        javax.swing.GroupLayout menuBarLayout = new javax.swing.GroupLayout(menuBar);
        menuBar.setLayout(menuBarLayout);
        menuBarLayout.setHorizontalGroup(
            menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
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
                .addGap(0, 0, 0)
                .addComponent(menuBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // Set save and cancel btn to be clickable:
        this.setSaveCancelBtn(true);
        // Set user input to be editable
        this.setUserInputComponents(true);
        // Clear user input
        this.clearUserInput();
        // Set selectedId to 0 (for action add)
        selectedId = 0;
    }//GEN-LAST:event_addBtnActionPerformed
    
    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        int userAns = JOptionPane.showConfirmDialog(rootPane, "Apakah Anda yakin ingin menambahkan log ini?\r\nLog tidak dapat diubah atau dihapus setelah ditambahkan!", "CFL - Konfirmasi tindakan", JOptionPane.YES_NO_OPTION);
        // userAns will return 0 if user answers YES, 1 if user answers NO
        if(userAns == 0) {
            try {
                inputExceptionCheck();
                
                Pegawai selP = (Pegawai) ddPegawai.getSelectedItem();
                
                JobHistory inJH = new JobHistory(
                        selectedId,
                        this.selectedT.getId(),
                        selP,
                        inputTanggal.getDateTimeStrict(),
                        inputAktivitas.getText()
                );
                
                if(selectedId == 0) {
                    // Action tambah, karena selectedId = 0
//                    jhCTRL.insertDataJobHistory(inJH);
                    JOptionPane.showMessageDialog(this, "Berhasil menambahkan log pada job history!", "CFL - Notification", JOptionPane.INFORMATION_MESSAGE);
                } // tidak ada action edit atau delete!
                
                // Resets the form
                // Set save and cansel btn to be clickable:
                this.setSaveCancelBtn(false);
                
                // Set user input to be uneditable
                this.setUserInputComponents(false);
                
                // Reset user input
                clearUserInput();
                
                // Refresh table
                getTableData();
            } catch (InputKosongException e) {
                JOptionPane.showMessageDialog(this, e.toString());
            }
        } else {
            // Batal menambahkan/memperbarui data
            JOptionPane.showMessageDialog(this, "Batal melakukan tindakan!", "CFL - Notification", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void tableJobHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableJobHistoryMouseClicked
        // Reset inputs:
        clearUserInput();
        
        // Disable save cancel btn
        setSaveCancelBtn(false);
        
        // In case the user input is already ON, turn it off
        setUserInputComponents(false);
        
        // Get selected data:
        Transaksi selectedT = (Transaksi) getTableSelectedObject(tableJobHistory);
        
        // Set the selected id --> having `0` means that we will be adding new data, not updating
        selectedId = selectedT.getId();
        
        // Display to input:
    }//GEN-LAST:event_tableJobHistoryMouseClicked

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
            java.util.logging.Logger.getLogger(JobHistoryView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JobHistoryView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JobHistoryView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JobHistoryView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
//            new JobHistoryView().setVisible(true);
            new TransaksiView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JLabel alamatLabel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JComboBox<Pegawai> ddPegawai;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JTextArea inputAktivitas;
    private javax.swing.JPanel inputPanel;
    private com.github.lgooddatepicker.components.DateTimePicker inputTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel manuBarDetailPanel;
    private javax.swing.JPanel menuBar;
    private javax.swing.JLabel namaDetailView;
    private javax.swing.JLabel namaDetailView1;
    private javax.swing.JLabel namaFooter;
    private javax.swing.JLabel namaLabel;
    private javax.swing.JPanel namaView;
    private javax.swing.JLabel nohpLabel;
    private javax.swing.JButton saveBtn;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JScrollPane scrollTabelPanel;
    private javax.swing.JTable tableJobHistory;
    // End of variables declaration//GEN-END:variables
}
