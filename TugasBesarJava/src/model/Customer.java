package model;

public class Customer {
    private int id;
    private String nama;
    private String alamat;
    private String noHP;
    
    public Customer(int id, String nama, String alamat, String noHP){
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.noHP = noHP;
    }
    
    public Customer(String nama, String alamat, String noHP){
        this.nama = nama;
        this.alamat = alamat;
        this.noHP = noHP;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNama(){
        return nama;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public String getAlamat(){
        return alamat;
    }
    
    public void setAlamat(String alamat){
        this.alamat = alamat;
    }
    
    public String getNoHP(){
        return noHP;
    }
    
    public void setNoHP(String noHP){
        this.noHP = noHP;
    }
    
    public String showDataCustomer(){
        return id + " | " + nama + " | " + alamat + " | " + noHP;
    }
}
