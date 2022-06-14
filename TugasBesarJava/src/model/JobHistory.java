package model;

import java.time.LocalDateTime;

public class JobHistory {
    private int id;
    private int idTransaksi; // <-- sebenarnya tidak berguna karena tidak aakn ditampilkan, tidak bisa diedit juga
    private Pegawai pegawai;
    private LocalDateTime tglLog;
    private String aktivitas;

    public JobHistory(int id, int idTransaksi, Pegawai pegawai, LocalDateTime tglLog, String aktivitas) {
        // Constructor untuk insert/update
        this.id = id;
        this.idTransaksi = idTransaksi;
        this.pegawai = pegawai;
        this.tglLog = tglLog;
        this.aktivitas = aktivitas;
    }
    
    public JobHistory(int id, int idTransaksi, Pegawai pegawai, String tglLog, String aktivitas) {
        // Constructor saat select dari db:
        this.id = id;
        this.idTransaksi = idTransaksi;
        this.pegawai = pegawai;
        this.tglLog = LocalDateTime.parse(tglLog, Transaksi.DEFAULT_DTF);
        this.aktivitas = aktivitas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }

    public LocalDateTime getTglLog() {
        return tglLog;
    }

    public void setTglLog(LocalDateTime tglLog) {
        this.tglLog = tglLog;
    }

    public String getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(String aktivitas) {
        this.aktivitas = aktivitas;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    
}
