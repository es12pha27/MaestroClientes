package com.lab02.maestroclientes.entidades;

public class Cliente {
    private int codigo;
    private String nombre;
    private String  ruc;
    private int codigozona;
    private int codigotipocliente;
    private String estado;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public int getCodigozona() {
        return codigozona;
    }

    public void setCodigozona(int codigozona) {
        this.codigozona = codigozona;
    }

    public int getCodigotipocliente() {
        return codigotipocliente;
    }

    public void setCodigotipocliente(int codigotipocliente) {
        this.codigotipocliente = codigotipocliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
