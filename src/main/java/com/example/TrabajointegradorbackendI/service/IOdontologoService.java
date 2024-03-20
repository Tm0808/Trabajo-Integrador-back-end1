package com.example.TrabajointegradorbackendI.service;

import com.example.TrabajointegradorbackendI.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    Odontologo guardar (Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorId(Integer id);

    Odontologo buscarPorId(Long id);

    void actualizar(Odontologo odontologo);
}
