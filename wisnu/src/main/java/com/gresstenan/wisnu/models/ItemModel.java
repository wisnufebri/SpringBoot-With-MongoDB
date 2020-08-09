package com.gresstenan.wisnu.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "items")
public class ItemModel {
    @Id
    private String id;
    
    private String nama;
    private int stock;
    private int harga;
    private String description;
    private boolean terjual;
    private int berat;



    public ItemModel() {
    }

    public ItemModel(String nama, int stock, int harga, String description, boolean terjual, int berat) {
        this.nama = nama;
        this.stock = stock;
        this.harga = harga;
        this.description = description;
        this.terjual = terjual;
        this.berat = berat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTerjual() {
        return terjual;
    }

    public void setTerjual(boolean terjual) {
        this.terjual = terjual;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }
}