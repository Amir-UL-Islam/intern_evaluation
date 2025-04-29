package org.spring.intro.service;

import lombok.RequiredArgsConstructor;
import org.spring.intro.model.entity.MUser;
import org.spring.intro.repository.MUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MUserService {
    // Some Dependencies
    private final MUserRepository userRepository;

    public void save(MUser entity) {
        // Business Logics
        entity.setMGroup("A");
        // Save into Database
        userRepository.save(entity);
    }


    public MUser findById(Long id) {
        Optional<MUser> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public Optional<MUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<MUser> findAll() {
        return userRepository.findAll();
    }

    public void delete(MUser user) {
        userRepository.delete(user);
    }
}
