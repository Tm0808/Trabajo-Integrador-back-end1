package com.example.TrabajointegradorbackendI.service.implementacion;
import com.example.TrabajointegradorbackendI.entity.Paciente;
import com.example.TrabajointegradorbackendI.exception.BadRequestException;
import com.example.TrabajointegradorbackendI.service.IPacienteService;
import com.example.TrabajointegradorbackendI.repository.IPacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private IPacienteRepository pacienteRepository;
    private final Logger LOGGER = Logger.getLogger(OdontologoService.class);
    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardar(Paciente paciente) {return pacienteRepository.save(paciente);}

    @Override
    public List<Paciente> listarTodos() throws BadRequestException {
        LOGGER.error("Error en un item de la lista");
        throw new BadRequestException("Error en el campo de Matricula");

    }

    @Override
    public Paciente buscarPorId(Integer id) throws BadRequestException {
        LOGGER.error("Error en buscarPorId");
        throw new BadRequestException("Error en bascarPorId");

    }

    @Override
    public void eliminar(Integer id) throws BadRequestException {
        LOGGER.error("Error al eliminar un Paciente");
        throw new BadRequestException("Error al eliminar un Paciente");

    }

    @Override
    public Paciente buscarPorId(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            return pacienteOptional.get();
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public void actualiza(Paciente paciente) {

    }
    }

