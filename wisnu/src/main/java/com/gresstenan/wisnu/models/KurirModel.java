package com.gresstenan.wisnu.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "kurir")
public class KurirModel {

    @Id
    @Indexed
    private String id;
    private String nama;
    private int resi;

    public KurirModel() {
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

    public int getResi() {
        return resi;
    }

    public void setResi(int resi) {
        this.resi = resi;
    }
}
