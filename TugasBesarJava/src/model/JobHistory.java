package model;

public class JobHistory {
    private Pegawai pegawai;
    private String tglLog;
    private String aktivitas;

    public JobHistory(Pegawai pegawai, String tglLog, String aktivitas){
        this.pegawai = pegawai;
        this.tglLog = tglLog;
        this.aktivitas = aktivitas;
    }
    
    public Pegawai getPegawai(){
        return pegawai;
    }
    
    public void setPegawai(Pegawai pegawai){
        this.pegawai = pegawai;
    }
    
    public String gettglLog(){
        return tglLog;
    }
    
    public void settglLog(String tglLog){
        this.tglLog = tglLog;
    }
    
    public String getAktivitas(){
        return aktivitas;
    }
    
    public void setAktivitas(String aktivitas){
        this.aktivitas = aktivitas;
    }
}
