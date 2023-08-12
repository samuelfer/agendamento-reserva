package com.reserva.service;

import com.reserva.dto.ImovelDto;
import com.reserva.exception.ImovelNotFoundException;
import com.reserva.exception.RegistroNotFoundException;
import com.reserva.model.Imovel;
import com.reserva.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    public List<Imovel> getAll() {
        return imovelRepository.findAll();
    }

    public Imovel getById(Long id) {
        return imovelRepository.findById(id).orElseThrow(() -> new ImovelNotFoundException());
    }

    public Imovel salvar(ImovelDto imovelDto) {
        Imovel novoImovel = new Imovel();
        novoImovel.setId(null);
        novoImovel.setNumero(imovelDto.getNumero());
        return imovelRepository.save(novoImovel);
    }
}
