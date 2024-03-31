package com.example.TrabajointegradorbackendI.service.implementacion;
import com.example.TrabajointegradorbackendI.entity.Odontologo;
import com.example.TrabajointegradorbackendI.service.IOdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private IOdontologoService odontologoService;

    @Test
    void guardar() {
        Integer odontologoId = 1;
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Tomas");
        odontologo.setApellido("Martinolich");
        odontologo.setMatricula("234");
        odontologoService.guardar(odontologo);

        assertNotNull(odontologo);

    }

    @Test
    void buscarPorId() {
    }

    @Test
    void findByMatricula() {
    }
}