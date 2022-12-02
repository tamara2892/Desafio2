package com.Api_java.desafio2.controllers;

import com.Api_java.desafio2.domain.ContactoDTO;
import com.Api_java.desafio2.domain.MensajeDTO;
import com.Api_java.desafio2.service.ContactoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/contacto")
public class ContactoControllers {
    private final ContactoService contactoService;

    public ContactoControllers(ContactoService contactoService) {
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


    @GetMapping("/{id}")
    public ResponseEntity<ContactoDTO> findOne(@PathVariable Integer id) {
        return ResponseEntity.ok(contactoService.findOne(id));
    }
}
