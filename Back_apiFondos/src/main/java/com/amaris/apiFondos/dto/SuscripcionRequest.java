package com.amaris.apiFondos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SuscripcionRequest {

    @NotBlank(message = "El ID del fondo es obligatorio")
    private String idFondo;

    @NotBlank(message = "Debe seleccionar un medio de notificación")
    @Pattern(regexp = "EMAIL|SMS", message = "El medio debe ser EMAIL o SMS")
    private String medioNotificacion;

    public String getIdFondo() {
        return idFondo;
    }

    public void setIdFondo(String idFondo) {
        this.idFondo = idFondo;
    }

    public String getMedioNotificacion() {
        return medioNotificacion;
    }

    public void setMedioNotificacion(String medioNotificacion) {
        this.medioNotificacion = medioNotificacion;
    }
}
