package com.example.kontak.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class kontak implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 13)
    private int id;

    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String nama;

    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String email;
    @Basic(optional = false)
    @Column(nullable = false, length = 13)
    private String nohp;


    public kontak(int id, String nama, String email, String nohp) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.nohp = nohp;
    }

    public kontak() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
