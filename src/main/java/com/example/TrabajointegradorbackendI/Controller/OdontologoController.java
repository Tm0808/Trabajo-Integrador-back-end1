package com.example.TrabajointegradorbackendI.Controller;

import com.example.TrabajointegradorbackendI.entity.Odontologo;
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
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id)  {
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        return odontologo != null ? ResponseEntity.ok(odontologo.get()) : ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        Odontologo nuevoOdontologo = null;
        try {
            nuevoOdontologo = odontologoService.guardar(odontologo);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoOdontologo);

    }


    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos() {
        List<Odontologo> listaBuscados = odontologoService.listarTodos();
        return ResponseEntity.ok(listaBuscados);
    }


    @PutMapping
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo) throws Exception {
        ResponseEntity<Odontologo> response;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBuscado.isPresent()) {
            odontologoService.guardar(odontologo);
            response = new ResponseEntity(odontologo, HttpStatus.OK);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        ResponseEntity<String> response;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        if (odontologoBuscado.isPresent()) {
            odontologoService.eliminar(id);
            response = ResponseEntity.ok("Se elimino el odontologo con id " + id);
        }
        else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

}





