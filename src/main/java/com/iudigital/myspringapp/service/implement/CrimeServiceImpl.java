package com.iudigital.myspringapp.service.implement;

import com.iudigital.myspringapp.dto.request.CrimeDtoRequest;
import com.iudigital.myspringapp.dto.response.CrimeDtoResponse;
import com.iudigital.myspringapp.model.Crime;
import com.iudigital.myspringapp.model.User;
import com.iudigital.myspringapp.repository.CrimeRepository;
import com.iudigital.myspringapp.repository.UserRepository;
import com.iudigital.myspringapp.service.ConstantService;
import com.iudigital.myspringapp.service.interfaces.ICrimeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CrimeServiceImpl implements ICrimeService {

    final 
    CrimeRepository crimeRepository;
    final
    UserRepository userRepository;
    
    public CrimeServiceImpl(CrimeRepository crimeRepository, UserRepository userRepository){
        this.crimeRepository = crimeRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CrimeDtoResponse> getAll(){
        
        List<Crime> crimes = crimeRepository.findAll();

        return crimes.stream().map(crime ->
            CrimeDtoResponse.builder()
                    .id(crime.getId())
                    .name(crime.getName())
                    .description(crime.getDescription())
                    .userId(crime.getUser().getId())
                    .build()
        ).toList();
    }

    @Override
    public CrimeDtoResponse getCrimeById(Long id) {
        return null;
    }


    @Override
    @Transactional
    public CrimeDtoResponse addCrime(CrimeDtoRequest crimeDtoRequest){

        User user = userRepository.findById(crimeDtoRequest.getUserId()).orElseThrow(() -> new RuntimeException(ConstantService.NOT_FOUND));

            Crime crime = new Crime();

            crime.setName(crimeDtoRequest.getName());
            crime.setDescription(crimeDtoRequest.getDescription());
            crime.setCreatedAt(LocalDate.now());
            crime.setUpdatedAt(LocalDate.now());
            crime.setUser(user);

            crimeRepository.save(crime);

            return CrimeDtoResponse.builder()
                    .id(crime.getId())
                    .name(crime.getName())
                    .description(crime.getDescription())
                    .userId(crime.getUser().getId())
                    .build();
    }
    @Override
    public String deleteCrimeById(Long id) {
        return null;
    }

}
