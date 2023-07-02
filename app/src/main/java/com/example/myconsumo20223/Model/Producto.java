package com.example.myconsumo20223.Model;
public class Producto {
    private String codProducto;
    private String nomProducto;
    private double preProducto;
    private int stkProducto;
    private String estaProducto;



    public Producto(String codProducto, String nomProducto, double preProducto, int stkProducto, String estaProducto) {
        this.codProducto = codProducto;
        this.nomProducto = nomProducto;
        this.preProducto = preProducto;
        this.stkProducto = stkProducto;
        this.estaProducto = estaProducto;
    }

    public Producto(int parseInt, String toString, double parseDouble, int parseInt1) {
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public double getPreProducto() {
        return preProducto;
    }

    public void setPreProducto(float preProducto) {
        this.preProducto = preProducto;
    }

    public int getStkProducto() {
        return stkProducto;
    }

    public void setStkProducto(int stkProducto) {
        this.stkProducto = stkProducto;
    }

    public String getEstaProducto() {
        return estaProducto;
    }

    public void setEstaProducto(String estaProducto) {
        this.estaProducto = estaProducto;
    }
}
