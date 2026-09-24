package com.proyectocuatro.helpdeskapi.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        String mensaje,
        String error,
        int status,
        LocalDateTime timestamp
) {
}
