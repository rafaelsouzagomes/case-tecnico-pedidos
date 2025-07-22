package com.interview.pedidos.dto;

import com.interview.pedidos.entities.Usuario;

import lombok.Data;

@Data
public  class TicketMedioUsuarioDTO {
    private Usuario usuario;
    private Double ticketMedio;
   
    public TicketMedioUsuarioDTO(Usuario usuario, Double ticketMedio) {
        this.usuario = usuario;
        this.ticketMedio = ticketMedio;
    }
}
