package com.proyectocuatro.helpdeskapi.service;

import com.proyectocuatro.helpdeskapi.dto.TicketDTO;
import com.proyectocuatro.helpdeskapi.model.EstadoTicket;
import com.proyectocuatro.helpdeskapi.model.Ticket;
import com.proyectocuatro.helpdeskapi.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket crearTicket(TicketDTO dto){

        if(dto.titulo().isBlank()|| dto.descripcion().isBlank()){
            throw new RuntimeException("El titulo y la descripcion son obligatorios");
        }

        Ticket tk = new Ticket(
                java.util.UUID.randomUUID().toString(),
                dto.titulo(),
                dto.descripcion(),
                EstadoTicket.ABIERTO,
                LocalDateTime.now()
        );

        return ticketRepository.guardar(tk);

    }

    public List<Ticket> obtenerTodos(){
        return ticketRepository.obtenerTodos();
    }

    public Ticket obtenerPorID(String id){
        if(id == null || id.isBlank()){
            throw new RuntimeException("El ID no es valido");
        }

        return ticketRepository.obtenerPorID(id)
                .orElseThrow(() -> new RuntimeException("El ticket con id: " + id + " no existe"));
    }

    public Ticket resolverTicket(String id){

        Ticket tk = this.obtenerPorID(id);

        if(tk.getEstado() == EstadoTicket.CERRADO){
            throw  new RuntimeException("El ticket ya se encuentra cerrado");
        }

        tk.setEstado(EstadoTicket.CERRADO);

        return tk;

    }
}
