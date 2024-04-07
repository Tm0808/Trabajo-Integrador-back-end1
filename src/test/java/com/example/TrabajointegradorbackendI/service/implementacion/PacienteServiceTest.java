package com.example.TrabajointegradorbackendI.service.implementacion;

import com.example.TrabajointegradorbackendI.entity.Domicilio;
import com.example.TrabajointegradorbackendI.entity.Paciente;
import com.example.TrabajointegradorbackendI.service.IPacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private IPacienteService pacienteService;
    Paciente paciente1 = new Paciente(null,"J","J","1", LocalDate.now(),null,null);
    Paciente paciente2 = new Paciente(null,"A","A","2", LocalDate.now(),null,null);
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
    @Test
    void guardar() throws Exception {
        Paciente pacienteGuardado;
        pacienteService.guardar(paciente1);
    }
    @Test
    void actualizar(){

    }
    @Test
    void findByDni(){

    }
    }