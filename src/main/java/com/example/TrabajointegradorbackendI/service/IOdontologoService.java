package com.example.TrabajointegradorbackendI.service;

import com.example.TrabajointegradorbackendI.entity.Odontologo;
import com.example.TrabajointegradorbackendI.exception.BadRequestException;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardar (Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorId(Integer id);

    Odontologo buscarPorId(Long id) throws BadRequestException;

    void actualizar(Odontologo odontologo);

    Optional<Odontologo> findByMatricula (String matricula) throws BadRequestException;

    void eliminarOdontologo(Long id);
}
