package com.evisa.banking.controllers;


import com.evisa.banking.dto.ContactDto;
import com.evisa.banking.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {
    
    public final ContactService contactService;


    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody ContactDto contactDto){
        return ResponseEntity.ok(contactService.save(contactDto));
    }


    @GetMapping("/")
    public ResponseEntity<List<ContactDto>> findAll(){
        return ResponseEntity.ok(contactService.findall());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(contactService.findById(id));
    }


    @GetMapping("findAllByUserId/{id}")
    public ResponseEntity<List<ContactDto>> findAllByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(contactService.findAllByUserId(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        contactService.delete(id);
        return ResponseEntity.ok().build();
    }
    
    
    
}
