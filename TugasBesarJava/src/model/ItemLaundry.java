package model;

public class ItemLaundry {
    private int index;
    private String jenis;
    private double berat;

    public ItemLaundry(int index, String jenis, double berat) {
        this.index = index;
        this.jenis = jenis;
        this.berat = berat;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
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
