package com.gresstenan.wisnu.models;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "layanankurir")
public class LayananKurirModel {

    private String id;
    private String nama;
    private String harga;
    private String estimasi;

    @DBRef
    private Set<KurirModel> kurir = new HashSet<>();

    public LayananKurirModel() {
    }

    public Set<KurirModel> getKurir() {
        return kurir;
    }

    public void setKurir(Set<KurirModel> kurir) {
        this.kurir = kurir;
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
