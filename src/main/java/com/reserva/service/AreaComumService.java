package com.reserva.service;

import com.reserva.dto.AreaComumDto;
import com.reserva.exception.RegistroNotFoundException;
import com.reserva.exception.RegistroUniqueException;
import com.reserva.model.AreaComum;
import com.reserva.repository.AreaComumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaComumService {

    @Autowired
    private AreaComumRepository areaComumRepository;

    public List<AreaComum> listAll() {
        return areaComumRepository.findAll();
    }

    public AreaComum getById(Long id) {
        return areaComumRepository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public AreaComum getByCodAreaComum(String codAreaComum) {
        return areaComumRepository.getFirstByCodAreaComum(codAreaComum).orElseThrow(() ->
                new RegistroNotFoundException(codAreaComum));
    }

    public AreaComum salvar(AreaComumDto areaComumDto) {
        if (getByCodAreaComum(areaComumDto.getCodAreaComum()) != null) {
            throw new RegistroUniqueException(areaComumDto.getCodAreaComum());
        }

        AreaComum areaComum = new AreaComum();
        areaComum.setId(null);
        areaComum.setCodAreaComum(areaComumDto.getCodAreaComum());
        areaComum.setDescricaoAreaComum(areaComumDto.getDescricaoAreaComum());
        areaComum.setHorarioEmMinutosMinimoAntecedencia(areaComumDto.getHorarioEmMinutosMinimoAntecedencia());
        areaComum.setQuantidadeReservaPermitidaPorSemana(areaComum.getQuantidadeReservaPermitidaPorSemana());
        areaComum.setQuantidadeReservaPermitidaPorMes(areaComumDto.getQuantidadeReservaPermitidaPorMes());
        return areaComumRepository.save(areaComum);
    }
}
