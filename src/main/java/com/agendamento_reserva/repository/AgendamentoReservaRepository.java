package com.agendamento_reserva.repository;

import com.agendamento_reserva.model.AgendaReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoReservaRepository extends JpaRepository<AgendaReserva, Long> {
    List<AgendaReserva> findByImovelId(Long imovelId);

    List<AgendaReserva> findByAreaComum(Long areaId);

    @Query(value = "select count(*) from reserva where area_comum = :areaComum and "+
                    "data_hora_reserva = :dataAgendamento", nativeQuery = true)
    Long existsAgendamentoParaAreaComumAndDataHoraReserva(Long areaComum, LocalDateTime dataAgendamento);
}
