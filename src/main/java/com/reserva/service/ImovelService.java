package com.reserva.service;

import com.reserva.dto.ImovelDto;
import com.reserva.exception.ObjectNotFoundException;
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
        return imovelRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public Imovel getByNumero(String numero) {
        return imovelRepository.findFirstByNumero(numero).orElseThrow(() -> new ObjectNotFoundException(numero));
    }

    public Imovel salvar(ImovelDto imovelDto) {
//        if (getByNumero(imovelDto.getNumero()) != null) {
//            throw new RegistroUniqueException(imovelDto.getNumero());
//        }
        Imovel novoImovel = new Imovel();
        novoImovel.setId(null);
        novoImovel.setNumero(imovelDto.getNumero());
        return imovelRepository.save(novoImovel);
    }
}
