package com.banco.M4_SPRING_RETO.controllers;

import com.banco.M4_SPRING_RETO.models.CuentaBasica;
import com.banco.M4_SPRING_RETO.models.CuentaPremium;
import com.banco.M4_SPRING_RETO.models.TransaccionBasica;
import com.banco.M4_SPRING_RETO.repositories.CuentaBasicaRepository;
import com.banco.M4_SPRING_RETO.services.CuentaBasicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cuenta/basica")
public class CuentaBasicaController {
    @Autowired
    private CuentaBasicaService cuentaBasicaService;
    @Autowired
    private CuentaBasicaRepository cuentaBasicaRepository;

    @GetMapping("/consulta")
    public ResponseEntity<CuentaBasica> Consuta(@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta){
        CuentaBasica cuentaBasica = this.cuentaBasicaService.Consulta(numeroCuenta);
        if(cuentaBasica == null)
            return new ResponseEntity("La cuenta no existe",HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok().body(cuentaBasica);
    }

    @GetMapping("/listar-ops")
    public ResponseEntity<List<TransaccionBasica>> ListarOps (@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta){
        CuentaBasica cuentaBasica = this.cuentaBasicaService.Consulta(numeroCuenta);
        if(cuentaBasica == null)
            return new ResponseEntity("La cuenta no existe",HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok().body(this.cuentaBasicaService.ListarOps(numeroCuenta));
    }

    @PostMapping("/nueva")
    public ResponseEntity<CuentaBasica> Crear(@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta){
        CuentaBasica cuentaBasica = this.cuentaBasicaService.Crear(numeroCuenta);
        if(cuentaBasica == null)
            return new ResponseEntity("La cuenta exite",HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok().body(cuentaBasica);
    }

    @PostMapping("/sucursal")
    public ResponseEntity<CuentaBasica> DepositoSucursal (@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta,
                                                           @RequestParam(name = "cantidad") BigDecimal cantidad){

        Optional<CuentaBasica> cuentaBasica = this.cuentaBasicaRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return new ResponseEntity("La cuenta no exite",HttpStatus.BAD_REQUEST);

        if(cantidad.compareTo( BigDecimal.ONE ) == -1)
            return new ResponseEntity("La cantidad a deposotar debe ser mayor a 1",HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok().body(this.cuentaBasicaService.DepositoSucursal(numeroCuenta, cantidad));

    }

    @PostMapping("/cajero")
    public ResponseEntity<CuentaBasica> DepositoCajero (@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta,
                                                      @RequestParam(name = "cantidad") BigDecimal cantidad){
        Optional<CuentaBasica> cuentaBasica = this.cuentaBasicaRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return new ResponseEntity("La cuenta no exite",HttpStatus.BAD_REQUEST);

        if(cantidad.compareTo( BigDecimal.ONE ) == -1)
            return new ResponseEntity("La cantidad a deposotar debe ser mayor a 1",HttpStatus.BAD_REQUEST);


        return ResponseEntity.ok().body(this.cuentaBasicaService.DepositoCajero(numeroCuenta, cantidad));
    }

    @PostMapping("/cuenta")
    public ResponseEntity<CuentaBasica> DepositoCuenta (@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta,
                                                      @RequestParam(name = "cantidad") BigDecimal cantidad){
        Optional<CuentaBasica> cuentaBasica = this.cuentaBasicaRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return new ResponseEntity("La cuenta no exite",HttpStatus.BAD_REQUEST);

        if(cantidad.compareTo( BigDecimal.ONE ) == -1)
            return new ResponseEntity("La cantidad a deposotar debe ser mayor a 1",HttpStatus.BAD_REQUEST);


        return ResponseEntity.ok().body(this.cuentaBasicaService.DepositoCuenta(numeroCuenta, cantidad));
    }

    @PostMapping("/compra")
    public ResponseEntity<CuentaBasica> Compra (@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta,
                                              @RequestParam(name = "cantidad") BigDecimal cantidad){
        Optional<CuentaBasica> cuentaBasica = this.cuentaBasicaRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return new ResponseEntity("La cuenta no exite",HttpStatus.BAD_REQUEST);

        if( cuentaBasica.get().getSaldo().compareTo( cantidad ) == -1)
            return new ResponseEntity("Saldo insuficiente",HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok().body(this.cuentaBasicaService.Compra(numeroCuenta, cantidad));
    }

    @PostMapping("/web")
    public ResponseEntity<CuentaBasica> CompraWeb (@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta,
                                                 @RequestParam(name = "cantidad") BigDecimal cantidad){
        Optional<CuentaBasica> cuentaBasica = this.cuentaBasicaRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return new ResponseEntity("La cuenta no exite",HttpStatus.BAD_REQUEST);

        if( cuentaBasica.get().getSaldo().compareTo( cantidad ) == -1)
            return new ResponseEntity("Saldo insuficiente",HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok().body(this.cuentaBasicaService.CompraWeb(numeroCuenta, cantidad));
    }

    @PostMapping("/retiro-cajero")
    public ResponseEntity<CuentaBasica> RetiroCajero (@RequestParam(name = "numeroCuenta") BigDecimal numeroCuenta,
                                                       @RequestParam(name = "cantidad") BigDecimal cantidad){
        Optional<CuentaBasica> cuentaBasica = this.cuentaBasicaRepository.findBynumeroCuenta(numeroCuenta);
        if(!cuentaBasica.isPresent())
            return new ResponseEntity("La cuenta no exite",HttpStatus.BAD_REQUEST);

        if( cuentaBasica.get().getSaldo().compareTo( cantidad ) == -1)
            return new ResponseEntity("Saldo insuficiente",HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok().body(this.cuentaBasicaService.RetiroCajero(numeroCuenta, cantidad));
    }
}
