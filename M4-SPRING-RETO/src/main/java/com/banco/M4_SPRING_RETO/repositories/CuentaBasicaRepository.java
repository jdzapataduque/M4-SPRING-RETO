package com.banco.M4_SPRING_RETO.repositories;

import com.banco.M4_SPRING_RETO.models.CuentaBasica;
import com.banco.M4_SPRING_RETO.models.TransaccionBasica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaBasicaRepository  extends JpaRepository<CuentaBasica, Long> {
    Optional<CuentaBasica> findBynumeroCuenta(BigDecimal numeroCuenta);
}
