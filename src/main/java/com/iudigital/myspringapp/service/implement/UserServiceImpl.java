package com.iudigital.myspringapp.service.implement;

import com.iudigital.myspringapp.dto.request.UserDtoRequest;
import com.iudigital.myspringapp.dto.response.UserDtoResponse;
import com.iudigital.myspringapp.model.User;
import com.iudigital.myspringapp.repository.RoleRepository;
import com.iudigital.myspringapp.repository.UserRepository;
import com.iudigital.myspringapp.service.interfaces.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    final
    RoleRepository roleRepository;

    final
    UserRepository userRepository;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDtoResponse> getAll() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) throw new NullPointerException();

        return users.stream().map(user ->
                UserDtoResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .userName(user.getUserName())
                        .email(user.getEmail())
                        .enabled(user.getEnabled())
                        .roles(user.getRoles())
                        .image(user.getImage())
                        .dateBirth(user.getDateBirth())
                        .createdAt(user.getCreatedAt())
                        .updatedAt(user.getUpdatedAt())
                        .build()
        ).toList();

    }

    @Override
    @Transactional
    public UserDtoResponse addUser(UserDtoRequest userDtoRequest){

            boolean userExist =  userRepository.existsByUserName(userDtoRequest.getUserName());
            boolean emailExist = userRepository.existsByEmail(userDtoRequest.getEmail());

            if(userExist || emailExist){

                throw new IllegalArgumentException();
            }

            User user = new User();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(userDtoRequest.getPassword());

            user.setName(userDtoRequest.getName());
            user.setUserName(userDtoRequest.getUserName());
            user.setEmail(userDtoRequest.getEmail());
            user.setPassword(hashedPassword);
            user.setRoles(roleRepository.findByRol("ROLE_USER"));
            user.setImage(userDtoRequest.getImage());
            user.setDateBirth(userDtoRequest.getDateBirth());
            user.setEnabled(true);
            user.setCreatedAt(LocalDate.now());
            user.setUpdatedAt(LocalDate.now());

            user = userRepository.save(user);

            return UserDtoResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .userName(user.getUserName())
                    .email(user.getEmail())
                    .image(user.getImage())
                    .dateBirth(user.getDateBirth())
                    .enabled(user.getEnabled())
                    .roles(user.getRoles())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(user.getUpdatedAt())
                    .build();

    }

    @Override
    @Transactional
    public UserDtoResponse updateUser(Long id, UserDtoRequest userDtoRequest) {

        User userExist = userRepository.findById(id).orElse(null);

        if(userExist == null){
            throw new NullPointerException();
        }

        if(!userExist.getUserName().equals(userDtoRequest.getUserName())){
            boolean existUserName = userRepository.existsByUserName(userDtoRequest.getUserName());
            if(existUserName){
                throw new IllegalArgumentException();
            }

            userExist.setUserName(userDtoRequest.getUserName());
        }

        if(!userExist.getEmail().equals(userDtoRequest.getEmail())){

           boolean existsByEmail = userRepository.existsByEmail(userDtoRequest.getEmail());

            if(existsByEmail){
                throw new IllegalArgumentException();
            }
            userExist.setEmail(userDtoRequest.getEmail());
        }

        if (!userDtoRequest.getPassword().isEmpty()) {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(userDtoRequest.getPassword());
            userExist.setPassword(hashedPassword);
        }

        if(userDtoRequest.getEnabled() != null){
            userExist.setEnabled(userDtoRequest.getEnabled());
        }

        if (userDtoRequest.getName() != null) {
            userExist.setName(userDtoRequest.getName());
        }

        if (userDtoRequest.getImage() != null) {
            userExist.setImage(userDtoRequest.getImage());
        }

        if (userDtoRequest.getDateBirth() != null) {
            userExist.setDateBirth(userDtoRequest.getDateBirth());
        }

        userExist.setUpdatedAt(LocalDate.now());

        userExist.setRoles(userDtoRequest.getRoles());

        return UserDtoResponse.builder()
                .id(userExist.getId())
                .name(userExist.getName())
                .userName(userExist.getUserName())
                .email(userExist.getEmail())
                .enabled(userExist.getEnabled())
                .dateBirth(userExist.getDateBirth())
                .image(userExist.getImage())
                .roles(userExist.getRoles())
                .createdAt(userExist.getCreatedAt())
                .updatedAt(userExist.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteUser(Long id) {

            User userExist = userRepository.findById(id).orElse(null);

            if(userExist == null){
                throw new NullPointerException();
            }

            userRepository.deleteById(id);

    }
}
