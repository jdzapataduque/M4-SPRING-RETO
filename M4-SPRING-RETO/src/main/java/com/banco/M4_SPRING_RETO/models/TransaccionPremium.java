package com.banco.M4_SPRING_RETO.models;

import com.banco.M4_SPRING_RETO.models.helper.Cuenta;
import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaccionPremium")
public class TransaccionPremium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tipo;
    private BigDecimal monto;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    private CuentaPremium cuentaPremium;

    public TransaccionPremium(String tipo, BigDecimal monto, LocalDateTime fecha, CuentaPremium cuentaPremium) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
        this.cuentaPremium = cuentaPremium;
    }
    public TransaccionPremium(){}
}
