package com.reserva.controller;

import com.reserva.dto.ImovelDto;
import com.reserva.service.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("imoveis")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;

    @GetMapping
    public ResponseEntity getImoveis() {
        return ResponseEntity.ok(imovelService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getImovelById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(imovelService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ImovelDto> salvar(@Valid @RequestBody ImovelDto imovelDto) throws Exception {
        return new ResponseEntity(imovelService.salvar(imovelDto), HttpStatus.CREATED);
    }
}
