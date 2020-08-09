package com.gresstenan.wisnu.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "list")
public class ListItem {
    @Id
    private String id;

    private String nama;
    private String description;
    private int harga;
    private int stok;
    private int berat;
    private boolean published;

    public ListItem() {
    }

    public ListItem(String nama, String description, int harga, int stok, int berat, boolean published) {
        this.nama = nama;
        this.description = description;
        this.harga = harga;
        this.stok = stok;
        this.berat = berat;
        this.published = published;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean isPublished) {
        this.published = isPublished;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", title=" + nama + ", desc=" + description + ", published=" + published + "]";
    }
}