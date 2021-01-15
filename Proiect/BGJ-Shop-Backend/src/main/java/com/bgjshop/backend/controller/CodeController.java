package com.bgjshop.backend.controller;

import com.bgjshop.backend.dto.CodeDto;
import com.bgjshop.backend.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/code")
public class CodeController {

    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(path = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CodeDto> get(@PathVariable String code) {
        CodeDto codeDto = codeService.get(code);
        return new ResponseEntity<>(codeDto, HttpStatus.OK);
    }

    @GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CodeDto> getAll() {
        return codeService.getAll();
    }

    @PostMapping(path = "/addCode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CodeDto> addCode(@Valid @RequestBody CodeDto request) {
        codeService.addCode(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteCode/{code}")
    public void deleteCode(@PathVariable String code) {
        codeService.deleteCode(code);
    }
}
