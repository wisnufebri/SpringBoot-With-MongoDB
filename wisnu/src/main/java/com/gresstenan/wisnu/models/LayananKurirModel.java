package com.gresstenan.wisnu.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "layanankurir")
public class LayananKurirModel{
    
    private String id;
    private String nama;
    private String harga;
    private String estimasi;
    private KurirModel kurirModel;

    public LayananKurirModel() {
    }

    public KurirModel getKurirModel() {
        return kurirModel;
    }

    public void setKurirModel(KurirModel kurirModel) {
        this.kurirModel = kurirModel;
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

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getEstimasi() {
        return estimasi;
    }

    public void setEstimasi(String estimasi) {
        this.estimasi = estimasi;
    }
}
