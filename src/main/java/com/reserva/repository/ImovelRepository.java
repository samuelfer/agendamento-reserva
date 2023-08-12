package com.reserva.repository;

import com.reserva.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository  extends JpaRepository<Imovel, Long> {
}
