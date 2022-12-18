package desafio5.demo.controllers;

import desafio5.demo.model.domain.direccionDTO.DireccionReadDTO;
import desafio5.demo.model.domain.direccionDTO.ListaDireccionesDTO;
import desafio5.demo.service.DireccionService;
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

    @GetMapping("/filtros")
    public ResponseEntity<ListaDireccionesDTO> getAllWithFilters(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "8") Integer size,
            @RequestParam(required = false) String calle,
            @RequestParam(required = false) String numero) {

        return ResponseEntity.ok(direccionService.findAllWithFilters(page, size, calle, numero));
    }
}
