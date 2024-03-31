package com.example.TrabajointegradorbackendI.Controller;

import com.example.TrabajointegradorbackendI.dto.request.TurnoRequestDTO;
import com.example.TrabajointegradorbackendI.dto.response.TurnoResponseDTO;
import com.example.TrabajointegradorbackendI.exception.BadRequestException;
import com.example.TrabajointegradorbackendI.exception.ResourceNotFoundException;
import com.example.TrabajointegradorbackendI.service.IOdontologoService;
import com.example.TrabajointegradorbackendI.service.IPacienteService;
import com.example.TrabajointegradorbackendI.service.ITurnoService;
import com.example.TrabajointegradorbackendI.service.implementacion.OdontologoService;
import com.example.TrabajointegradorbackendI.service.implementacion.PacienteService;
import com.example.TrabajointegradorbackendI.service.implementacion.TurnoService;
import org.antlr.v4.runtime.misc.NotNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private static final Logger LOGGER = Logger.getLogger(TurnoController.class);

    private ITurnoService turnoService;
    private IOdontologoService odontologoService;
    private IPacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<TurnoResponseDTO> guardar (@RequestBody TurnoRequestDTO turnoRequestDTO) throws BadRequestException {
        ResponseEntity<TurnoResponseDTO> response;

        LOGGER.info("esto trae el turno: " + turnoRequestDTO);

        if (odontologoService.buscarPorId(turnoRequestDTO.getOdontologo_id()) != null &&
                pacienteService.buscarPorId(turnoRequestDTO.getPaciente_id()) != null) {

            response = ResponseEntity.ok(turnoService.guardar(turnoRequestDTO));

        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
        return response;
    }
    @GetMapping
    public ResponseEntity<List<TurnoResponseDTO>> findAll() throws BadRequestException {
        return ResponseEntity.ok(turnoService.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponseDTO> findById(@PathVariable Long id) {

        turnoService.buscarPorId(id);
        return ResponseEntity.ok().build();



    }

}
