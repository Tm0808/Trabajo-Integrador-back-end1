package com.example.TrabajointegradorbackendI.service;

import com.example.TrabajointegradorbackendI.entity.Paciente;
import com.example.TrabajointegradorbackendI.exception.BadRequestException;

import java.util.List;

public interface IPacienteService {

    Paciente guardar (Paciente paciente);

    List<Paciente> listarTodos() throws BadRequestException;

    Paciente buscarPorId(Integer id) throws BadRequestException;

    void eliminar(Integer id) throws BadRequestException;

    Paciente buscarPorId(Long id);

    void eliminar(Long id);

    void actualiza(Paciente paciente);
}
