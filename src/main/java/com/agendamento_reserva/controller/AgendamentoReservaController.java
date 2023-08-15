package com.agendamento_reserva.controller;

import com.agendamento_reserva.dto.AgendamentoReservaDto;
import com.agendamento_reserva.service.AgendamentoReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RequestMapping("agendamento-reservas")
@RestController
public class AgendamentoReservaController {

    @Autowired
    private AgendamentoReservaService reservaService;

    @GetMapping
    public ResponseEntity getReservas() {
        return ResponseEntity.ok(reservaService.listAll());
    }

    @GetMapping("/imoveis/{imovelId}")
    public ResponseEntity getReservasPorImovel(@PathVariable("numero") Long imovelId) {
        return ResponseEntity.ok(reservaService.listAllReservasPorImovel(imovelId));
    }

    @GetMapping("/areas/{areaId}")
    public ResponseEntity getReservasPorArea(@PathVariable("areaId") Long areaId) {
        return ResponseEntity.ok(reservaService.listAllReservasPorArea(areaId));
    }

    @PostMapping
    public ResponseEntity agendarReserva(@Valid @RequestBody AgendamentoReservaDto dados) {
        try {
            return ResponseEntity.ok(reservaService.agendarReserva(dados));
        } catch (Exception e) {
            return new ResponseEntity<>(Arrays.asList(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
