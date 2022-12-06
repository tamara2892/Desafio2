package desafio3.demo.controllers;

import desafio3.demo.model.domain.ContactoDTO;
import desafio3.demo.model.domain.MensajeDTO;
import desafio3.demo.service.ContactoService;
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
    public ResponseEntity<List<ContactoDTO>> findAll() {
        return ResponseEntity.ok(contactoService.findAll());
    }

    @PostMapping
    public ResponseEntity<MensajeDTO> add(@RequestBody ContactoDTO contactoDTO) {
        return ResponseEntity.ok(contactoService.add(contactoDTO));
    }

    @GetMapping("/{contactoid}")
    public ResponseEntity<ContactoDTO> findById(@PathVariable Integer contactoid){
        return ResponseEntity.ok(contactoService.findById(contactoid));
    }

}