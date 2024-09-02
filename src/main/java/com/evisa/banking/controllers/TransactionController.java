package com.evisa.banking.controllers;

import com.evisa.banking.dto.TransactionDto;
import com.evisa.banking.dto.TransactionDto;
import com.evisa.banking.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    public final TransactionService transactionService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(transactionService.save(transactionDto));
    }


    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> findAll(){
        return ResponseEntity.ok(transactionService.findall());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(transactionService.findById(id));
    }


    @GetMapping("findAllByUserId/{id}")
    public ResponseEntity<List<TransactionDto>> findAllByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(transactionService.findAllByUserID(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        transactionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
