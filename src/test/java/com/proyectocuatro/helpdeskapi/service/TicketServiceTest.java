package com.proyectocuatro.helpdeskapi.service;

import com.proyectocuatro.helpdeskapi.dto.TicketDTO;
import com.proyectocuatro.helpdeskapi.model.EstadoTicket;
import com.proyectocuatro.helpdeskapi.model.Ticket;
import com.proyectocuatro.helpdeskapi.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void crearTicket_conDatosValidos_RetornaTicketAbierto(){
        // 1. Arrange (Preparar)
        TicketDTO dto = new TicketDTO("Fallo de red", "Sin internet");

        // Controlamos el comportamiento del mock interceptando la llamada
        when(ticketRepository.guardar(any(Ticket.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // 2. Act (Actuar)
        Ticket resultado = ticketService.crearTicket(dto);

        // 3. Assert (Afirmar)
        assertNotNull(resultado.getId());
        assertEquals("Fallo de red", resultado.getTitulo());
        assertEquals(EstadoTicket.ABIERTO, resultado.getEstado());

        // Verificamos que el método guardar() del mock se haya llamado exactamente 1 vez (como un spy)
        verify(ticketRepository, times(1)).guardar(any(Ticket.class));
    }

    @Test
    void crearTicket_conTituloVacio_lanzaExcepcion() {
        // 1. Arrange
        TicketDTO dtoInvalido = new TicketDTO("", "Descripción válida");

        // 2 y 3. Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ticketService.crearTicket(dtoInvalido);
        });

        assertEquals("El titulo y la descripcion son obligatorios", exception.getMessage());

        // Verificamos que nunca se haya intentado guardar en la base de datos
        verify(ticketRepository, never()).guardar(any());
    }
}
