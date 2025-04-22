package org.spring.intro.service;

import org.spring.intro.model.dto.MUserDTO;
import org.spring.intro.model.entity.MUser;
import org.spring.intro.model.mapper.MUserMapper;
import org.spring.intro.repository.MUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MUserService {
    // Some Dependencies
    private final MUserMapper userMapper;
    private final MUserRepository userRepository;

    public MUserService(MUserMapper userMapper, MUserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public void save(MUserDTO dto) {
        MUser entity = userMapper.map(dto);

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
}
