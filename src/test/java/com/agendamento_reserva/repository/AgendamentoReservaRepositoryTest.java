package com.agendamento_reserva.repository;

import com.agendamento_reserva.model.AgendaReserva;
import com.agendamento_reserva.model.AreaComum;
import com.agendamento_reserva.model.Imovel;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.awt.geom.Area;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AgendamentoReservaRepositoryTest {

    @Autowired
    private AgendamentoReservaRepository repository;
    @Autowired
    private TestEntityManager em;

    LocalDateTime proximaSegundaAs13 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
    .atTime(13, 0);

    @Test
    @DisplayName("Não Deveria conseguir agendar caso exista agendamento para a area solicitada na dataEhora informada")
    void verificarSeAreaComumJaEstaAgendadaNaDataHoraSolicitada() {
        AreaComum areaComum = cadastrarAreaComum("Area 1", "Área de teste 1");
        Imovel imovel = cadastrarImovel("1");

        agendarReserva(imovel, areaComum, proximaSegundaAs13);

        Long countAgendamento = repository.existsAgendamentoParaAreaComumAndDataHoraReserva(areaComum.getId(), proximaSegundaAs13);
        assertThat(countAgendamento).isEqualTo(1);
    }

    private AreaComum cadastrarAreaComum(String codArea, String descricaoArea) {
        AreaComum areaComum = new AreaComum();
        areaComum.setCodAreaComum(codArea);
        areaComum.setDescricaoAreaComum(descricaoArea);
        areaComum.setHorarioEmMinutosMinimoAntecedencia(30L);
        areaComum.setQuantidadeReservaPermitidaPorSemana(2);
        areaComum.setQuantidadeReservaPermitidaPorMes(5);
        em.persist(areaComum);
        return areaComum;
    }

    private Imovel cadastrarImovel(String numero) {
        Imovel imovel = new Imovel();
        imovel.setNumero(numero);
        em.persist(imovel);
        return imovel;
    }

    private void agendarReserva(Imovel imovel, AreaComum areaComum, LocalDateTime dataHoraAgendamento) {
        AgendaReserva agendamento = new AgendaReserva();
        agendamento.setImovel(imovel);
        agendamento.setAreaComum(areaComum.getId());
        agendamento.setDataHoraReserva(dataHoraAgendamento);
        em.persist(agendamento);
    }
}