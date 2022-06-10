package io.aadesh.yogdaan.service;

import io.aadesh.yogdaan.entity.AppUser;
import io.aadesh.yogdaan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public AppUser getAppUser(String userId){
        Optional<AppUser> optional = userRepository.findById(userId);
        if(optional.isEmpty()){
            return null;
        }
        return optional.get();
    }

    public void saveUser(AppUser user){
        userRepository.save(user);
    }

}
