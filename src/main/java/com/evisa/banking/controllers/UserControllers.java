package com.evisa.banking.controllers;

import com.evisa.banking.dto.UserDto;
import com.evisa.banking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllers {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody UserDto dto){
        return ResponseEntity.ok(userService.save(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(userService.findall());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PatchMapping("/validate/{id}")
    public ResponseEntity<Integer> validateAccount(@PathVariable Integer id){
        return ResponseEntity.ok(userService.validateAccount(id));
    }

    @PatchMapping("/invalidate/{id}")
    public ResponseEntity<Integer> invalidateAccount(@PathVariable Integer id){
        return ResponseEntity.ok(userService.invalidateAccount(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}
