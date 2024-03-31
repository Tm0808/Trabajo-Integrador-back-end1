package com.example.TrabajointegradorbackendI.repository;
import com.example.TrabajointegradorbackendI.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {
    @Query ("SELECT o FROM Odontologo o WHERE o.matricula = ?1")
    Optional <Odontologo> findByMatricula (String matricula);
    //@Query  ("SELECT o FROM OdontoLogo o WHERE o.nombre = ?1")
    //Optional <List<Odontologo>> findByNombre (String nombre);
    //@Query ("SELECT o FROM OdontoLogo o WHERE o.apellido = ?1")
    //Optional <List<Odontologo>> findByApellido (String apellido);

}
