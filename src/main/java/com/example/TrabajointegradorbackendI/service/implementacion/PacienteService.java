package com.example.TrabajointegradorbackendI.service.implementacion;
import com.example.TrabajointegradorbackendI.entity.Paciente;
import com.example.TrabajointegradorbackendI.exception.BadRequestException;
import com.example.TrabajointegradorbackendI.exception.ResourceNotFoundException;
import com.example.TrabajointegradorbackendI.service.IPacienteService;
import com.example.TrabajointegradorbackendI.repository.IPacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private static IPacienteRepository pacienteRepository;
    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardar(Paciente paciente) throws BadRequestException {
        LOGGER.info("Persistiendo un Paciente");
        Optional<Paciente> odontologoOptional = pacienteRepository.findByDni(paciente.getDni());
        if (odontologoOptional.isEmpty()) {
            if (paciente.getNombre() == null || paciente.getApellido() == null|| paciente.getDni() == null
            ||paciente.getNombre().trim().isEmpty()||
            paciente.getApellido().trim().isEmpty() || paciente.getDni().trim().isEmpty()){
                throw new BadRequestException("No puedes poner campos vacíos");
            }
            LOGGER.info("Guardado exitosamente");
            return pacienteRepository.save(paciente);
        } else {
            LOGGER.error("Un paciente con el mismo Dni ya se encontraba en la base de datos");
            throw new BadRequestException("Un paciente con el mismo Dni ya se encontraba en la base de datos");
        }
    }

    @Override
    public List<Paciente> listarTodos() {
        LOGGER.info("Buscando la lista de pacientes");
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> buscarPorId(Long id) throws ResourceNotFoundException {
        LOGGER.info("Buscando el paciente con id: " + id);
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            return pacienteOptional;
        } else {
            throw new ResourceNotFoundException("No se encontro al paciente");
        }
    }

    @Override
    public void actualizar(Paciente paciente) throws BadRequestException {
        LOGGER.info("actualizando el paciente con id: " + paciente.getId());
        Optional<Paciente> pacienteOptional = pacienteRepository.findByDni(paciente.getDni());

        if (pacienteOptional.isPresent() && pacienteOptional.get().getId().equals(paciente.getId())) {
            if (paciente.getNombre() == null || paciente.getApellido() == null || paciente.getDni() == null
                    || paciente.getNombre().trim().isEmpty() || paciente.getApellido().trim().isEmpty() || paciente.getDni().trim().isEmpty()) {
                LOGGER.error("Al menos uno de los campos a actualizar está vacío");
                throw new BadRequestException("No puedes poner campos vacíos");
            }
            LOGGER.info("Actualizado exitosamente");
            pacienteRepository.save(paciente);
        } else if (pacienteOptional.isEmpty()) {
            LOGGER.error("No existe un paciente con este dni en la base de datos");
            throw new BadRequestException("El dni del paciente no existe");
        } else {
            LOGGER.error("Error desconocido al intentar actualizar el paciente");
            throw new BadRequestException("Error desconocido al intentar actualizar el paciente");
        }
    }


    @Override
    public void eliminar(Long id) throws ResourceNotFoundException {
        LOGGER.info("Eliminando al paciente con id: " + id);
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            LOGGER.info("Paciente eliminado exitosamente");
            pacienteRepository.deleteById(id);

        } else {
            throw new ResourceNotFoundException("El Paciente con id: " + id + " no se encontro en la base de datos");
        }
    }

    @Override
    public Optional<Paciente> findByDni(String dni) throws ResourceNotFoundException {
        LOGGER.info("Buscando al paciente con dni: " + dni);
        Optional<Paciente> pacienteOptional = pacienteRepository.findByDni(dni);
        if (pacienteOptional.isPresent()) {
            return pacienteOptional;
        } else {
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }
}




