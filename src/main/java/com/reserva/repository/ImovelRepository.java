package com.reserva.repository;

import com.reserva.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImovelRepository  extends JpaRepository<Imovel, Long> {
    Optional<Imovel> findFirstByNumero(String numero);
}
