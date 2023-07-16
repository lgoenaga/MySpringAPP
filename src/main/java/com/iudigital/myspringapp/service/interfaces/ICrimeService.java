package com.iudigital.myspringapp.service.interfaces;

import com.iudigital.myspringapp.dto.request.CrimeDtoRequest;
import com.iudigital.myspringapp.dto.response.CrimeDtoResponse;

import java.util.List;

public interface ICrimeService {

        List<CrimeDtoResponse> getAll();

        CrimeDtoResponse getCrimeById(Long id);

        CrimeDtoResponse addCrime(CrimeDtoRequest crimeDtoRequest);

        String deleteCrimeById(Long id);
}
