package com.gresstenan.wisnu.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userdetail")
public class UserDetail {

    @Id
    private String alamat;
    private int phone1;
    private int phone2;
    private String type;
    private String jeniskelamin;
    private int kodepos;

    public UserDetail() {
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getPhone1() {
        return phone1;
    }

    public void setPhone1(int phone1) {
        this.phone1 = phone1;
    }

    public int getPhone2() {
        return phone2;
    }

    public void setPhone2(int phone2) {
        this.phone2 = phone2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public int getKodepos() {
        return kodepos;
    }

    public void setKodepos(int kodepos) {
        this.kodepos = kodepos;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "alamat='" + alamat + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", type='" + type + '\'' +
                ", jeniskelamin='" + jeniskelamin + '\'' +
                ", kodepos=" + kodepos +
                '}';
    }
}