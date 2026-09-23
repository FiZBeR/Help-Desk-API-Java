package com.proyectocuatro.helpdeskapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private String id;
    private String titulo;
    private String descripcion;
    private EstadoTicket estado;
    private LocalDateTime fechaCreacion;

}
