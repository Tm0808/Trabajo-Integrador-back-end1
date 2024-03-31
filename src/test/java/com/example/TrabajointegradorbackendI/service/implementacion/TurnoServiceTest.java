package com.example.TrabajointegradorbackendI.service.implementacion;

import com.example.TrabajointegradorbackendI.entity.Domicilio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    @Test
    void listarTodos() {
    }

    @Test
    void buscarPorId() {
        Domicilio domicilio1 = new Domicilio();
        domicilio1.setCalle("25 de mayo");
        domicilio1.setNumero(1300);
        domicilio1. setLocalidad ("Tigre");
        domicilio1.setProvincia("Buenos Aires");
    }

    @Test
    void eliminar() {

        Domicilio domicilio1 = new Domicilio();
        domicilio1.setCalle("25 de mayo");
        domicilio1.setNumero(1300);
        domicilio1. setLocalidad ("Tigre");
        domicilio1.setProvincia("Buenos Aires");

    }
}