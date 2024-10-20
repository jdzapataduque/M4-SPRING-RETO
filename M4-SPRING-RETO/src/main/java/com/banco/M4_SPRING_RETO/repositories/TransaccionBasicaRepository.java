package com.banco.M4_SPRING_RETO.repositories;

import com.banco.M4_SPRING_RETO.models.TransaccionBasica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionBasicaRepository extends JpaRepository<TransaccionBasica, Long> {
    List<TransaccionBasica> findTop5ByOrderByFechaDesc();
}
