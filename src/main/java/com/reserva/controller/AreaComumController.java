package com.reserva.controller;

import com.reserva.dto.AreaComumDto;
import com.reserva.service.AreaComumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("areas")
public class AreaComumController {

    @Autowired
    private AreaComumService areaComumService;

    @GetMapping
    public ResponseEntity getAreas() {
        return ResponseEntity.ok(areaComumService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getAreaById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(areaComumService.getById(id));
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid AreaComumDto areaComumDto) {
        return ResponseEntity.ok(areaComumService.salvar(areaComumDto));
    }
}
