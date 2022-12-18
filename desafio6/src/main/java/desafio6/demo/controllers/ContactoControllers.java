package desafio6.demo.controllers;

import desafio6.demo.model.domain.MensajeDTO;
import desafio6.demo.model.domain.contactoDTO.ContactoAddDTO;
import desafio6.demo.model.domain.contactoDTO.ContactoReadDTO;
import desafio6.demo.model.domain.contactoDTO.ListaContactosDTO;
import desafio6.demo.service.ContactoService;
import org.springframework.data.domain.Page;
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

   @PostMapping
    public ResponseEntity<MensajeDTO> save(@RequestBody ContactoAddDTO contactodto) {
        return ResponseEntity.ok(contactoService.add(contactodto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactoReadDTO> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contactoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contactoService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Page<ContactoReadDTO>> getAllWithPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "8") Integer size) {

        return ResponseEntity.ok(contactoService.findAllWithPagitation(page, size));
    }

    @GetMapping("/filtros")
    public ResponseEntity<ListaContactosDTO> getAllWithFilters(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "8") Integer size,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String celular) {

        return ResponseEntity.ok(contactoService.findAllWithFilters(page, size, nombre, celular));
    }

    @GetMapping("/direcciones/{id}/contactos")
    public ResponseEntity<ListaContactosDTO> getAllByDireccion(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contactoService.findAllByDireccion(id));
    }

}