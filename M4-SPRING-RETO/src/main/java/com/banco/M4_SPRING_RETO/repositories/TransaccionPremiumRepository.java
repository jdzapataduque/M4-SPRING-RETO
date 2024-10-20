package com.banco.M4_SPRING_RETO.repositories;

import com.banco.M4_SPRING_RETO.models.TransaccionBasica;
import com.banco.M4_SPRING_RETO.models.TransaccionPremium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionPremiumRepository extends JpaRepository<TransaccionPremium, Long> {
    List<TransaccionPremium> findTop5ByOrderByFechaDesc();
}
