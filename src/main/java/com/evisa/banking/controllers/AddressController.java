package com.evisa.banking.controllers;

import com.evisa.banking.dto.AddressDto;
import com.evisa.banking.services.AccountService;
import com.evisa.banking.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    public final AddressService addressService;

    // save, findAll, findBydId, delete, update

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody AddressDto addressDto){
       return ResponseEntity.ok(addressService.save(addressDto));
    }


    @GetMapping("/")
    public ResponseEntity<List<AddressDto>> findAll(){
        return ResponseEntity.ok(addressService.findall());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(addressService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        addressService.delete(id);
        return ResponseEntity.ok().build();
    }








}
