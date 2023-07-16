package com.iudigital.myspringapp.service.interfaces;

import com.iudigital.myspringapp.dto.request.UserDtoRequest;
import com.iudigital.myspringapp.dto.response.UserDtoResponse;

import java.util.List;

public interface IUserService {

    List<UserDtoResponse> getAll();

    UserDtoResponse addUser(UserDtoRequest userDtoRequest);

    UserDtoResponse updateUser(Long id, UserDtoRequest userDtoRequest);

    String deleteUser(Long id);
}
