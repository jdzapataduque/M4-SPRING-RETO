package com.banco.M4_SPRING_RETO.repositories;

import com.banco.M4_SPRING_RETO.models.CuentaPremium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CuentaPremiumRepository  extends JpaRepository<CuentaPremium, Long> {
    Optional<CuentaPremium> findBynumeroCuenta(BigDecimal numeroCuenta);
}
