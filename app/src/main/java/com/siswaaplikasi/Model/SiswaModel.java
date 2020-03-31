package com.siswaaplikasi.Model;

import java.io.Serializable;

public class SiswaModel implements Serializable {
    private int id;
    private int nis;
    private String nama;
    private  String jenis_kelamin;
    private String status;
    private String tgl_lahir;

    public void SiswaModel(int id,int nis, String nama , String jenis_kelamin, String status , String tgl_lahir){
        this.id = id;
        this.nis = nis;
        this.nama = nama;
        this.jenis_kelamin = jenis_kelamin;
        this.status = status;
        this.tgl_lahir = tgl_lahir;
    }

    public int getNis() {
        return nis;
    }

    public void setNis(int nis) {
        this.nis = nis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
