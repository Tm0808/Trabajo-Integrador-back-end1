package com.example.TrabajointegradorbackendI.Controller;

import com.example.TrabajointegradorbackendI.entity.Odontologo;
import com.example.TrabajointegradorbackendI.exception.BadRequestException;
import com.example.TrabajointegradorbackendI.service.IOdontologoService;
import com.example.TrabajointegradorbackendI.service.implementacion.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) throws BadRequestException {

        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }


    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }


    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos() {
        return ResponseEntity.ok(odontologoService.listarTodos());
    }


    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Odontologo odontologo) throws BadRequestException {
        ResponseEntity<String> response;
        Odontologo odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBuscado != null) {
            odontologoService.actualizar(odontologo);
            response = ResponseEntity.ok("Se actualizó el odontologo con id " + odontologo.getId());
        } else {
            response = ResponseEntity.ok().body("No se puede actualizar el odontologo");
        }
        return response;

    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Odontologo> findByMatricula(@PathVariable String matricula) throws Exception {
        Optional<Odontologo> odontologoOptional = odontologoService.findByMatricula(matricula);
        if (odontologoOptional.isPresent()) {
            return ResponseEntity.ok(odontologoOptional.get());
        } else {
            throw new Exception("odontologo con matricula no disponible" + matricula);
        }

    }

    @DeleteMapping("/{id)")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable Long id) {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}



