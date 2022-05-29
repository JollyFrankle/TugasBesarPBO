package model;

public class JobHistory {
    private Transaksi transaksi;
    private Pegawai pegawai;
    private String tglLog;
    private String kegiatan;

    public JobHistory(Transaksi transaksi, Pegawai pegawai, String tglLog, String kegiatan){
        this.transaksi = transaksi;
        this.pegawai = pegawai;
        this.tglLog = tglLog;
        this.kegiatan = kegiatan;
    }

    public void showDataPegawai(){

    }

    public void hitungGajiPegawai(){
        
    }
}
