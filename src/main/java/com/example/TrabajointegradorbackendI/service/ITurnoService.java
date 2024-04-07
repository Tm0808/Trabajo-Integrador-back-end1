package com.example.TrabajointegradorbackendI.service;

import com.example.TrabajointegradorbackendI.dto.request.TurnoRequestDTO;
import com.example.TrabajointegradorbackendI.dto.response.TurnoResponseDTO;
import com.example.TrabajointegradorbackendI.entity.Turno;

import java.util.List;
import java.util.Optional;
public interface ITurnoService {
    TurnoResponseDTO guardar(TurnoRequestDTO turno) throws Exception;
    List<TurnoResponseDTO> listarTodos();


    Optional<TurnoResponseDTO> buscarPorId(Long id);

    void eliminar(Long id);

    void actualizar(Turno turno);
    Optional<List<TurnoResponseDTO>> findByOdontologoId(Long id);
    Optional<List<TurnoResponseDTO>> findByPacienteId(Long id);

}
