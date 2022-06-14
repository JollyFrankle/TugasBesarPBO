package model;

public class Pegawai {
    private String namaPegawai;
    private String id;
    private String tglLahir;
    private String noHP;
    private String jobDesc;

    public Pegawai(String id, String namaPegawai, String tglLahir, String noHP, String jobDesc){
        this.namaPegawai = namaPegawai;
        this.id = id;
        this.tglLahir = tglLahir;
        this.noHP = noHP;
        this.jobDesc = jobDesc;
    }

    public String getNama() {
        return this.namaPegawai;
    }

    public void setNama(String namaPegawai) {
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
    
    public String showDataPegawai(){
        return id + " | " + namaPegawai + " | " + tglLahir + " | " + noHP + " | " + noHP + " | " + jobDesc;
    }

    public int hitungGajiPegawai(){     // JobDesc menggunakan DropDown
        switch(jobDesc){
            case "Pencuci Pakaian":     // Pilihan 1 : Pencuci Pakaian
                return 2500000;
            case "Penyetrika Pakaian":  // Pilihan 2 : Penyetrika Pakaian
                return 2000000;
            case "Packing Pakaian":     // Pilihan 3 : Packing Pakaian
                return 1000000;
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return this.namaPegawai;
    }
}
