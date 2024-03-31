package com.example.TrabajointegradorbackendI.repository;
import com.example.TrabajointegradorbackendI.entity.Domicilio;
import com.example.TrabajointegradorbackendI.entity.Odontologo;
import com.example.TrabajointegradorbackendI.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
    //@Query("SELECT o FROM Paciente o WHERE o.nombre = ?1")
    //Optional<List<Paciente>> findByNombre(String nombre);
    //@Query ("SELECT o FROM Paciente o WHERE o.apellido = ?1")
    //Optional <List<Paciente>> findByApellido(String apellido);

}
