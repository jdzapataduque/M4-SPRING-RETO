package com.banco.M4_SPRING_RETO.services;

import com.banco.M4_SPRING_RETO.models.CuentaBasica;
import com.banco.M4_SPRING_RETO.models.CuentaPremium;
import com.banco.M4_SPRING_RETO.models.TransaccionBasica;
import com.banco.M4_SPRING_RETO.models.TransaccionPremium;
import com.banco.M4_SPRING_RETO.repositories.CuentaPremiumRepository;
import com.banco.M4_SPRING_RETO.repositories.TransaccionPremiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaPremiumService {
    @Autowired
    private CuentaPremiumRepository cuentaPremiumRepository;
    @Autowired
    private TransaccionPremiumRepository transaccionPremiumRepository;

    public List<TransaccionPremium> ListarOps (BigDecimal numeroCuenta){
        Optional<CuentaPremium> cuentaBasica = this.cuentaPremiumRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return null;

        List<TransaccionPremium> transaccionPremiums = transaccionPremiumRepository.findTop5ByOrderByFechaDesc().stream().toList();
        return transaccionPremiums;
    }


    public CuentaPremium Consulta (BigDecimal numeroCuenta){
        Optional<CuentaPremium> cuentaPremium = this.cuentaPremiumRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaPremium.isPresent())
            return null;

        return cuentaPremium.get();
    }

    public CuentaPremium Crear (BigDecimal numeroCuenta){
        CuentaPremium cuentaPremium = new CuentaPremium(numeroCuenta);
        cuentaPremium.setTransacciones(List.of());
        CuentaPremium nuevaCuenta = this.cuentaPremiumRepository.save(cuentaPremium);
        if(nuevaCuenta == null)
            return null;
        return nuevaCuenta;
    }

    public CuentaPremium DepositoSucursal(BigDecimal numeroCuenta, BigDecimal cantidad) {
        Optional<CuentaPremium> cuentaPremium = this.cuentaPremiumRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaPremium.isPresent())
            return null;

        cuentaPremium.get().DepositoSucursal(cantidad);
        TransaccionPremium transaccionPremium = new TransaccionPremium("dep-sucursal",cantidad, LocalDateTime.now(), cuentaPremium.get());
        List<TransaccionPremium> transaccionesPremiums = cuentaPremium.get().getTransacciones();
        transaccionesPremiums.add(transaccionPremium);
        cuentaPremium.get().setTransacciones(transaccionesPremiums);
        this.cuentaPremiumRepository.save(cuentaPremium.get());

        return cuentaPremium.get();
    }


    public CuentaPremium DepositoCajero(BigDecimal numeroCuenta, BigDecimal cantidad) {
        Optional<CuentaPremium> cuentaPremium = this.cuentaPremiumRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaPremium.isPresent())
            return null;

        cuentaPremium.get().DepositoSucursal(cantidad);

        TransaccionPremium transaccionPremium = new TransaccionPremium("dep-cajero",cantidad, LocalDateTime.now(), cuentaPremium.get());
        List<TransaccionPremium> transaccionesPremiums = cuentaPremium.get().getTransacciones();
        transaccionesPremiums.add(transaccionPremium);
        cuentaPremium.get().setTransacciones(transaccionesPremiums);

        this.cuentaPremiumRepository.save(cuentaPremium.get());
        return cuentaPremium.get();
    }


    public CuentaPremium DepositoCuenta(BigDecimal numeroCuenta, BigDecimal cantidad) {
        Optional<CuentaPremium> cuentaPremium = this.cuentaPremiumRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaPremium.isPresent())
            return null;

        cuentaPremium.get().DepositoCuenta(cantidad);

        TransaccionPremium transaccionPremium = new TransaccionPremium("dep-cuenta",cantidad, LocalDateTime.now(), cuentaPremium.get());
        List<TransaccionPremium> transaccionesPremiums = cuentaPremium.get().getTransacciones();
        transaccionesPremiums.add(transaccionPremium);
        cuentaPremium.get().setTransacciones(transaccionesPremiums);
        this.cuentaPremiumRepository.save(cuentaPremium.get());
        return cuentaPremium.get();
    }


    public CuentaPremium Compra(BigDecimal numeroCuenta, BigDecimal cantidad) {
        Optional<CuentaPremium> cuentaPremium = this.cuentaPremiumRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaPremium.isPresent())
            return null;

        cuentaPremium.get().Compra(cantidad);

        TransaccionPremium transaccionPremium = new TransaccionPremium("comp",cantidad, LocalDateTime.now(), cuentaPremium.get());
        List<TransaccionPremium> transaccionesPremiums = cuentaPremium.get().getTransacciones();
        transaccionesPremiums.add(transaccionPremium);
        cuentaPremium.get().setTransacciones(transaccionesPremiums);
        this.cuentaPremiumRepository.save(cuentaPremium.get());
        return cuentaPremium.get();
    }


    public CuentaPremium CompraWeb(BigDecimal numeroCuenta, BigDecimal cantidad) {
        Optional<CuentaPremium> cuentaPremium = this.cuentaPremiumRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaPremium.isPresent())
            return null;

        cuentaPremium.get().CompraWeb(cantidad);

        TransaccionPremium transaccionPremium = new TransaccionPremium("comp-web",cantidad, LocalDateTime.now(), cuentaPremium.get());
        List<TransaccionPremium> transaccionesPremiums = cuentaPremium.get().getTransacciones();
        transaccionesPremiums.add(transaccionPremium);
        cuentaPremium.get().setTransacciones(transaccionesPremiums);
        this.cuentaPremiumRepository.save(cuentaPremium.get());
        return cuentaPremium.get();
    }

    public CuentaPremium RetiroCajero(BigDecimal numeroCuenta, BigDecimal cantidad) {
        Optional<CuentaPremium> cuentaPremium = this.cuentaPremiumRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaPremium.isPresent())
            return null;

        cuentaPremium.get().RetiroCajero(cantidad);

        TransaccionPremium transaccionPremium = new TransaccionPremium("ret",cantidad, LocalDateTime.now(), cuentaPremium.get());
        List<TransaccionPremium> transaccionesPremiums = cuentaPremium.get().getTransacciones();
        transaccionesPremiums.add(transaccionPremium);
        cuentaPremium.get().setTransacciones(transaccionesPremiums);
        this.cuentaPremiumRepository.save(cuentaPremium.get());
        return cuentaPremium.get();
    }

}
