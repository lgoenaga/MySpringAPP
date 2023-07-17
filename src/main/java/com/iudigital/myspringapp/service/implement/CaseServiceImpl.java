package com.iudigital.myspringapp.service.implement;

import com.iudigital.myspringapp.dto.request.CaseDtoRequest;
import com.iudigital.myspringapp.dto.response.CaseDtoResponse;
import com.iudigital.myspringapp.model.Case;
import com.iudigital.myspringapp.repository.CaseRepository;
import com.iudigital.myspringapp.repository.CrimeRepository;
import com.iudigital.myspringapp.repository.UserRepository;
import com.iudigital.myspringapp.service.interfaces.ICaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CaseServiceImpl implements ICaseService {


    final
    CaseRepository caseRepository;
    final
    UserRepository userRepository;

    final
    CrimeRepository crimeRepository;

    public CaseServiceImpl(CaseRepository caseRepository, UserRepository userRepository, CrimeRepository crimeRepository) {
        this.caseRepository = caseRepository;
        this.userRepository = userRepository;
        this.crimeRepository = crimeRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<CaseDtoResponse> getAll() {

        List<Case> cases = caseRepository.findAll();

        return cases.stream().map(caseCrime ->
                CaseDtoResponse.builder()
                        .id(caseCrime.getId())
                        .description(caseCrime.getDescription())
                        .dateCase(caseCrime.getDateCase())
                        .altitude(caseCrime.getAltitude())
                        .latitude(caseCrime.getLatitude())
                        .longitude(caseCrime.getLongitude())
                        .createdAt(caseCrime.getCreatedAt())
                        .updatedAt(caseCrime.getUpdatedAt())
                        .user_id(caseCrime.getUser().getId())
                        .user_name(caseCrime.getUser().getName())
                        .crime_id(caseCrime.getCrime().getId())
                        .crime_name(caseCrime.getCrime().getName())
                        .build()
        ).toList();

    }

    @Override
    @Transactional
    public CaseDtoResponse addCase(CaseDtoRequest caseDtoRequest) throws IllegalArgumentException {

        boolean userExist = userRepository.existsById(caseDtoRequest.getUser_id());
        boolean crimeExist = caseRepository.existsById(caseDtoRequest.getCrime_id());

        if (!userExist || !crimeExist) throw new IllegalArgumentException();

        Case caseCrime = new Case();

        caseCrime.setDescription(caseDtoRequest.getDescription());
        caseCrime.setDateCase(caseDtoRequest.getDateCase());
        caseCrime.setAltitude(caseDtoRequest.getAltitude());
        caseCrime.setLatitude(caseDtoRequest.getLatitude());
        caseCrime.setLongitude(caseDtoRequest.getLongitude());
        caseCrime.setCreatedAt(LocalDate.now());
        caseCrime.setUpdatedAt(LocalDate.now());
        caseCrime.setUser(userRepository.getReferenceById(caseDtoRequest.getUser_id()));
        caseCrime.setCrime(crimeRepository.getReferenceById(caseDtoRequest.getCrime_id()));
        caseCrime.setIsVisibility(true);
        caseCrime.setRmiUrl("");
        caseCrime.setUrlMap("");



        caseRepository.save(caseCrime);

        return CaseDtoResponse.builder()
                .id(caseCrime.getId())
                .description(caseCrime.getDescription())
                .dateCase(caseCrime.getDateCase())
                .altitude(caseCrime.getAltitude())
                .latitude(caseCrime.getLatitude())
                .longitude(caseCrime.getLongitude())
                .createdAt(caseCrime.getCreatedAt())
                .updatedAt(caseCrime.getUpdatedAt())
                .user_id(caseCrime.getUser().getId())
                .user_name(caseCrime.getUser().getName())
                .crime_id(caseCrime.getCrime().getId())
                .crime_name(caseCrime.getCrime().getName())
                .build();

    }
}
