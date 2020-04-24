package com.example.listado.empresas;

import java.io.Serializable;

public class Empresa implements Serializable {
    private String nombre,correo, tipo;
    private int telefono,id;

    public Empresa(String nombre, String correo, String tipo, int telefono, int id) {
        this.nombre = nombre;
        this.correo = correo;
        this.tipo = tipo;
        this.telefono = telefono;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
