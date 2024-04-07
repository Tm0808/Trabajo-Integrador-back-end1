package com.example.TrabajointegradorbackendI.service.implementacion;
import com.example.TrabajointegradorbackendI.entity.Odontologo;
import com.example.TrabajointegradorbackendI.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;
    Odontologo odontologo1 = new Odontologo("Tomás","Martinolich","1");
    Odontologo odontologo2 = new Odontologo("Martina","Gomez","2");
    @Test
    void guardar() throws BadRequestException {
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo1);
        assertEquals(odontologoGuardado,odontologo1);
    }

    @Test
    public void testListarTodos() throws BadRequestException {
        odontologoService.guardar(odontologo1);
        odontologoService.guardar(odontologo2);
        List<Odontologo> odontologos = odontologoService.listarTodos();


        assertEquals(2, odontologos.size());

    }

    @Test
    void buscarPorId() throws BadRequestException {
        odontologoService.guardar(odontologo1);
        Odontologo odontologoEncontrado = new Odontologo();
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(odontologo1.getId());
        if (odontologoBuscado.isPresent()) {
            odontologoEncontrado = odontologoBuscado.get();
        }
        assertEquals(odontologo1.getId(),odontologoEncontrado.getId());
    }

    @Test
    void actualizar() throws BadRequestException {
        Odontologo odontologo1Actualizado = new Odontologo(1L,"1","1","1",null);
        odontologoService.guardar(odontologo1);
        odontologoService.actualizar(odontologo1Actualizado);
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(1L);
        assertEquals(odontologo.get().getId(),odontologo1Actualizado.getId());
        assertEquals(odontologo.get().getNombre(),odontologo1Actualizado.getNombre());
        assertEquals(odontologo.get().getApellido(),odontologo1Actualizado.getApellido());

    }
    @Test
    void eliminar() throws BadRequestException {
        odontologoService.guardar(odontologo1);
        odontologoService.guardar(odontologo2);
        odontologoService.eliminar(odontologo1.getId());
        List<Odontologo> odontologoList =odontologoService.listarTodos();
        assertEquals(odontologoList.size(),1);
    }

    @Test
    void findByNombre() throws BadRequestException {
        odontologoService.guardar(odontologo1);
        Optional<List<Odontologo>> odontologoOptional = odontologoService.findByNombre(odontologo1.getNombre());
        assertEquals(odontologo1.getApellido(), odontologoOptional.get().get(0).getApellido());
        assertEquals(odontologo1.getNombre(), odontologoOptional.get().get(0).getNombre());
        assertEquals(odontologo1.getMatricula(), odontologoOptional.get().get(0).getMatricula());
    }

    @Test
    void findByApellido() throws BadRequestException {
        odontologoService.guardar(odontologo1);
        Optional<List<Odontologo>> odontologoOptional = odontologoService.findByApellido(odontologo1.getApellido());
        assertEquals(odontologo1.getApellido(), odontologoOptional.get().get(0).getApellido());
        assertEquals(odontologo1.getNombre(), odontologoOptional.get().get(0).getNombre());
        assertEquals(odontologo1.getMatricula(), odontologoOptional.get().get(0).getMatricula());
    }

    @Test
    void findByMatricula() throws BadRequestException {
        odontologoService.guardar(odontologo1);
        Optional<Odontologo> odontologoOptional = odontologoService.findByMatricula(odontologo1.getMatricula());
        assertEquals(odontologo1.getApellido(), odontologoOptional.get().getApellido());
        assertEquals(odontologo1.getApellido(), odontologoOptional.get().getApellido());
        assertEquals(odontologo1.getNombre(), odontologoOptional.get().getNombre());
        assertEquals(odontologo1.getMatricula(), odontologoOptional.get().getMatricula());
    }

}
