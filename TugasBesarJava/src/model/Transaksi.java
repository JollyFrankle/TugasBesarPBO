package model;

import java.util.*;

public class Transaksi {
    private int idTransaksi;
    private String status;
    private String tglMasuk;
    private String tglSelesai;
    private String tglAmbil;
    private double totalBiaya;
    private String tipeLayanan;
    private List<ItemLaundry> listItem;
    private List<JobHistory> jobHistory;
    Customer customer;

    public Transaksi(String status, String tglMasuk, String tglSelesai, String tglAmbil, double totalBiaya, String tipeLayanan, Customer customer){
        this.status = status;
        this.tglMasuk = tglMasuk;
        this.tglSelesai = tglSelesai;
        this.tglAmbil = tglAmbil;
        this.totalBiaya = totalBiaya;
        this.tipeLayanan = tipeLayanan;
        this.customer = customer;

        List<ItemLaundry> listItem = new ArrayList<ItemLaundry>();
        List<JobHistory> jobHistory = new ArrayList<JobHistory>();
    }

    public void hitungBiaya(){

    }

    public double hitungDurasi(){
        return 1;
    }

    public double hitungBonus(){
        return 1;
    }

    public void hitungBerat(){

    }
}
