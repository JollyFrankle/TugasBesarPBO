package model;

public class ItemLaundry {
    private String jenis;
    private int qty;
    private double berat;

    public ItemLaundry(String jenis, int qty, double berat) {
        this.jenis = jenis;
        this.qty = qty;
        this.berat = berat;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getBerat() {
        return berat;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }

    public double getHargaSatuan(String kecepatan) {
        switch(kecepatan) {
            case "REGULAR":
                switch(this.jenis) {
                    case "Pakaian":
                        return 6000.0;
                    case "Selimut":
                        return 10000.0;
                    case "Boneka":
                        return 25000.0;
                }
                break;
            case "EXPRESS":
                switch(this.jenis) {
                    case "Pakaian":
                        return 10000.0;
                    case "Selimut":
                        return 15000.0;
                    case "Boneka":
                        return 35000.0;
                }
                break;
        }
        return 0;
    }

    
}
