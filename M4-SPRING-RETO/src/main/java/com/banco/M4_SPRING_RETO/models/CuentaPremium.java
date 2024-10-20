package com.banco.M4_SPRING_RETO.models;

import com.banco.M4_SPRING_RETO.models.helper.Cuenta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cuenta_premium")
public class CuentaPremium extends Cuenta {

    @OneToMany(mappedBy = "cuentaPremium", cascade = CascadeType.ALL)
    private List<TransaccionPremium> transacciones;

    public CuentaPremium(BigDecimal numeroCuenta){
        super(numeroCuenta);
    }

    public CuentaPremium(){}

    @Override
    public BigDecimal DepositoSucursal(BigDecimal cantidad) {
        BigDecimal nuevoSaldo = this.getSaldo().add(cantidad);
        this.setSaldo(nuevoSaldo);
        return nuevoSaldo;
    }

    @Override
    public BigDecimal DepositoCajero(BigDecimal cantidad) {
        BigDecimal nuevoSaldo = this.getSaldo().add(cantidad);
        this.setSaldo(nuevoSaldo);
        return nuevoSaldo;
    }

    @Override
    public BigDecimal DepositoCuenta(BigDecimal cantidad) {
        BigDecimal costo = BigDecimal.valueOf(1.5);
        BigDecimal nuevoSaldo = this.getSaldo().add(cantidad.subtract(costo));
        this.setSaldo(nuevoSaldo);
        return nuevoSaldo;
    }

    @Override
    public BigDecimal Compra(BigDecimal cantidad) {
        BigDecimal nuevoSaldo = this.getSaldo().subtract(cantidad);
        this.setSaldo(nuevoSaldo);
        return nuevoSaldo;
    }

    @Override
    public BigDecimal CompraWeb(BigDecimal cantidad) {
        BigDecimal costo = BigDecimal.valueOf(5);
        BigDecimal nuevoSaldo = this.getSaldo().subtract(cantidad.add(costo));
        this.setSaldo(nuevoSaldo);
        return nuevoSaldo;
    }

    @Override
    public BigDecimal RetiroCajero(BigDecimal cantidad) {
        BigDecimal nuevoSaldo = this.getSaldo().subtract(cantidad);
        if(nuevoSaldo.compareTo(BigDecimal.ZERO) == -1)
            return null;
        this.setSaldo(nuevoSaldo);
        return nuevoSaldo;
    }

    public List<TransaccionPremium> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<TransaccionPremium> transacciones) {
        this.transacciones = transacciones;
    }
}
