package com.banco.M4_SPRING_RETO.controllers;

import com.banco.M4_SPRING_RETO.models.CuentaBasica;
import com.banco.M4_SPRING_RETO.models.CuentaPremium;
import com.banco.M4_SPRING_RETO.models.TransaccionBasica;
import com.banco.M4_SPRING_RETO.models.TransaccionPremium;
import com.banco.M4_SPRING_RETO.repositories.CuentaPremiumRepository;
import com.banco.M4_SPRING_RETO.services.CuentaPremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cuenta/premium")
public class CuentaPremiumController {
    @Autowired
    private CuentaPremiumService cuentaPremiumService;
    @Autowired
    private CuentaPremiumRepository cuentaPremiumRepository;

    @GetMapping("/listar-ops")
    public ResponseEntity<List<TransaccionPremium>> ListarOps (@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta){
        CuentaPremium cuentaPremium = this.cuentaPremiumService.Consulta(numeroCuenta);
        if(cuentaPremium == null)
            return new ResponseEntity("La cuenta no existe",HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok().body(this.cuentaPremiumService.ListarOps(numeroCuenta));
    }

    @GetMapping("/consulta")
    public ResponseEntity<CuentaPremium> Consuta(@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta){
        CuentaPremium cuentaPremium = this.cuentaPremiumService.Consulta(numeroCuenta);
        if(cuentaPremium == null)
            return new ResponseEntity("La cuenta no existe",HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok().body(cuentaPremium);
    }

    @PostMapping("/nueva")
    public ResponseEntity<CuentaPremium> Crear(@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta){
        CuentaPremium cuentaPremium = this.cuentaPremiumService.Crear(numeroCuenta);
        if(cuentaPremium == null)
            return new ResponseEntity("La cuenta exite",HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok().body(cuentaPremium);
    }

    @PostMapping("/sucursal")
    public ResponseEntity<CuentaPremium> DepositoSucursal (@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta,
                                                        @RequestParam(name = "cantidad") BigDecimal cantidad){
        Optional<CuentaPremium> cuentaPremium = this.cuentaPremiumRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaPremium.isPresent())
            return new ResponseEntity("La cuenta no exite",HttpStatus.BAD_REQUEST);

        if(cantidad.compareTo( BigDecimal.ONE ) == -1)
            return new ResponseEntity("La cantidad a deposotar debe ser mayor a 1",HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok().body(this.cuentaPremiumService.DepositoSucursal(numeroCuenta, cantidad));

    }

    @PostMapping("/cajero")
    public ResponseEntity<CuentaPremium> DepositoCajero (@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta,
                                                        @RequestParam(name = "cantidad") BigDecimal cantidad){
        Optional<CuentaPremium> cuentaPremium = this.cuentaPremiumRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaPremium.isPresent())
            return new ResponseEntity("La cuenta no exite",HttpStatus.BAD_REQUEST);

        if(cantidad.compareTo( BigDecimal.ONE ) == -1)
            return new ResponseEntity("La cantidad a deposotar debe ser mayor a 1",HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok().body(this.cuentaPremiumService.DepositoCajero(numeroCuenta, cantidad));
    }

    @PostMapping("/cuenta")
    public ResponseEntity<CuentaPremium> DepositoCuenta (@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta,
                                                      @RequestParam(name = "cantidad") BigDecimal cantidad){
        Optional<CuentaPremium> cuentaPremium = this.cuentaPremiumRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaPremium.isPresent())
            return new ResponseEntity("La cuenta no exite",HttpStatus.BAD_REQUEST);

        if(cantidad.compareTo( BigDecimal.ONE ) == -1)
            return new ResponseEntity("La cantidad a deposotar debe ser mayor a 1",HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok().body(this.cuentaPremiumService.DepositoCuenta(numeroCuenta, cantidad));
    }

    @PostMapping("/compra")
    public ResponseEntity<CuentaPremium> Compra (@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta,
                                                      @RequestParam(name = "cantidad") BigDecimal cantidad){
        Optional<CuentaPremium> cuentaPremium = this.cuentaPremiumRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaPremium.isPresent())
            return new ResponseEntity("La cuenta no exite",HttpStatus.BAD_REQUEST);

        if( cuentaPremium.get().getSaldo().compareTo( cantidad ) == -1)
            return new ResponseEntity("Saldo insuficiente",HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok().body(this.cuentaPremiumService.Compra(numeroCuenta, cantidad));
    }

    @PostMapping("/web")
    public ResponseEntity<CuentaPremium> CompraWeb (@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta,
                                                      @RequestParam(name = "cantidad") BigDecimal cantidad){
        Optional<CuentaPremium> cuentaPremium = this.cuentaPremiumRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaPremium.isPresent())
            return new ResponseEntity("La cuenta no exite",HttpStatus.BAD_REQUEST);

        if( cuentaPremium.get().getSaldo().compareTo( cantidad ) == -1)
            return new ResponseEntity("Saldo insuficiente",HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok().body(this.cuentaPremiumService.CompraWeb(numeroCuenta, cantidad));
    }

    @PostMapping("/retiro-cajero")
    public ResponseEntity<CuentaPremium> RetiroCajero (@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta,
                                                    @RequestParam(name = "cantidad") BigDecimal cantidad){
        Optional<CuentaPremium> cuentaPremium = this.cuentaPremiumRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaPremium.isPresent())
            return new ResponseEntity("La cuenta no exite",HttpStatus.BAD_REQUEST);

        if( cuentaPremium.get().getSaldo().compareTo( cantidad ) == -1)
            return new ResponseEntity("Saldo insuficiente",HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok().body(this.cuentaPremiumService.RetiroCajero(numeroCuenta, cantidad));
    }
}
