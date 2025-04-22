package com.amaris.apiFondos.repository;

import com.amaris.apiFondos.model.Transaccion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransaccionRepository {

    private final List<Transaccion> transacciones = new ArrayList<>();

    public void save(Transaccion t) {
        transacciones.add(t);
    }

    public List<Transaccion> findAll() {
        return transacciones;
    }
}
