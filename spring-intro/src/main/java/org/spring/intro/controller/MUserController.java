package org.spring.intro.controller;

import lombok.RequiredArgsConstructor;
import org.spring.intro.model.dto.MUserDTO;
import org.spring.intro.model.mapper.MUserMapper;
import org.spring.intro.service.MUserService;
import org.spring.intro.model.entity.MUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MUserController {
    private final MUserService userService;
    private final MUserMapper userMapper;


    @PostMapping("/api/v1/user")
    public void saveUser(@RequestBody MUserDTO dto) {
        MUser entity = userMapper.map(dto);
        userService.save(entity);
    }

    @GetMapping("/api/v1/user/{id}")
    public ResponseEntity<MUserDTO> getUserById(@PathVariable("id") Long id) {
        MUser user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(userMapper.map(user));
        } else return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/login")
    private ResponseEntity<String> login() {
        System.out.println("Hello World");
        return ResponseEntity.ok("Hello World");
    }

}
