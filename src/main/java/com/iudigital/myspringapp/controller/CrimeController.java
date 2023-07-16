package com.iudigital.myspringapp.controller;

import com.iudigital.myspringapp.dto.request.CrimeDtoRequest;
import com.iudigital.myspringapp.dto.response.CrimeDtoResponse;
import com.iudigital.myspringapp.service.interfaces.ICrimeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crimes")
public class CrimeController {

    final
    ICrimeService crimeService;
    public CrimeController(ICrimeService crimeService) {
        this.crimeService = crimeService;
    }

    @GetMapping
    public ResponseEntity<List<CrimeDtoResponse>> index(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(crimeService.getAll());
    }


    @PostMapping
    public ResponseEntity<CrimeDtoResponse> store(@Valid @RequestBody CrimeDtoRequest crimeDtoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(crimeService.addCrime(crimeDtoRequest));
    }

}
