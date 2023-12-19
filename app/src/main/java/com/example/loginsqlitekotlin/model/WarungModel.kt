package com.example.loginsqlitekotlin.model

class WarungModel {
    private var idwarung: Int = 0
    private var namawarung: String = ""
    private var logo: String = ""
    private var gambar: String = ""

    constructor(idwarung: Int, namawarung: String, logo: String, gambar: String) {
        this.idwarung = idwarung
        this.namawarung = namawarung
        this.logo = logo
        this.gambar = gambar
    }

    fun getIdwarung(): Int {
        return idwarung
    }

    fun setIdwarung(idwarung: Int) {
        this.idwarung = idwarung
    }

    fun getNamawarung(): String {
        return namawarung
    }

    fun setNamawarung(namawarung: String) {
        this.namawarung = namawarung
    }

    fun getLogo(): String {
        return logo
    }

    fun setLogo(logo: String) {
        this.logo = logo
    }

    fun getGambar(): String {
        return gambar
    }

    fun setGambar(gambar: String) {
        this.gambar = gambar
    }


}