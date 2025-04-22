package com.amaris.apiFondos.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Usuario {

    private String id;
    private String nombre;
    private int saldoDisponible;

    public Usuario(String id, String nombre, int saldoDisponible) {
        this.id = id;
        this.nombre = nombre;
        this.saldoDisponible = saldoDisponible;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSaldoDisponible(int saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSaldoDisponible() {
        return saldoDisponible;
    }
}
