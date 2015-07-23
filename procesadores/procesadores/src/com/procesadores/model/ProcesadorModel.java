/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.procesadores.model;

import java.util.Date;

/**
 *
 * @author Andres
 */
public class ProcesadorModel {

    private int idProcesador;
    private String marca;
    private String modelo;
    private Date fechaVenta;
    private float precio;
    private int entregado;

    public ProcesadorModel() {
    }

    public ProcesadorModel(int idProcesador, String marca, String modelo, Date fechaVenta, float precio, boolean entregado) {
        this.idProcesador = idProcesador;
        this.marca = marca;
        this.modelo = modelo;
        this.fechaVenta = fechaVenta;
        this.precio = precio;
        setEntregado(entregado);
    }

    public int getIdProcesador() {
        return idProcesador;
    }

    public boolean getEntregado() {
        if (entregado == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int getEntregado2() {
        if (getEntregado() == true) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setEntregado(boolean entregado) {
        if (entregado) {
            this.entregado = 1;
        } else {
            this.entregado = 0;
        }
    }

    public void setIdProcesador(int idProcesador) {
        this.idProcesador = idProcesador;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

}
