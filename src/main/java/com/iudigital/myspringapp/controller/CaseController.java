package com.iudigital.myspringapp.controller;

import com.iudigital.myspringapp.dto.request.CaseDtoRequest;
import com.iudigital.myspringapp.dto.response.CaseDtoResponse;
import com.iudigital.myspringapp.service.interfaces.ICaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cases")
public class CaseController {

    final
    ICaseService caseService;


    public CaseController(ICaseService caseService) {
        this.caseService = caseService;
    }


    @GetMapping
    public ResponseEntity<List<CaseDtoResponse>> index(){
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(caseService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<CaseDtoResponse> store(@Valid @RequestBody CaseDtoRequest caseDtoRequest){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(caseService.addCase(caseDtoRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
