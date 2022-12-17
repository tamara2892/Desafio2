package desafio4.demo.controllers;

import desafio4.demo.model.domain.contactoDTO.ContactoAddDTO;
import desafio4.demo.model.domain.contactoDTO.ContactoReadDTO;
import desafio4.demo.model.domain.MensajeDTO;
import desafio4.demo.service.ContactoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacto")
public class ContactoControllers {
    private final ContactoService contactoService;

    public ContactoControllers(ContactoService contactoService)  {
        this.contactoService = contactoService;
    }

    @GetMapping
    public ResponseEntity<List<ContactoReadDTO>> getAll() {
        return ResponseEntity.ok(contactoService.findAll());
    }

    @PostMapping
    public ResponseEntity<MensajeDTO> save(@RequestBody ContactoAddDTO contactodto) {
        return ResponseEntity.ok(contactoService.add(contactodto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactoReadDTO> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contactoService.findById(id));
    }

}