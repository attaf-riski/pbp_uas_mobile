package com.example.loginsqlitekotlin.model

class RoleModel{

    private var idrole: Int = 0;
    private var role: String = "";
    private var status: String = "";

    constructor(idrole: Int, role: String, status: String) {
        this.idrole = idrole
        this.role = role
        this.status = status
    }
    fun getIdRole(): Int{
        return idrole;
    }

    fun setIdRole(idrole: Int){
        this.idrole = idrole;
    }
    fun getRole(): String{
        return role;
    }

    fun setRole(role: String){
        this.role = role;
    }
    fun getStatus(): String{
        return status;
    }

    fun setStatus(status: String){
        this.status = status;
    }




}