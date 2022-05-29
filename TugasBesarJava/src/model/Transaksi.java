package model;

import java.util.*;

public class Transaksi {
    private int idTransaksi;
    private String status;
    private String tglMasuk;
    private String tglSelesai;
    private String tglAmbil;
    private String tipeLayanan;
    private List<ItemLaundry> listItem;
    private List<JobHistory> listHistory;
    private Customer customer;

    public Transaksi(int idTransaksi, String status, String tglMasuk, String tglSelesai, String tglAmbil, String tipeLayanan, Customer customer) {
        this.idTransaksi = idTransaksi;
        this.status = status;
        this.tglMasuk = tglMasuk;
        this.tglSelesai = tglSelesai;
        this.tglAmbil = tglAmbil;
        this.tipeLayanan = tipeLayanan;
        this.customer = customer;
    }

    public int getIdTransaksi() {
        return this.idTransaksi;
    }

    public void setIdTransaksi(int input) {
        this.idTransaksi = input;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String input) {
        this.status = input;
    }

    public String getTglMasuk() {
        return this.tglMasuk;
    }

    public void setTglMasuk(String input) {
        this.tglMasuk = input;
    }

    public String getTglSelesai() {
        return this.tglSelesai;
    }

    public void setTglSelesai(String input) {
        this.tglSelesai = input;
    }

    public String getTglAmbil() {
        return this.tglAmbil;
    }

    public void setTglAmbil(String input) {
        this.tglAmbil = input;
    }

    public String getTipeLayanan() {
        return this.tipeLayanan;
    }

    public void setTipeLayanan(String input) {
        this.tipeLayanan = input;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer input) {
        this.customer = input;
    }

    public void setListItem(List<ItemLaundry> listItem){
        this.listItem = listItem;
    }

    public void setJobHistory(List<JobHistory> listHistory){
        this.listHistory = listHistory;
    }
}
