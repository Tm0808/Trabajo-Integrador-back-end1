package com.example.TrabajointegradorbackendI.service;

import com.example.TrabajointegradorbackendI.dto.request.TurnoRequestDTO;
import com.example.TrabajointegradorbackendI.dto.response.TurnoResponseDTO;
import com.example.TrabajointegradorbackendI.entity.Turno;
import com.example.TrabajointegradorbackendI.exception.BadRequestException;
import com.example.TrabajointegradorbackendI.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
public interface ITurnoService {
    TurnoResponseDTO guardar(TurnoRequestDTO turno);


    List<TurnoResponseDTO> listarTodos() throws BadRequestException;


    Optional<TurnoResponseDTO> buscarPorId(Long id) throws ResourceNotFoundException;
    void eliminar(Integer id) throws BadRequestException;

    void actualizar(Turno turno);
}
