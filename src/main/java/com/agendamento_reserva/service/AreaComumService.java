package com.agendamento_reserva.service;

import com.agendamento_reserva.dto.AreaComumDto;
import com.agendamento_reserva.exception.ObjectNotFoundException;
import com.agendamento_reserva.model.AreaComum;
import com.agendamento_reserva.repository.AreaComumRepository;
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
        return areaComumRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public AreaComum getByCodAreaComum(String codAreaComum) {
        return areaComumRepository.getFirstByCodAreaComum(codAreaComum).orElseThrow(() ->
                new ObjectNotFoundException(codAreaComum));
    }

    public AreaComum salvar(AreaComumDto areaComumDto) {
        AreaComum areaComum = areaComumDto.salvar(areaComumDto);
        return areaComumRepository.save(areaComum);
    }
}
