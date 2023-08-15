package com.agendamento_reserva.service;

import com.agendamento_reserva.dto.ImovelDto;
import com.agendamento_reserva.exception.ObjectNotFoundException;
import com.agendamento_reserva.exception.RegrasAgendamentoValidadorException;
import com.agendamento_reserva.model.Imovel;
import com.agendamento_reserva.repository.ImovelRepository;
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
        Imovel novoImovel = new Imovel();
        novoImovel.setId(null);
        novoImovel.setNumero(imovelDto.getNumero());
        return imovelRepository.save(novoImovel);
    }

    public Imovel validaStatusInadimplencia(Long imovelId) {
        Imovel imovel = getById(imovelId);

        if (imovel.getInadimplente()) {
          throw new RegrasAgendamentoValidadorException("Não será possível agendar essa reserva. Contate o Síndico");
        }
        return imovel;
    }
}
