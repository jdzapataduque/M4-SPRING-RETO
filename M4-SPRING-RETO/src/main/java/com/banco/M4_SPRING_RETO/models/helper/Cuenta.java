package com.banco.M4_SPRING_RETO.models.helper;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable=false, updatable=false)
    private Long id;
    private BigDecimal saldo;
    @Column(unique = true, nullable = false)
    private BigDecimal numeroCuenta;


    public Cuenta(BigDecimal numeroCuenta) {
        this.id = Long.valueOf(0);
        this.saldo = BigDecimal.valueOf(0);
        this.numeroCuenta = numeroCuenta;
    }

    public Cuenta(){}

    public abstract BigDecimal DepositoSucursal(BigDecimal cantidad);
    public abstract BigDecimal DepositoCajero(BigDecimal cantidad);
    public abstract BigDecimal DepositoCuenta(BigDecimal cantidad);
    public abstract BigDecimal Compra(BigDecimal cantidad);
    public abstract BigDecimal CompraWeb(BigDecimal cantidad);
    public abstract BigDecimal RetiroCajero(BigDecimal cantidad);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(BigDecimal numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

}
