package com.example.demo.resource;

import com.example.demo.model.Funcionario;
import com.example.demo.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository repository;

    @PostMapping
    public ResponseEntity create(@RequestBody Funcionario entity){
        repository.save(entity);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(defaultValue = "9") int page,
                                  @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Funcionario> funcionarioPage = repository.findAll(pageable);
        Page<FuncionarioDTO> funcionarioDTOPage = funcionarioPage.map(FuncionarioDTO::fromEntity);
                return ResponseEntity.ok(funcionarioDTOPage);
    }
}
