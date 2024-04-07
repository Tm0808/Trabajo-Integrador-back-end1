package com.example.TrabajointegradorbackendI.service.implementacion;

import com.example.TrabajointegradorbackendI.dto.request.TurnoRequestDTO;
import com.example.TrabajointegradorbackendI.dto.response.TurnoResponseDTO;
import com.example.TrabajointegradorbackendI.entity.Odontologo;
import com.example.TrabajointegradorbackendI.entity.Paciente;
import com.example.TrabajointegradorbackendI.entity.Turno;
import com.example.TrabajointegradorbackendI.exception.BadRequestException;
import com.example.TrabajointegradorbackendI.exception.ResourceNotFoundException;
import com.example.TrabajointegradorbackendI.repository.ITurnoRepository;
import com.example.TrabajointegradorbackendI.service.IOdontologoService;
import com.example.TrabajointegradorbackendI.service.IPacienteService;
import com.example.TrabajointegradorbackendI.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;
    private IPacienteService pacienteService;
    private IOdontologoService odontologoService;
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(OdontologoService.class);
    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }
@Override
    public TurnoResponseDTO guardar(TurnoRequestDTO turnoRequestDTO) throws BadRequestException {
        LOGGER.info("Persitiendo un Turno");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate date = LocalDate.parse(turnoRequestDTO.getFecha(), formatter);
        LocalTime time = LocalTime.parse(turnoRequestDTO.getHora(),formatterTime);

        Optional<List<TurnoResponseDTO>> turnosChequeoOdontologo = findByOdontologoId(turnoRequestDTO.getOdontologo_id());
        if (turnosChequeoOdontologo.isPresent()) {
            List<TurnoResponseDTO> turnosOdontologo = turnosChequeoOdontologo.get();

            for (TurnoResponseDTO turnoExistente : turnosOdontologo) {
                LocalDate fechaTurnoExistente = LocalDate.parse(turnoExistente.getFecha(), formatter);
                LocalTime horaTurnoExistente = LocalTime.parse(turnoExistente.getHora(), formatterTime);
                if (fechaTurnoExistente.equals(date) && horaTurnoExistente.equals(time)) {
                    LOGGER.error("Este odontologo ya tiene un turno asignado en esta fecha y hora");
                    throw new BadRequestException("Este odontologo ya tiene un turno asignado en esta fecha y hora");
                }
            }
        }
        Turno turnoEntity = new Turno();

        Paciente paciente = new Paciente();
        paciente.setId(turnoRequestDTO.getPaciente_id());

        Odontologo odontologo = new Odontologo();
        odontologo.setId(turnoRequestDTO.getOdontologo_id());

        turnoEntity.setOdontologo(odontologo);
        turnoEntity.setPaciente(paciente);

        turnoEntity.setFecha(date);
        turnoEntity.setHora(time);

        turnoRepository.save(turnoEntity);
        LOGGER.info("Turno Persistido");
        TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();
        turnoResponseDTO.setId(turnoEntity.getId());
        turnoResponseDTO.setHora(turnoEntity.getHora().toString());
        turnoResponseDTO.setOdontologo_id(turnoEntity.getOdontologo().getId());
        turnoResponseDTO.setPaciente_id(turnoEntity.getPaciente().getId());
        turnoResponseDTO.setFecha(turnoEntity.getFecha().toString());

        return turnoResponseDTO;
    }




    @Override
    public List<TurnoResponseDTO> listarTodos() {
        LOGGER.info("Buscando lista de turnos");
        List<TurnoResponseDTO> turnoResponseDTOList = new ArrayList<>();
        List<Turno> turnoList = turnoRepository.findAll();
        for (Turno turno:turnoList) {
            TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();
            turnoResponseDTO.setId(turno.getId());
            turnoResponseDTO.setFecha(turno.getFecha().toString());
            turnoResponseDTO.setHora(turno.getHora().toString());
            turnoResponseDTO.setPaciente_id(turno.getPaciente().getId());
            turnoResponseDTO.setOdontologo_id(turno.getOdontologo().getId());
            turnoResponseDTOList.add(turnoResponseDTO);
        }
        return turnoResponseDTOList;
    }



    @Override
    public Optional<TurnoResponseDTO> buscarPorId(Long id) throws ResourceNotFoundException {
        LOGGER.info("Buscando el turno con id: "+id );
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if (turnoOptional.isPresent()) {
            TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();
            Turno turno = turnoOptional.get();
            turnoResponseDTO.setId(turno.getId());
            turnoResponseDTO.setFecha(turno.getFecha().toString());
            turnoResponseDTO.setHora(turno.getHora().toString());
            turnoResponseDTO.setPaciente_id(turno.getPaciente().getId());
            turnoResponseDTO.setOdontologo_id(turno.getOdontologo().getId());
            return Optional.of(turnoResponseDTO);
        } else {
            throw  new ResourceNotFoundException("No se encontro el Turno con id: " +id);
        }

    }
    @Override
    public void eliminar(Long id) throws ResourceNotFoundException{
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if (turnoOptional.isPresent()) {
            turnoRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("El turno con id: " + id +" no se puede eliminar porque no existe");
        }
    }

    @Override
    public void actualizar(Turno turno) {
        Optional<Turno> turnoOptional = turnoRepository.findById(turno.getId());
        if (turnoOptional.isPresent()) {
            turnoRepository.save(turno);
        }
        else  {
            throw new ResourceNotFoundException("El turno con id: " + turno.getId() +" no se puede actualizar porque no existe");
        }
    }

    @Override
    public Optional<List<TurnoResponseDTO>> findByOdontologoId(Long id) throws ResourceNotFoundException {
        LOGGER.info("Buscando los turnos con Odontologo id: "+id);
        Optional<List<Turno>> turnoOptionalList = turnoRepository.findByOdontologoId(id);
        List<TurnoResponseDTO> turnoResponseDTOList = new ArrayList<>();
        if (turnoOptionalList.isPresent()) {
            for (Turno turno:turnoOptionalList.get()) {
                TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();
                turnoResponseDTO.setId(turno.getId());
                turnoResponseDTO.setFecha(turno.getFecha().toString());
                turnoResponseDTO.setHora(turno.getHora().toString());
                turnoResponseDTO.setPaciente_id(turno.getPaciente().getId());
                turnoResponseDTO.setOdontologo_id(turno.getOdontologo().getId());
                turnoResponseDTOList.add(turnoResponseDTO);
                return Optional.of(turnoResponseDTOList);
            }
        }
        return Optional.empty();
    }
    @Override
    public Optional<List<TurnoResponseDTO>> findByPacienteId(Long id) throws ResourceNotFoundException {
        LOGGER.info("Buscando los turnos con Paciente id: "+id);
        Optional<List<Turno>> turnoOptionalList = turnoRepository.findByPacienteId(id);
        List<TurnoResponseDTO> turnoResponseDTOList = new ArrayList<>();
        if (turnoOptionalList.isPresent()) {
            for (Turno turno:turnoOptionalList.get()) {
                TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();
                turnoResponseDTO.setId(turno.getId());
                turnoResponseDTO.setFecha(turno.getFecha().toString());
                turnoResponseDTO.setHora(turno.getHora().toString());
                turnoResponseDTO.setPaciente_id(turno.getPaciente().getId());
                turnoResponseDTO.setOdontologo_id(turno.getOdontologo().getId());
                turnoResponseDTOList.add(turnoResponseDTO);
                return Optional.of(turnoResponseDTOList);
            }
        }
        return Optional.empty();
    }

}





