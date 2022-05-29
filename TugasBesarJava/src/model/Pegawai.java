package model;

public abstract class Pegawai {
    protected String namaPegawai;
    protected String idPegawai;
    protected String tglLahir;
    protected String noHandphonePegawai;

    public Pegawai(String namaPegawai, String idPegawai, String tglLahir, String noHandphonePegawai){
        this.namaPegawai = namaPegawai;
        this.idPegawai = idPegawai;
        this.tglLahir = tglLahir;
        this.noHandphonePegawai = noHandphonePegawai;
    }

    public abstract void showDataPegawai();
    public abstract void hitungGajiPegawai();
}
