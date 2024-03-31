package com.example.TrabajointegradorbackendI.service.implementacion;

import com.example.TrabajointegradorbackendI.entity.Odontologo;
import com.example.TrabajointegradorbackendI.exception.BadRequestException;
import com.example.TrabajointegradorbackendI.repository.IOdontologoRepository;
import com.example.TrabajointegradorbackendI.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OdontologoService implements IOdontologoService {
    private IOdontologoRepository odontologoRepository;
    private final Logger LOGGER = Logger.getLogger(OdontologoService.class);

    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return null;
    }


    @Override
    public Odontologo buscarPorId(Long id) throws BadRequestException {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
        if (odontologoOptional.isPresent()) {
            return odontologoOptional.get();
        } else { LOGGER.error("Error en buscarPorId");
            throw new BadRequestException("Error en buscarPorId");

        }

    }

    @Override
    public void actualizar(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> findByMatricula(String matricula) throws BadRequestException {
        LOGGER.error("Error en el campo de Matricula");
        throw new BadRequestException("Error en el campo de Matricula");
        //return odontologoRepository.findByMatricula (matricula);
    }

    @Override
    public void eliminarOdontologo(Long id) {

    }
}

