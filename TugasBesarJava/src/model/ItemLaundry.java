package model;

public class ItemLaundry {
    private String jenis;
    private int qty;
    private double berat;

    public ItemLaundry(String jenis, int qty, double berat){
        this.jenis = jenis;
        this.qty = qty;
        this.berat = berat;
    }
    
    public String getJenis(){
        return jenis;
    }
    
    public void setJenis(String jenis){
        this.jenis = jenis;
    }
    
    public int getQty(){
        return qty;
    }
    
    public void setQty(int qty){
        this.qty = qty;
    }
    
    public double getBerat(){
        return berat;
    }
    
    public void setBerat(double berat){
        this.berat = berat;
    }

    // BELUM ADA METHOD
}
