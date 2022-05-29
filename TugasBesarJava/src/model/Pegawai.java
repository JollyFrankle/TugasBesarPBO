package model;

import java.net.SocketTimeoutException;

public class Pegawai {
    private String namaPegawai;
    private String id;
    private String tglLahir;
    private String noHP;
    private String jobDesc;

    public String getNamaPegawai() {
        return this.namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTglLahir() {
        return this.tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getNoHP() {
        return this.noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getJobDesc() {
        return this.jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public Pegawai(String namaPegawai, String id, String tglLahir, String noHP, String jobDesc){
        this.namaPegawai = namaPegawai;
        this.id = id;
        this.tglLahir = tglLahir;
        this.noHP = noHP;
        this.jobDesc = jobDesc;
    }
    
    

    public void showDataPegawai(){
        System.out.println("Nama Pegawai: "+namaPegawai);
        System.out.println("ID Pegawai: "+id);
        System.out.println("Tanggal Lahir: "+tglLahir);
        System.out.println("No Handphone: "+noHP);
        System.out.println("Job Desc: "+jobDesc);
    }

    public void hitungGajiPegawai(){
        
    }
}
