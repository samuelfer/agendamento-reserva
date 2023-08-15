package com.agendamento_reserva.repository;

import com.agendamento_reserva.model.AreaComum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaComumRepository extends JpaRepository<AreaComum, Long> {
    Optional<AreaComum> getFirstByCodAreaComum(String codAreaComum);
}
