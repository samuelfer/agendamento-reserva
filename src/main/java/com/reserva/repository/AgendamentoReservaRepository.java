package com.reserva.repository;

import com.reserva.model.AgendaReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface AgendamentoReservaRepository extends JpaRepository<AgendaReserva, Long> {
    List<AgendaReserva> findByImovelId(Long imovelId);

    List<AgendaReserva> findByAreaComum(Long areaId);

    @Query(value = "select count(*) from reserva where area_comum = :areaComum and "+
                    "date(data_hora_reserva) = :dataAgendamento", nativeQuery = true)
    Long existsByAreaComumAndAndDataHoraReserva(Long areaComum, Date dataAgendamento);
}
