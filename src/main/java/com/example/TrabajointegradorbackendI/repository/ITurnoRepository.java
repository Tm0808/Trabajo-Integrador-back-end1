package com.example.TrabajointegradorbackendI.repository;
import com.example.TrabajointegradorbackendI.entity.Paciente;
import com.example.TrabajointegradorbackendI.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
    //@Query("SELECT o FROM Turno o WHERE o.turno = ?1")
    //Optional<List<Turno>> findByTurno(String Turno);
}
