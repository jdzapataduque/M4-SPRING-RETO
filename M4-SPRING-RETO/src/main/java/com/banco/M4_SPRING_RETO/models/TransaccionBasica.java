package com.banco.M4_SPRING_RETO.models;

import com.banco.M4_SPRING_RETO.models.helper.Cuenta;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaccionBasica")
public class TransaccionBasica {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tipo;
    private BigDecimal monto;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    private CuentaBasica cuentaBasica;

    public TransaccionBasica(String tipo, BigDecimal monto, LocalDateTime fecha, CuentaBasica cuentaBasica) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
        this.cuentaBasica = cuentaBasica;
    }

    public TransaccionBasica(){}

}
