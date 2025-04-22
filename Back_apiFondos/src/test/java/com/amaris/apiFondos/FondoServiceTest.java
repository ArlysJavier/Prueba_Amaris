package com.amaris.apiFondos;

import com.amaris.apiFondos.model.Fondo;
import com.amaris.apiFondos.model.Transaccion;
import com.amaris.apiFondos.model.Usuario;
import com.amaris.apiFondos.repository.FondoRepository;
import com.amaris.apiFondos.repository.TransaccionRepository;
import com.amaris.apiFondos.repository.UsuarioRepository;
import com.amaris.apiFondos.service.FondoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FondoServiceTest {

    private FondoRepository fondoRepo;
    private TransaccionRepository transaccionRepo;
    private UsuarioRepository usuarioRepo;
    private FondoService fondoService;

    @BeforeEach
    public void setUp() {
        fondoRepo = mock(FondoRepository.class);
        transaccionRepo = mock(TransaccionRepository.class);
        usuarioRepo = mock(UsuarioRepository.class);
        fondoService = new FondoService(fondoRepo, transaccionRepo, usuarioRepo);
    }

    @Test
    public void suscripcionExitosaTest() {
        Fondo fondo = new Fondo("1", "Fondo Prueba", 100000, "FPV");
        Usuario usuario = new Usuario("1", "Usuario Test", 200000);

        when(fondoRepo.findById("1")).thenReturn(Optional.of(fondo));
        when(usuarioRepo.getUsuario()).thenReturn(usuario);

        String resultado = fondoService.suscribirAFondo("1", "EMAIL");

        assertEquals("Suscripción realizada con éxito al fondo Fondo Prueba", resultado);
        assertEquals(100000, usuario.getSaldoDisponible());
        verify(transaccionRepo, times(1)).save(any(Transaccion.class));
    }

    @Test
    public void suscripcionFondoNoExisteTest() {
        when(fondoRepo.findById("X")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            fondoService.suscribirAFondo("X", "EMAIL");
        });

        assertEquals("Fondo no encontrado", ex.getMessage());
    }

    @Test
    public void suscripcionSinSaldoTest() {
        Fondo fondo = new Fondo("1", "Fondo Grande", 150000, "FPV");
        Usuario usuario = new Usuario("1", "Usuario Test", 100000);

        when(fondoRepo.findById("1")).thenReturn(Optional.of(fondo));
        when(usuarioRepo.getUsuario()).thenReturn(usuario);

        String mensaje = fondoService.suscribirAFondo("1", "SMS");

        assertEquals("No tiene saldo disponible para vincularse al fondo Fondo Grande", mensaje);
        verify(transaccionRepo, never()).save(any());
    }
}
