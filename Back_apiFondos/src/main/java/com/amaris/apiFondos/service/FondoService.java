package com.amaris.apiFondos.service;

import com.amaris.apiFondos.model.Fondo;
import com.amaris.apiFondos.model.Transaccion;
import com.amaris.apiFondos.model.Usuario;
import com.amaris.apiFondos.repository.FondoRepository;
import com.amaris.apiFondos.repository.TransaccionRepository;
import com.amaris.apiFondos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FondoService {

    private final FondoRepository fondoRepository;
    private final TransaccionRepository transaccionRepository;
    private final UsuarioRepository usuarioRepository;

    public FondoService(FondoRepository fondoRepository, TransaccionRepository transaccionRepository, UsuarioRepository usuarioRepository) {
        this.fondoRepository = fondoRepository;
        this.transaccionRepository = transaccionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Fondo> listarFondos() {
        return fondoRepository.findAll();
    }

    public String suscribirAFondo(String idFondo, String medio) {
        Fondo fondo = fondoRepository.findById(idFondo)
                .orElseThrow(() -> new RuntimeException("Fondo no encontrado"));

        Usuario usuario = usuarioRepository.getUsuario();

        if (usuario.getSaldoDisponible() < fondo.getMontoMinimo()) {
            return "No tiene saldo disponible para vincularse al fondo " + fondo.getNombre();
        }

        usuario.setSaldoDisponible(usuario.getSaldoDisponible() - fondo.getMontoMinimo());

        Transaccion t = new Transaccion(UUID.randomUUID().toString(), "APERTURA", fondo.getId(), fondo.getNombre(), fondo.getMontoMinimo(), LocalDateTime.now());
        transaccionRepository.save(t);

        System.out.println("Notificación enviada por " + medio);

        return "Suscripción realizada con éxito al fondo " + fondo.getNombre();
    }

    public String cancelarFondo(String idFondo) {
        Fondo fondo = fondoRepository.findById(idFondo)
                .orElseThrow(() -> new RuntimeException("Fondo no encontrado para cancelación"));

        Usuario usuario = usuarioRepository.getUsuario();
        usuario.setSaldoDisponible(usuario.getSaldoDisponible() + fondo.getMontoMinimo());

        Transaccion t = new Transaccion(
                UUID.randomUUID().toString(),
                "CANCELACION",
                fondo.getId(),
                fondo.getNombre(),
                fondo.getMontoMinimo(),
                LocalDateTime.now()
        );

        transaccionRepository.save(t);

        return "Cancelación realizada con éxito del fondo " + fondo.getNombre();
    }


    public List<Transaccion> historial() {
        return transaccionRepository.findAll();
    }

    public int saldoDisponible() {
        return usuarioRepository.getUsuario().getSaldoDisponible();
    }

}
