package com.example.TrabajointegradorbackendI.Controller;

import com.example.TrabajointegradorbackendI.dto.request.TurnoRequestDTO;
import com.example.TrabajointegradorbackendI.dto.response.TurnoResponseDTO;
import com.example.TrabajointegradorbackendI.service.IOdontologoService;
import com.example.TrabajointegradorbackendI.service.IPacienteService;
import com.example.TrabajointegradorbackendI.service.ITurnoService;
import com.example.TrabajointegradorbackendI.service.implementacion.OdontologoService;
import com.example.TrabajointegradorbackendI.service.implementacion.PacienteService;
import com.example.TrabajointegradorbackendI.service.implementacion.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<TurnoResponseDTO> guardar (@RequestBody TurnoRequestDTO turnoRequestDTO) throws Exception {
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
    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<TurnoResponseDTO> turnoResponseDTOOptional = turnoService.buscarPorId(id);
        if (turnoResponseDTOOptional.isPresent()){
            return ResponseEntity.ok(turnoResponseDTOOptional.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response;
        Optional<TurnoResponseDTO> turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()) {
            turnoService.eliminar(id);
            response = ResponseEntity.ok("Se eliminó el turno con id " + id);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }
    @GetMapping
    public ResponseEntity<List<TurnoResponseDTO>> listarTodos() {
        List<TurnoResponseDTO> listaTurnos = turnoService.listarTodos();
        if (!listaTurnos.isEmpty()) {
            return ResponseEntity.ok(listaTurnos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<TurnoResponseDTO>> buscarPorPacienteId(@RequestParam Long id) {
        Optional<List<TurnoResponseDTO>> optionalTurnoResponseDTOList = turnoService.findByPacienteId(id);
        if (optionalTurnoResponseDTOList.isPresent()) {
            List<TurnoResponseDTO> turnoResponseDTOList = optionalTurnoResponseDTOList.get();
            return ResponseEntity.ok(turnoResponseDTOList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/odontolgo/{id}")
    public ResponseEntity<List<TurnoResponseDTO>> buscarPorOdontologoId(@RequestParam Long id) {
        Optional<List<TurnoResponseDTO>> optionalTurnoResponseDTOList = turnoService.findByPacienteId(id);
        if (optionalTurnoResponseDTOList.isPresent()) {
            List<TurnoResponseDTO> turnoResponseDTOList = optionalTurnoResponseDTOList.get();
            return ResponseEntity.ok(turnoResponseDTOList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


