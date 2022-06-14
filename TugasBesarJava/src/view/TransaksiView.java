/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.github.lgooddatepicker.components.DateTimePicker;
import control.CustomerControl;
import control.PegawaiControl;
import control.TransaksiControl;
import exception.InputKosongException;
import exception.TanggalAmbilInvalidException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Customer;
import model.Pegawai;
import model.Transaksi;
import org.json.JSONArray;
import org.json.JSONObject;
import table.TableTransaksi;

/**
 *
 * @author Captbay
 */
public class TransaksiView extends javax.swing.JFrame {
    private final TransaksiControl tCTRL = new TransaksiControl();
    private final CustomerControl cCTRL = new CustomerControl();
    private final PegawaiControl pCTRL = new PegawaiControl();
    private int selectedId = 0;
    private Transaksi selectedTransaksi = null;

    // Penampung:
    private List<Customer> listC;
    private List<Pegawai> listP;
    
    /**
     * Creates new form test
     */
    public TransaksiView() {
        initComponents();

        initDTInput(inputTglMasuk, LocalDate.now().minusYears(1), LocalDate.now().plusMonths(1));
        initDTInput(inputTglSelesai, LocalDate.now().minusMonths(1), LocalDate.now().plusMonths(2));
        initDTInput(inputTglAmbil, LocalDate.now().minusMonths(1), LocalDate.now().plusMonths(3));
        inputTglMasuk.addDateTimeChangeListener((com.github.lgooddatepicker.zinternaltools.DateTimeChangeEvent event) -> {
            // tgl masuk berubah, recheck tgl ambil
            this.setTglSelesai();
        });
        
        // Disable edit and delete button
        setEditDeleteBtn(false);
        // Disable save and cancel button - as theres no data displayed yet
        setSaveCancelBtn(false);
        // Disable all user inputs
        setUserInputComponents(false);
        // Display data to the table
        getTableData("", false);
        // Display customer dan pegawai to dropdown:
        displayToDD();
        // Clear all user input
        clearUserInput();
    }
    
    private void displayToDD() {
        listC = cCTRL.showListAllCustomer();
        for(int i = 0; i < listC.size(); i++){
            ddCustomer.addItem(listC.get(i));
        }
        listP = pCTRL.showListAllPegawai();
        for(int i = 0; i < listP.size(); i++){
            ddPegawai.addItem(listP.get(i));
        }
    }
    
    private void setEditDeleteBtn(boolean v) {
        editBtn.setEnabled(v);
        deleteBtn.setEnabled(v);
    }
    
    private void setUserInputComponents(boolean v) {
        ddCustomer.setEnabled(v);
        inputTglMasuk.setEnabled(v);
        inputTglSelesai.setEnabled(false); // ini tidak boleh diubah, automatically generated
        inputTglAmbil.setEnabled(v);
        
        // sebelah kanan:
        inputBeratPakaian.setEnabled(v);
        inputBeratSelimut.setEnabled(v);
        inputBeratBoneka.setEnabled(v);
        ddKecepatan.setEnabled(v);
        cbFCuci.setEnabled(v);
        cbFSetrika.setEnabled(v);
        
        // tambahan button:
        btnResetTglMasuk.setEnabled(v);
        btnResetTglAmbil.setEnabled(v);
        
        // tombol ini hanya boleh diaktifkan scr manual:
        jobHistoryBtn.setEnabled(false);
        
        btnSetDTMasukNow.setEnabled(v);
        btnSetDTAmbilNow.setEnabled(v);
    }
    
    private void clearUserInput() {
        ddCustomer.setSelectedIndex(-1);
        inputTglMasuk.clear();
        inputTglSelesai.clear();
        inputTglAmbil.clear();
        
        inputBeratPakaian.setValue(0.0);
        inputBeratSelimut.setValue(0.0);
        inputBeratBoneka.setValue(0.0);
        
        ddKecepatan.setSelectedIndex(-1);
        cbFCuci.setSelected(false);
        cbFSetrika.setSelected(false);
        
        outTotalHarga.setText("-");
        
        // Pilihan pegawai penginput (hanya show jika action=add)
        lblPegawai.setVisible(false);
        ddPegawai.setVisible(false);
        ddPegawai.setSelectedIndex(-1);
    }
    
