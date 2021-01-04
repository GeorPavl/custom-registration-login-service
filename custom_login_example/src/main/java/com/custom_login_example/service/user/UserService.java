package com.custom_login_example.service.user;

import com.custom_login_example.dto.UserDTO;
import com.custom_login_example.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails loadUserByUsername(String username);

    User dtoToEntity(UserDTO source);

    UserDTO writeUserDTO(User source);

    UserDTO findUserByUsername(String username);
}
