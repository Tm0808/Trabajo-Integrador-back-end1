package com.example.TrabajointegradorbackendI.service.implementacion;

import com.example.TrabajointegradorbackendI.dto.request.TurnoRequestDTO;
import com.example.TrabajointegradorbackendI.dto.response.TurnoResponseDTO;
import com.example.TrabajointegradorbackendI.entity.Odontologo;
import com.example.TrabajointegradorbackendI.entity.Paciente;
import com.example.TrabajointegradorbackendI.entity.Turno;
import com.example.TrabajointegradorbackendI.exception.BadRequestException;
import com.example.TrabajointegradorbackendI.exception.ResourceNotFoundException;
import com.example.TrabajointegradorbackendI.repository.ITurnoRepository;
import com.example.TrabajointegradorbackendI.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;
    private final Logger LOGGER = Logger.getLogger(OdontologoService.class);
    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public TurnoResponseDTO guardar(TurnoRequestDTO turnoRequestDTO) {

        Turno turnoEntity = new Turno();


        Paciente paciente = new Paciente();
        paciente.setId(turnoRequestDTO.getPaciente_id());

        Odontologo odontologo = new Odontologo();
        odontologo.setId(turnoRequestDTO.getOdontologo_id());

        turnoEntity.setOdontologo(odontologo);
        turnoEntity.setPaciente(paciente);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(turnoRequestDTO.getFecha(), formatter);

        turnoEntity.setFecha(date);

        turnoRepository.save(turnoEntity);

        TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();
        turnoResponseDTO.setId(turnoEntity.getId());
        turnoResponseDTO.setOdontologo_id(turnoEntity.getOdontologo().getId());
        turnoResponseDTO.setPaciente_id(turnoEntity.getPaciente().getId());
        turnoResponseDTO.setFecha(turnoEntity.getFecha().toString());

        return turnoResponseDTO;
    }



    @Override
    public List<TurnoResponseDTO> listarTodos() throws BadRequestException {
        LOGGER.error("Error en un items de la lista");
        throw new BadRequestException("Error en un items de la lista");
    }

    @Override
    public Optional <TurnoResponseDTO> buscarPorId(Long id) {
        Optional<Turno> turnoABuscar = turnoRepository.findById(id);


        Optional<TurnoResponseDTO> turnoOptional = null;

        if (turnoABuscar.isPresent()) {

            Turno turno = turnoABuscar.get();

            TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();
            turnoResponseDTO.setId(turno.getId());
            turnoResponseDTO.setOdontologo_id(turno.getOdontologo().getId());
            turnoResponseDTO.setPaciente_id(turno.getPaciente().getId());
            turnoResponseDTO.setFecha(turno.getFecha().toString());

            turnoOptional = Optional.of(turnoResponseDTO);

            return turnoOptional;
        } else {
            LOGGER.error("No se encontró el turno con id + id");
            throw new ResourceNotFoundException("No se encontró el turno con  id " + id);
        }
    }


    @Override
    public void eliminar(Integer id) throws BadRequestException {
        LOGGER.error("error al eliminar un turno");
        throw new BadRequestException("error al eliminar un turno");

    }

    @Override
    public void actualizar(Turno turno) {


    }
}
