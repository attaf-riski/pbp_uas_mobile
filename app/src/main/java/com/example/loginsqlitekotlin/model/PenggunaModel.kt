package com.example.loginsqlitekotlin.model

class PenggunaModel {
    private var idpengguna: Int = 0;
    private var username: String = "";
    private var password: String = "";
    private var namapengguna: String = "";
    private var idrole: Int = 0;
    private var status: String = "";
    private var foto: String = "";

    constructor(idpengguna: Int, username: String, password: String, namapengguna: String, idrole: Int, status: String, foto: String){
        this.idpengguna = idpengguna;
        this.username = username;
        this.password = password;
        this.namapengguna = namapengguna;
        this.idrole = idrole;
        this.status = status;
        this.foto = foto;
    }

    fun getIdPengguna(): Int{
        return idpengguna;
    }

    fun setIdPengguna(idpengguna: Int){
        this.idpengguna = idpengguna;
    }

    fun getUsername(): String{
        return username;
    }

    fun setUsername(username: String){
        this.username = username;
    }

    fun getPassword(): String{
        return password;
    }

    fun setPassword(password: String){
        this.password = password;
    }

    fun getNamaPengguna(): String{
        return namapengguna;
    }

    fun setNamaPengguna(namapengguna: String){
        this.namapengguna = namapengguna;
    }

    fun getIdRole(): Int{
        return idrole;
    }

    fun setIdRole(idrole: Int){
        this.idrole = idrole;
    }

    fun getStatus(): String{
        return status;
    }

    fun setStatus(status: String){
        this.status = status;
    }

    fun getFoto(): String{
        return foto;
    }

    fun setFoto(foto: String){
        this.foto = foto;
    }
}