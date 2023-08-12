package com.reserva.service;

import com.reserva.dto.AreaComumDto;
import com.reserva.dto.ImovelDto;
import com.reserva.exception.RegistroNotFoundException;
import com.reserva.model.AreaComum;
import com.reserva.model.Imovel;
import com.reserva.repository.AreaComumRepository;
import com.reserva.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AreaComumService {

    @Autowired
    private AreaComumRepository areaComumRepository;

    public List<AreaComum> listAll() {
        return areaComumRepository.findAll();
    }

    public AreaComum salvar(AreaComumDto areaComumDto) {
        AreaComum areaComum = new AreaComum();
        areaComum.setId(null);
        areaComum.setCodAreaComum(areaComumDto.getCodAreaComum());
        areaComum.setDescricaoAreaComum(areaComumDto.getDescricaoAreaComum());
        areaComum.setHorarioEmMinutosMinimoAntecedencia(areaComumDto.getHorarioEmMinutosMinimoAntecedencia());
        areaComum.setQuantidadeReservaPermitidaPorSemana(areaComum.getQuantidadeReservaPermitidaPorSemana());
        areaComum.setQuantidadeReservaPermitidaPorMes(areaComumDto.getQuantidadeReservaPermitidaPorMes());
        return areaComumRepository.save(areaComum);
    }

    public AreaComum getById(Long id) {
        return areaComumRepository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));
    }
}
