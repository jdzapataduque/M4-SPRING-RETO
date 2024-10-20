package com.banco.M4_SPRING_RETO.models;

import com.banco.M4_SPRING_RETO.models.helper.Cuenta;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cuenta_basica")
public class CuentaBasica extends Cuenta {

    @OneToMany(mappedBy = "cuentaBasica", cascade = CascadeType.ALL)
    private List<TransaccionBasica> transacciones;

    public CuentaBasica(BigDecimal numeroCuenta){
        super(numeroCuenta);
    }

    public CuentaBasica(){}

    @Override
    public BigDecimal DepositoSucursal(BigDecimal cantidad) {
        BigDecimal nuevoSaldo = this.getSaldo().add(cantidad);
        this.setSaldo(nuevoSaldo);
        return nuevoSaldo;
    }

    @Override
    public BigDecimal DepositoCajero(BigDecimal cantidad) {
        BigDecimal costo = BigDecimal.valueOf(2);
        BigDecimal nuevoSaldo = this.getSaldo().add(cantidad.subtract(costo));
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
        BigDecimal costo = BigDecimal.valueOf(1);
        BigDecimal nuevoSaldo = this.getSaldo().subtract(cantidad.add(costo));
        this.setSaldo(nuevoSaldo);
        return nuevoSaldo;
    }

    public List<TransaccionBasica> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<TransaccionBasica> transacciones) {
        this.transacciones = transacciones;
    }
}
