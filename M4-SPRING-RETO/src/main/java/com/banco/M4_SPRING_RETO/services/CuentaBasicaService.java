package com.banco.M4_SPRING_RETO.services;

import com.banco.M4_SPRING_RETO.models.CuentaBasica;
import com.banco.M4_SPRING_RETO.models.CuentaPremium;
import com.banco.M4_SPRING_RETO.models.TransaccionBasica;
import com.banco.M4_SPRING_RETO.repositories.CuentaBasicaRepository;
import com.banco.M4_SPRING_RETO.repositories.TransaccionBasicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaBasicaService {

    @Autowired
    private CuentaBasicaRepository cuentaBasicaRepository;
    @Autowired
    private TransaccionBasicaRepository transaccionBasicaRepository;

    public List<TransaccionBasica> ListarOps (BigDecimal numeroCuenta){
        Optional<CuentaBasica> cuentaBasica = this.cuentaBasicaRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return null;

        List<TransaccionBasica> transaccionBasicas = transaccionBasicaRepository.findTop5ByOrderByFechaDesc().stream().toList();
        return transaccionBasicas;
    }

    public CuentaBasica Consulta (BigDecimal numeroCuenta){
        Optional<CuentaBasica> cuentaBasica = this.cuentaBasicaRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return null;

        return cuentaBasica.get();
    }

    public CuentaBasica Crear (BigDecimal numeroCuenta){
        CuentaBasica cuentaBasica = new CuentaBasica(numeroCuenta);
        cuentaBasica.setTransacciones(List.of());
        CuentaBasica nuevaCuenta = this.cuentaBasicaRepository.save(cuentaBasica);
        if(nuevaCuenta == null)
            return null;
        return nuevaCuenta;
    }

    public CuentaBasica DepositoSucursal(BigDecimal numeroCuenta, BigDecimal cantidad) {
        Optional<CuentaBasica> cuentaBasica = this.cuentaBasicaRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return null;

        cuentaBasica.get().DepositoSucursal(cantidad);
        TransaccionBasica transaccionBasica = new TransaccionBasica("dep-sucursal",cantidad, LocalDateTime.now(), cuentaBasica.get());
        List<TransaccionBasica> transaccionesBasicas = cuentaBasica.get().getTransacciones();
        transaccionesBasicas.add(transaccionBasica);
        cuentaBasica.get().setTransacciones(transaccionesBasicas);
        this.cuentaBasicaRepository.save(cuentaBasica.get());
        return cuentaBasica.get();
    }


    public CuentaBasica DepositoCajero(BigDecimal numeroCuenta, BigDecimal cantidad) {
        Optional<CuentaBasica> cuentaBasica = this.cuentaBasicaRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return null;

        cuentaBasica.get().DepositoSucursal(cantidad);
        TransaccionBasica transaccionBasica = new TransaccionBasica("dep-cajero",cantidad, LocalDateTime.now(), cuentaBasica.get());
        List<TransaccionBasica> transaccionesBasicas = cuentaBasica.get().getTransacciones();
        transaccionesBasicas.add(transaccionBasica);
        cuentaBasica.get().setTransacciones(transaccionesBasicas);
        this.cuentaBasicaRepository.save(cuentaBasica.get());
        return cuentaBasica.get();
    }


    public CuentaBasica DepositoCuenta(BigDecimal numeroCuenta, BigDecimal cantidad) {
        Optional<CuentaBasica> cuentaBasica = this.cuentaBasicaRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return null;
        cuentaBasica.get().DepositoCuenta(cantidad);
        TransaccionBasica transaccionBasica = new TransaccionBasica("dep-cuenta",cantidad, LocalDateTime.now(), cuentaBasica.get());
        List<TransaccionBasica> transaccionesBasicas = cuentaBasica.get().getTransacciones();
        transaccionesBasicas.add(transaccionBasica);
        cuentaBasica.get().setTransacciones(transaccionesBasicas);
        this.cuentaBasicaRepository.save(cuentaBasica.get());
        return cuentaBasica.get();
    }


    public CuentaBasica Compra(BigDecimal numeroCuenta, BigDecimal cantidad) {
        Optional<CuentaBasica> cuentaBasica = this.cuentaBasicaRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return null;

        cuentaBasica.get().Compra(cantidad);

        TransaccionBasica transaccionBasica = new TransaccionBasica("comp",cantidad, LocalDateTime.now(), cuentaBasica.get());
        List<TransaccionBasica> transaccionesBasicas = cuentaBasica.get().getTransacciones();
        transaccionesBasicas.add(transaccionBasica);
        cuentaBasica.get().setTransacciones(transaccionesBasicas);
        this.cuentaBasicaRepository.save(cuentaBasica.get());
        return cuentaBasica.get();
    }


    public CuentaBasica CompraWeb(BigDecimal numeroCuenta, BigDecimal cantidad) {
        Optional<CuentaBasica> cuentaBasica = this.cuentaBasicaRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return null;

        cuentaBasica.get().CompraWeb(cantidad);

        TransaccionBasica transaccionBasica = new TransaccionBasica("comp-web",cantidad, LocalDateTime.now(), cuentaBasica.get());
        List<TransaccionBasica> transaccionesBasicas = cuentaBasica.get().getTransacciones();
        transaccionesBasicas.add(transaccionBasica);
        cuentaBasica.get().setTransacciones(transaccionesBasicas);
        this.cuentaBasicaRepository.save(cuentaBasica.get());
        return cuentaBasica.get();
    }

    public CuentaBasica RetiroCajero(BigDecimal numeroCuenta, BigDecimal cantidad) {
        Optional<CuentaBasica> cuentaBasica = this.cuentaBasicaRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return null;

        cuentaBasica.get().RetiroCajero(cantidad);

        TransaccionBasica transaccionBasica = new TransaccionBasica("ret",cantidad, LocalDateTime.now(), cuentaBasica.get());
        List<TransaccionBasica> transaccionesBasicas = cuentaBasica.get().getTransacciones();
        transaccionesBasicas.add(transaccionBasica);
        cuentaBasica.get().setTransacciones(transaccionesBasicas);
        this.cuentaBasicaRepository.save(cuentaBasica.get());

        return cuentaBasica.get();
    }
}
