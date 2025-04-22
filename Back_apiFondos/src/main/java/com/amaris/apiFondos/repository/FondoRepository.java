package com.amaris.apiFondos.repository;

import com.amaris.apiFondos.model.Fondo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FondoRepository {

    private final List<Fondo> fondos = List.of(
            new Fondo("1", "FPV_EL CLIENTE_RECAUDADORA", 75000, "FPV"),
            new Fondo("2", "FPV_EL CLIENTE_ECOPETROL", 125000, "FPV"),
            new Fondo("3", "DEUDAPRIVADA", 50000, "FIC"),
            new Fondo("4", "FDO-ACCIONES", 250000, "FIC"),
            new Fondo("5", "FPV_EL CLIENTE_DINAMICA", 100000, "FPV")
    );

    public List<Fondo> findAll() {
        return fondos;
    }

    public Optional<Fondo> findById(String id) {
        return fondos.stream().filter(f -> f.getId().equals(id)).findFirst();
    }

}
