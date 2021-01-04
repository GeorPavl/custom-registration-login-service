package com.custom_login_example.service.user;

import com.custom_login_example.dto.UserDTO;
import com.custom_login_example.entity.User;
import com.custom_login_example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /****/

    @Override
    public UserDetails loadUserByUsername(String username) {
        return (UserDetails) userRepository.findByUsername(username);
    }

    @Override
    public User dtoToEntity(UserDTO source) {
        User result = new User();
        if (source.getId() != null){
            result.setId(source.getId());
        }
        if (source.getUsername() != null){
            result.setUsername(source.getUsername());
        }
        if (source.getPassword() != null){
            result.setPassword(source.getPassword());
        }
        if (source.getEnabled() != null){
            result.setEnabled(source.getEnabled());
        }
        return result;
    }

    @Override
    public UserDTO writeUserDTO(User source) {
        UserDTO result = new UserDTO();
        result.setId(source.getId());
        result.setUsername(source.getUsername());
        result.setPassword(source.getPassword());
        result.setEnabled(source.getEnabled());
        return result;
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new RuntimeException("Did not find user: " + username);
        }
        return writeUserDTO(user);
    }
}
