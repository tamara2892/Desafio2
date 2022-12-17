package desafio4.demo.controllers;

import desafio4.demo.model.domain.direccionDTO.DireccionReadDTO;
import desafio4.demo.service.DireccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direccion")
public class DireccionController {

    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @GetMapping
    public ResponseEntity<List<DireccionReadDTO>> findAll() {
        return ResponseEntity.ok(direccionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionReadDTO> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(direccionService.findById(id));
    }
}
