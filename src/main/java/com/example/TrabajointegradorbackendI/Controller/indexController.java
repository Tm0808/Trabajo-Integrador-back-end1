package com.example.TrabajointegradorbackendI.Controller;
import com.example.TrabajointegradorbackendI.entity.Odontologo;
import com.example.TrabajointegradorbackendI.service.IOdontologoService;
import com.example.TrabajointegradorbackendI.service.implementacion.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/odontologo")
public class indexController {
    private IOdontologoService odontologoService;

    public indexController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    @GetMapping
    public String buscarOdontologoPorId(Model model, @RequestParam Long id, @RequestParam String nombre) {
        Odontologo odontologo = odontologoService.buscarPorId(Math.toIntExact(id));
        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());
        return "buscarOdontologo";
    }

    @GetMapping("/{id}")
    public String buscarOdontologoPorIdVariable(Model model, @PathVariable Long id) {
        Odontologo odontologo = odontologoService.buscarPorId(Math.toIntExact(id));
        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());
        return "buscarOdontologo";
    }


}
