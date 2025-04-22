package com.amaris.apiFondos.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Fondo {
    private String id;
    private String nombre;
    private int montoMinimo;
    private String categoria;

    public Fondo(String id, String nombre, int montoMinimo, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.montoMinimo = montoMinimo;
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMontoMinimo() {
        return montoMinimo;
    }

    public String getCategoria() {
        return categoria;
    }
}
