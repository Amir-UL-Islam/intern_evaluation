package org.spring.intro;

import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<MUserDTO> findById(Long id) {
        Optional<MUser> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(userMapper.map(user.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