    private void setSaveCancelBtn(boolean v) {
        saveBtn.setEnabled(v);
        cancelBtn.setEnabled(v);
    }

    private void getTableData(String query, boolean strict) {
        /*
         * boolean strict:
         * IF true: digunakan dalam melakukan pencarian: return "data tidak ditemukan"
         * kalau tidak ada row yang didapat
         * IF false: digunakan dalam inisialisasi awal
         */
        TableTransaksi tblTx = tCTRL.searchTransaksi(query);
        if (tblTx.getRowCount() > 0 || strict == false) {
            tableTransaksi.setModel(tblTx);
            // Set width
            int colWidth[] = {175, 175, 175, 175, 120, 75, 250};
            int minWidth[] = {150, 150, 150, 150, 120, 75, 250};
            int maxWidth[] = {300, 300, 300, 300, 200, 100, 0};
            for(int i=0; i<colWidth.length; i++) {
                if(colWidth[i] > 0)
                    tableTransaksi.getColumnModel().getColumn(i).setPreferredWidth(colWidth[i]);
                if(minWidth[i] > 0)
                    tableTransaksi.getColumnModel().getColumn(i).setMinWidth(minWidth[i]);
                if(maxWidth[i] > 0)
                    tableTransaksi.getColumnModel().getColumn(i).setMaxWidth(maxWidth[i]);
            }
            
            // reset user input
            clearUserInput();
            
            // disable edit, delete, save, and cancel button in case user had viewed/edited something
            setEditDeleteBtn(false);
            setSaveCancelBtn(false);
            
            // disable user input
            setUserInputComponents(false);
        } else {
            JOptionPane.showMessageDialog(this, "Data berdasarkan kueri pencarian tidak ditemukan!", "CFL - Notification", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void publicDataReload() {
        this.getTableData("", false);
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
    
    private void setTglSelesai() {
        if(ddKecepatan.getSelectedIndex() == -1 || getFullDateTime(inputTglMasuk) == null) {
            // Kecepatan belum dipilih ATAU (checkbox cuci dan checkbox setrika belum ada yang dicentang): belum bisa dihitung tanggal ambil
            outTotalHarga.setText("-");
        } else {
            // Dapatkan dulu kecepatannya express atau reguler?
            String kec = (String) ddKecepatan.getSelectedItem();
            if(kec.equalsIgnoreCase("EXPRESS")) {
                // 6 jam
                inputTglSelesai.setDateTimeStrict(inputTglMasuk.getDateTimeStrict().plusHours(6));
            } else {
                // 2 hari
                inputTglSelesai.setDateTimeStrict(inputTglMasuk.getDateTimeStrict().plusDays(2));
            }
            // cek tgl ambil < tgl selesai pas mau simpan saja
        }
    }
    
    private void setTotalHarga() {
        if(ddKecepatan.getSelectedIndex() == -1 || (!cbFCuci.isSelected() && !cbFSetrika.isSelected())) {
            // Kecepatan belum dipilih ATAU (checkbox cuci dan checkbox setrika belum ada yang dicentang): belum bisa dihitung harga
            outTotalHarga.setText("-");
            lblHargaPakaian.setText("");
            lblHargaSelimut.setText("");
            lblHargaBoneka.setText("");
        } else {
            float totalHarga = 0;
            float beratPakaian = Float.parseFloat(inputBeratPakaian.getValue().toString());
            float beratSelimut = Float.parseFloat(inputBeratSelimut.getValue().toString());
            float beratBoneka = Float.parseFloat(inputBeratBoneka.getValue().toString());
            
            // Langsung kalkulasi seluruh nya
            java.util.Locale locID = new java.util.Locale("id");
            float hrgSatuanP = 0, hrgSatuanS = 0, hrgSatuanB = 0;
            if(ddKecepatan.getSelectedIndex() == 0){        // REGULAR
                if(cbFCuci.isSelected()) {
                    hrgSatuanP += 3000;
                    hrgSatuanS += 5000;
                    hrgSatuanB += 6000;
                }
                if(cbFSetrika.isSelected()) {
                    hrgSatuanP += 2000;
                    hrgSatuanS += 2000;
                    hrgSatuanB += 2000;
                }
            } else if(ddKecepatan.getSelectedIndex() == 1){     // EXPRESS
                if(cbFCuci.isSelected()) {
                    hrgSatuanP += 6000;
                    hrgSatuanS += 10000;
                    hrgSatuanB += 12000;
                }
                if(cbFSetrika.isSelected()) {
                    hrgSatuanP += 4000;
                    hrgSatuanS += 4000;
                    hrgSatuanB += 4000;
                }
            }
            lblHargaPakaian.setText("Rp" + String.format(locID, "%,.0f", hrgSatuanP) + "/kg");
            lblHargaSelimut.setText("Rp" + String.format(locID, "%,.0f", hrgSatuanS) + "/kg");
            lblHargaBoneka.setText("Rp" + String.format(locID, "%,.0f", hrgSatuanB) + "/kg");
            totalHarga += hrgSatuanP * beratPakaian;
            totalHarga += hrgSatuanS * beratSelimut;
            totalHarga += hrgSatuanB * beratBoneka;
            
//            if(ddKecepatan.getSelectedIndex() == 0){        // REGULAR
//                if(cbFCuci.isSelected()){
//                    hrgSatuanP += 5000;
//                    hrgSatuanS += 7000;
//                    hrgSatuanB += 6000;
//                    totalHarga += 5000*beratPakaian + 7000*beratSelimut + 6000*beratBoneka;
//                } else if(!cbFCuci.isSelected() && cbFSetrika.isSelected()){
//                    totalHarga += 2000*beratPakaian + 2000*beratSelimut + 0*beratBoneka;
//                } else if(cbFCuci.isSelected() && !cbFSetrika.isSelected()){
//                    totalHarga += 3000*beratPakaian + 5000*beratSelimut + 6000*beratBoneka;
//                }
//            } else if(ddKecepatan.getSelectedIndex() == 1){     // EXPRESS
//                if(cbFCuci.isSelected() && cbFSetrika.isSelected()){
//                    totalHarga += 10000*beratPakaian + 14000*beratSelimut + 12000*beratBoneka;
//                } else if(!cbFCuci.isSelected() && cbFSetrika.isSelected()){
//                    totalHarga += 4000*beratPakaian + 4000*beratSelimut + 0*beratBoneka;
//                } else if(cbFCuci.isSelected() && !cbFSetrika.isSelected()){
//                    totalHarga += 6000*beratPakaian + 10000*beratSelimut + 12000*beratBoneka;
//                }
//            }
            outTotalHarga.setText("Rp" + String.format(locID, "%,.0f", totalHarga));
        }
    }
    
    private JSONObject getTipeLayanan() {
        JSONObject retVal = new JSONObject();
        retVal.put("facility", new JSONArray());
        retVal.put("speed", (String) ddKecepatan.getSelectedItem());
        if(cbFCuci.isSelected()) {
            retVal.getJSONArray("facility").put("CUCI");
        }
        if(cbFSetrika.isSelected()) {
            retVal.getJSONArray("facility").put("SETRIKA");
        }
        return retVal;
    }
    
    // Input exception handling:
    private void inputExceptionCheck() throws InputKosongException, TanggalAmbilInvalidException {
        // Why do I use isBlank instead of isEmpty? Because if the user inputs whitespaces, it should totally be considered "not yet inputed".
        if(
                ddCustomer.getSelectedIndex() == -1 ||
                getFullDateTime(inputTglMasuk) == null ||
                getFullDateTime(inputTglSelesai) == null ||
                ddKecepatan.getSelectedIndex() == -1 ||
                (!cbFCuci.isSelected() && !cbFSetrika.isSelected()) || // kalau belum ada centang pada cusi/setrika, BLOCK
                (selectedId == 0 && ddPegawai.getSelectedIndex() == -1) // Kalau action=add dan belum ada petugas penginput yang dipilih, BLOCK
        ) {
            throw new InputKosongException();
        }
        
        if(
                getFullDateTime(inputTglAmbil) != null &&
                inputTglAmbil.getDateTimeStrict().isBefore(inputTglSelesai.getDateTimeStrict())
        ) {
            throw new TanggalAmbilInvalidException(
                    inputTglSelesai.getDateTimeStrict().format(Transaksi.LOCAL_DTF),
                    inputTglAmbil.getDateTimeStrict().format(Transaksi.LOCAL_DTF)
            );
        }
        // All checks passed
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
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        inputTglMasuk = new com.github.lgooddatepicker.components.DateTimePicker();
        inputTglSelesai = new com.github.lgooddatepicker.components.DateTimePicker();
        inputTglAmbil = new com.github.lgooddatepicker.components.DateTimePicker();
        ddCustomer = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        lblPegawai = new javax.swing.JLabel();
        ddPegawai = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        btnResetTglMasuk = new javax.swing.JButton();
        btnResetTglAmbil = new javax.swing.JButton();
        btnSetDTMasukNow = new javax.swing.JButton();
        btnSetDTAmbilNow = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cancelBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        inputBeratPakaian = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        lblHargaPakaian = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        inputBeratSelimut = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        lblHargaSelimut = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        inputBeratBoneka = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        lblHargaBoneka = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ddKecepatan = new javax.swing.JComboBox<>();
        cbFCuci = new javax.swing.JCheckBox();
        cbFSetrika = new javax.swing.JCheckBox();
        outTotalHarga = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jobHistoryBtn = new javax.swing.JButton();
        scrollTabelPanel = new javax.swing.JScrollPane();
        tableTransaksi = new javax.swing.JTable();
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
        transaksiBtn.setEnabled(false);
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
        scrollPanel.setMinimumSize(new java.awt.Dimension(100, 750));
        scrollPanel.setPreferredSize(new java.awt.Dimension(550, 750));
        scrollPanel.setRequestFocusEnabled(false);

        manuBarDetailPanel.setBackground(new java.awt.Color(255, 255, 255));
        manuBarDetailPanel.setPreferredSize(new java.awt.Dimension(550, 550));

        headerPanel.setBackground(new java.awt.Color(125, 135, 147));

        namaView.setOpaque(false);

        namaDetailView.setText("Transaksi");
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

        jPanel2.setOpaque(false);

        jLabel5.setText("Tanggal Ambil");
        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel3.setText("Tanggal Selesai");
        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel1.setText("Tanggal Masuk");
        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel4.setText("Customer");
        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

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

        inputTglMasuk.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputTglMasuk.setOpaque(false);

        inputTglSelesai.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputTglSelesai.setOpaque(false);

        inputTglAmbil.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputTglAmbil.setOpaque(false);

        ddCustomer.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel15.setText("Data Transaksi");
        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N

        lblPegawai.setText("Petugas Penginput Transaksi");
        lblPegawai.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        ddPegawai.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel18.setText("(otomatis, berdasarkan kecepatan layanan)");
        jLabel18.setFont(new java.awt.Font("Century Gothic", 2, 12)); // NOI18N

        btnResetTglMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-backspace.png"))); // NOI18N
        btnResetTglMasuk.setBackground(new java.awt.Color(33, 37, 41));
        btnResetTglMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnResetTglMasuk.setForeground(new java.awt.Color(255, 255, 255));
        btnResetTglMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTglMasukActionPerformed(evt);
            }
        });

        btnResetTglAmbil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-backspace.png"))); // NOI18N
        btnResetTglAmbil.setBackground(new java.awt.Color(33, 37, 41));
        btnResetTglAmbil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnResetTglAmbil.setForeground(new java.awt.Color(255, 255, 255));
        btnResetTglAmbil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTglAmbilActionPerformed(evt);
            }
        });

        btnSetDTMasukNow.setBackground(new java.awt.Color(25, 135, 84));
        btnSetDTMasukNow.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnSetDTMasukNow.setForeground(new java.awt.Color(255, 255, 255));
        btnSetDTMasukNow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-calendar.png"))); // NOI18N
        btnSetDTMasukNow.setText("Sekarang");
        btnSetDTMasukNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetDTMasukNowActionPerformed(evt);
            }
        });

        btnSetDTAmbilNow.setBackground(new java.awt.Color(25, 135, 84));
        btnSetDTAmbilNow.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnSetDTAmbilNow.setForeground(new java.awt.Color(255, 255, 255));
        btnSetDTAmbilNow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-calendar.png"))); // NOI18N
        btnSetDTAmbilNow.setText("Sekarang");
        btnSetDTAmbilNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetDTAmbilNowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inputTglAmbil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputTglSelesai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputTglMasuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSetDTAmbilNow, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetTglAmbil))
                    .addComponent(ddCustomer, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPegawai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ddPegawai, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSetDTMasukNow, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetTglMasuk))
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 67, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnSetDTMasukNow)
                    .addComponent(btnResetTglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputTglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel18))
                .addGap(8, 8, 8)
                .addComponent(inputTglSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(btnSetDTAmbilNow))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputTglAmbil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(lblPegawai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ddPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnResetTglAmbil, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        inputPanel.add(jPanel2);

        jPanel1.setOpaque(false);

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

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jPanel4.setOpaque(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Pakaian");
        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        inputBeratPakaian.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(20.0f), Float.valueOf(0.1f)));
        inputBeratPakaian.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputBeratPakaian.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                inputBeratPakaianComponentAdded(evt);
            }
        });
        inputBeratPakaian.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                beratStateChanged(evt);
            }
        });
        inputBeratPakaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputBeratPakaianMouseClicked(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("kg");
        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lblHargaPakaian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHargaPakaian.setText("Rp1.000/kg");
        lblHargaPakaian.setFont(new java.awt.Font("Century Gothic", 2, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHargaPakaian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(inputBeratPakaian, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)))
                .addGap(8, 8, 8))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputBeratPakaian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(4, 4, 4)
                .addComponent(lblHargaPakaian)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4);

        jPanel7.setOpaque(false);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Selimut");
        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        inputBeratSelimut.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(20.0f), Float.valueOf(0.1f)));
        inputBeratSelimut.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputBeratSelimut.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                beratStateChanged(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("kg");
        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lblHargaSelimut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHargaSelimut.setText("Rp1.000/kg");
        lblHargaSelimut.setFont(new java.awt.Font("Century Gothic", 2, 12)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(inputBeratSelimut, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)))
                .addGap(8, 8, 8))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHargaSelimut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputBeratSelimut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHargaSelimut)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel7);

        jPanel8.setOpaque(false);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Boneka");
        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        inputBeratBoneka.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(20.0f), Float.valueOf(0.1f)));
        inputBeratBoneka.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputBeratBoneka.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                beratStateChanged(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("kg");
        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lblHargaBoneka.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHargaBoneka.setText("Rp1.000/kg");
        lblHargaBoneka.setFont(new java.awt.Font("Century Gothic", 2, 12)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHargaBoneka, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(inputBeratBoneka, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputBeratBoneka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHargaBoneka)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel8);

        jLabel11.setText("Rincian Item Laundry");
        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N

        jLabel12.setText("Tipe Layanan");
        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N

        jLabel13.setText("Kecepatan");
        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel14.setText("Fasilitas");
        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        ddKecepatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "REGULAR", "EXPRESS" }));
        ddKecepatan.setSelectedIndex(-1);
        ddKecepatan.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ddKecepatan.setToolTipText("");
        ddKecepatan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ddcbItemStateChanged(evt);
            }
        });

        cbFCuci.setText("Cuci");
        cbFCuci.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cbFCuci.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ddcbItemStateChanged(evt);
            }
        });

        cbFSetrika.setText("Setrika");
        cbFSetrika.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cbFSetrika.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ddcbItemStateChanged(evt);
            }
        });

        outTotalHarga.setText("(jalankan program)");
        outTotalHarga.setFont(new java.awt.Font("Century Gothic", 1, 32)); // NOI18N

        jLabel17.setText("Total Harga");
        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel16.setText("REGULAR: selesai dalam 2 hari, EXPRESS: selesai dalam 6 jam");
        jLabel16.setFont(new java.awt.Font("Century Gothic", 2, 12)); // NOI18N

        jobHistoryBtn.setBackground(new java.awt.Color(33, 37, 41));
        jobHistoryBtn.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jobHistoryBtn.setForeground(new java.awt.Color(255, 255, 255));
        jobHistoryBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/buttons/icon-save.png"))); // NOI18N
        jobHistoryBtn.setText("Lihat Job History");
        jobHistoryBtn.setBorder(null);
        jobHistoryBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jobHistoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobHistoryBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbFCuci)
                        .addGap(18, 18, 18)
                        .addComponent(cbFSetrika)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ddKecepatan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(outTotalHarga)
                                    .addComponent(jLabel17)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jobHistoryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(16, 16, 16))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jobHistoryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(4, 4, 4)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ddKecepatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFCuci)
                    .addComponent(cbFSetrika))
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(0, 0, 0)
                .addComponent(outTotalHarga)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        inputPanel.add(jPanel1);

        tableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tableTransaksi.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tableTransaksi.setRowHeight(32);
        tableTransaksi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableTransaksi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTransaksiMouseClicked(evt);
            }
        });
        scrollTabelPanel.setViewportView(tableTransaksi);

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
                .addComponent(scrollTabelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollPanel.setViewportView(manuBarDetailPanel);

        javax.swing.GroupLayout menuBarLayout = new javax.swing.GroupLayout(menuBar);
        menuBar.setLayout(menuBarLayout);
        menuBarLayout.setHorizontalGroup(
            menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
        );
        menuBarLayout.setVerticalGroup(
            menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
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
        // Set save and cancel btn to be clickable:
        this.setSaveCancelBtn(true);
        // Set user input to be editable
        this.setUserInputComponents(true);
        // Disable update delete btn
        this.setEditDeleteBtn(false);
        // Clear user input
        this.clearUserInput();
        // Set selectedId to 0 (for action add)
        selectedId = 0;
        
        // Pastikan dropdown pegawai penginput ditampilkan:
        lblPegawai.setVisible(true);
        ddPegawai.setVisible(true);
    }//GEN-LAST:event_addBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // Set save and cancel btn to be clickable:
        this.setSaveCancelBtn(true);
        // Set user input to be editable
        this.setUserInputComponents(true);
        // DO NOT re-set the selectedId as we've already set it once the user clicked on the table row
    }//GEN-LAST:event_editBtnActionPerformed

    private void tableTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTransaksiMouseClicked
        // Reset inputs:
        clearUserInput();
        
        // Set edit and delete button to be selectable:
        setEditDeleteBtn(true);
        
        // Disable save cancel btn
        setSaveCancelBtn(false);
        
        // In case the user input is already ON, turn it off
        setUserInputComponents(false);
        
        // Get selected data:
        Transaksi selectedT = (Transaksi) getTableSelectedObject(tableTransaksi);
        
        // Set the selected id --> having `0` means that we will be adding new data, not updating
        selectedId = selectedT.getId();
        
        // Display to input:
        for(int i=0; i<listC.size(); i++) {
            if(listC.get(i).getId() == selectedT.getId()) {
                ddCustomer.setSelectedIndex(i);
                break;
            }
        }
        
        inputTglMasuk.setDateTimeStrict(selectedT.getTglMasuk());
        inputTglSelesai.setDateTimeStrict(selectedT.getTglSelesai());
        inputTglAmbil.setDateTimeStrict(selectedT.getTglAmbil());
        
        // Right panel:
        inputBeratPakaian.setValue(selectedT.getBeratPakaian());
        inputBeratSelimut.setValue(selectedT.getBeratSelimut());
        inputBeratBoneka.setValue(selectedT.getBeratBoneka());
        
        ddKecepatan.setSelectedItem(selectedT.getTipeLayanan().getString("speed"));
        for(int i=0; i<selectedT.getTipeLayanan().getJSONArray("facility").length(); i++) {
            String facility = selectedT.getTipeLayanan().getJSONArray("facility").getString(i).toLowerCase();
            switch(facility) {
                case "cuci":
                    cbFCuci.setSelected(true);
                    break;
                case "setrika":
                    cbFSetrika.setSelected(true);
                    break;
            }
        }
        
        // job history button:
        selectedTransaksi = selectedT;
        jobHistoryBtn.setEnabled(true);
    }//GEN-LAST:event_tableTransaksiMouseClicked

    private void beratStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_beratStateChanged
        // Berat berubah, recheck harga
        this.setTotalHarga();
    }//GEN-LAST:event_beratStateChanged

    private void ddcbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ddcbItemStateChanged
        // DD dan CB berubah, recheck harga dan tgl ambil
        this.setTglSelesai();
        this.setTotalHarga();
    }//GEN-LAST:event_ddcbItemStateChanged

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // Set save and cancel btn to NOT be clickable:
        this.setSaveCancelBtn(false);
        // Set user input to NOT be editable
        this.setUserInputComponents(false);
        // Clear user input
        this.clearUserInput();
        // Disable edit delete btn
        this.setEditDeleteBtn(false);
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int userAns = JOptionPane.showConfirmDialog(rootPane, "Apakah Anda yakin ingin menghapus data ini?\r\nData yang sudah dihapus tidak dapat lagi dipulihkan.", "CFL - Konfirmasi tindakan", JOptionPane.YES_NO_OPTION);
        if(userAns == 0) {
            // proceed
            tCTRL.deleteDataTransaksi(selectedId);
            JOptionPane.showMessageDialog(this, "Berhasil menghapus data transaksi!", "CFL - Notification", JOptionPane.INFORMATION_MESSAGE);
            
            // reload table
            getTableData(searchInput.getText(), false);
        } else {
            // Batal menambahkan/memperbarui data
            JOptionPane.showMessageDialog(this, "Batal melakukan tindakan!", "CFL - Notification", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        String confirmDialogText;
        if(selectedId == 0) {
            // Attempted action: ADD
            confirmDialogText = "Apakah Anda yakin ingin menambahkan data baru?";
        } else {
            // Attempted action: UPDATE
            confirmDialogText = "Apakah Anda yakin ingin memperbarui data?";
        }
        int userAns = JOptionPane.showConfirmDialog(rootPane, confirmDialogText, "CFL - Konfirmasi tindakan", JOptionPane.YES_NO_OPTION);
        // userAns will return 0 if user answers YES, 1 if user answers NO
        if(userAns == 0) {
            try {
                inputExceptionCheck();
                
                Customer selC = (Customer) ddCustomer.getSelectedItem();
                
                Transaksi inT = new Transaksi(
                    // selectedId will default to "0" if user wants to add new data, else will contain currently selected item
                    selectedId,
                    inputTglMasuk.getDateTimeStrict(),
                    inputTglSelesai.getDateTimeStrict(),
                    inputTglAmbil.getDateTimePermissive(),
                    getTipeLayanan(),
                    Float.parseFloat(inputBeratPakaian.getValue().toString()),
                    Float.parseFloat(inputBeratSelimut.getValue().toString()),
                    Float.parseFloat(inputBeratBoneka.getValue().toString()),
                    selC
                );
                
                if(selectedId == 0) {
                    // Action tambah, karena selectedId = 0
                    Pegawai inP = (Pegawai) ddPegawai.getSelectedItem();
                    tCTRL.insertDataTransaksi(inT, inP);
                    JOptionPane.showMessageDialog(this, "Berhasil menambahkan data transaksi!", "CFL - Notification", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Action UPDATE
                    tCTRL.updateDataTransaksi(inT);
                    JOptionPane.showMessageDialog(this, "Berhasil memperbarui data transaksi!", "CFL - Notification", JOptionPane.INFORMATION_MESSAGE);
                }
                
                // Resets the form
                // Set save and cansel btn to be clickable:
                this.setSaveCancelBtn(false);
                // Set user input to be editable
                this.setUserInputComponents(false);
                
                // Reset user input
                clearUserInput();
                
                // Refresh table
                getTableData("", false);
            } catch (InputKosongException e) {
                JOptionPane.showMessageDialog(this, e.toString());
            } catch (TanggalAmbilInvalidException e) {
                JOptionPane.showMessageDialog(this, e.toString());
            }
        } else {
            // Batal menambahkan/memperbarui data
            JOptionPane.showMessageDialog(this, "Batal melakukan tindakan!", "CFL - Notification", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        getTableData(searchInput.getText(), true);
        searchInput.setText("");
    }//GEN-LAST:event_searchBtnActionPerformed

    private void btnResetTglMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTglMasukActionPerformed
        inputTglMasuk.setDateTimeStrict(null);
    }//GEN-LAST:event_btnResetTglMasukActionPerformed

    private void btnResetTglAmbilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTglAmbilActionPerformed
        inputTglAmbil.setDateTimeStrict(null);
    }//GEN-LAST:event_btnResetTglAmbilActionPerformed

    private void customerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerBtnActionPerformed
        CustomerView cv = new CustomerView();
        this.dispose();
        cv.setVisible(true);
    }//GEN-LAST:event_customerBtnActionPerformed

    private void pegawaiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pegawaiBtnActionPerformed
        PegawaiView2 pv = new PegawaiView2();
        this.dispose();
        pv.setVisible(true);
    }//GEN-LAST:event_pegawaiBtnActionPerformed

    private void inputBeratPakaianComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_inputBeratPakaianComponentAdded
        System.out.println("\nComponent Added brokkkk\n");
    }//GEN-LAST:event_inputBeratPakaianComponentAdded

    private void inputBeratPakaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputBeratPakaianMouseClicked
        System.out.println("\nComponent Added brokkkk\n");
    }//GEN-LAST:event_inputBeratPakaianMouseClicked

    private void jobHistoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jobHistoryBtnActionPerformed
        JobHistoryView jhv = new JobHistoryView(selectedTransaksi, this);
        jhv.setVisible(true);
    }//GEN-LAST:event_jobHistoryBtnActionPerformed

    private void btnSetDTMasukNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetDTMasukNowActionPerformed
        inputTglMasuk.datePicker.setDateToToday();
        inputTglMasuk.timePicker.setTimeToNow();
    }//GEN-LAST:event_btnSetDTMasukNowActionPerformed

    private void btnSetDTAmbilNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetDTAmbilNowActionPerformed
        inputTglAmbil.datePicker.setDateToToday();
        inputTglAmbil.timePicker.setTimeToNow();
    }//GEN-LAST:event_btnSetDTAmbilNowActionPerformed

    private void logoAreaMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_logoAreaMouseClicked
        MainMenuView MMV = new MainMenuView();
        this.dispose();
        MMV.setVisible(true);
    }// GEN-LAST:event_logoAreaMouseClicked

    private void transaksiBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_transaksiBtnActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_transaksiBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransaksiView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TransaksiView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnResetTglAmbil;
    private javax.swing.JButton btnResetTglMasuk;
    private javax.swing.JButton btnSetDTAmbilNow;
    private javax.swing.JButton btnSetDTMasukNow;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JCheckBox cbFCuci;
    private javax.swing.JCheckBox cbFSetrika;
    private javax.swing.JButton customerBtn;
    private javax.swing.JComboBox<Customer> ddCustomer;
    private javax.swing.JComboBox<String> ddKecepatan;
    private javax.swing.JComboBox<Pegawai> ddPegawai;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JSpinner inputBeratBoneka;
    private javax.swing.JSpinner inputBeratPakaian;
    private javax.swing.JSpinner inputBeratSelimut;
    private javax.swing.JPanel inputPanel;
    private com.github.lgooddatepicker.components.DateTimePicker inputTglAmbil;
    private com.github.lgooddatepicker.components.DateTimePicker inputTglMasuk;
    private com.github.lgooddatepicker.components.DateTimePicker inputTglSelesai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JButton jobHistoryBtn;
    private javax.swing.JLabel lblHargaBoneka;
    private javax.swing.JLabel lblHargaPakaian;
    private javax.swing.JLabel lblHargaSelimut;
    private javax.swing.JLabel lblPegawai;
    private javax.swing.JLabel logoArea;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel manuBarDetailPanel;
    private javax.swing.JPanel menuBar;
    private javax.swing.JLabel namaDetailView;
    private javax.swing.JLabel namaFooter;
    private javax.swing.JPanel namaView;
    private javax.swing.JPanel navBar;
    private javax.swing.JLabel outTotalHarga;
    private javax.swing.JButton pegawaiBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JScrollPane scrollTabelPanel;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchInput;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTable tableTransaksi;
    private javax.swing.JButton transaksiBtn;
    // End of variables declaration//GEN-END:variables
}
