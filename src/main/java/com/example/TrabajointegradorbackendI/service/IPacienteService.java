package com.example.TrabajointegradorbackendI.service;

import com.example.TrabajointegradorbackendI.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    Paciente guardar (Paciente paciente);

    List<Paciente> listarTodos();

    Paciente buscarPorId(Integer id);

    void eliminar(Integer id);

    Paciente buscarPorId(Long id);

    void eliminar(Long id);

    void actualiza(Paciente paciente);
}
