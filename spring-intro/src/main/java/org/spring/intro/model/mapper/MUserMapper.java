package org.spring.intro.model.mapper;

import org.spring.intro.model.dto.MUserDTO;
import org.spring.intro.model.entity.MUser;
import org.springframework.stereotype.Component;

@Component
public class MUserMapper {
    public MUser map(MUserDTO dto) {
        MUser user = new MUser();

        // is email valid
        // is this unique
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());

        //
        user.setPassword(dto.getUsername());
        user.setUsername(dto.getUsername());
        user.setPhoneNumber(dto.getPhoneNumber());
        return user;
    }

    public MUserDTO map(MUser entity) {
        MUserDTO dto = new MUserDTO();
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setUsername(entity.getUsername());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }
}
