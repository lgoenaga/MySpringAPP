package com.iudigital.myspringapp.service.interfaces;

import com.iudigital.myspringapp.dto.request.CaseDtoRequest;
import com.iudigital.myspringapp.dto.response.CaseDtoResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ICaseService {


    List<CaseDtoResponse> getAll();

    CaseDtoResponse addCase(CaseDtoRequest caseDtoRequest);
}
