package com.reserva.controller;

import com.reserva.dto.DadosAgendamentoReservaDto;
import com.reserva.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RequestMapping("reservas")
@RestController
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

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
    public ResponseEntity agendarReserva(@RequestBody @Valid DadosAgendamentoReservaDto dados) {
        try {
            reservaService.agendarReserva(dados);
            return ResponseEntity.ok(reservaService.listAll());
        } catch (Exception e) {
            return new ResponseEntity<>(Arrays.asList(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }
}
