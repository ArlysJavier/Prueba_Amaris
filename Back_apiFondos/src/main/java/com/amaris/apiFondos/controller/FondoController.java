package com.amaris.apiFondos.controller;

import com.amaris.apiFondos.dto.SuscripcionRequest;
import com.amaris.apiFondos.model.Fondo;
import com.amaris.apiFondos.model.Transaccion;
import com.amaris.apiFondos.service.FondoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fondos")
//@RequiredArgsConstructor
public class FondoController {

    private final FondoService fondoService;

    public FondoController(FondoService fondoService) {
        this.fondoService = fondoService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping
    public List<Fondo> getFondos() {
        return fondoService.listarFondos();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/suscribir")
    public ResponseEntity<String> suscribir(@Valid @RequestBody SuscripcionRequest request) {
        return ResponseEntity.ok(fondoService.suscribirAFondo(request.getIdFondo(), request.getMedioNotificacion()));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/cancelar/{idFondo}")
    public ResponseEntity<String> cancelar(@PathVariable String idFondo) {
        return ResponseEntity.ok(fondoService.cancelarFondo(idFondo));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/historial")
    public List<Transaccion> historial() {
        return fondoService.historial();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/saldo")
    public int saldo() {
        return fondoService.saldoDisponible();
    }
}
