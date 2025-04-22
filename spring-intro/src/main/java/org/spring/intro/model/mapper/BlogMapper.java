package org.spring.intro.model.mapper;

import lombok.RequiredArgsConstructor;
import org.spring.intro.exception.IlligaleException;
import org.spring.intro.model.entity.MUser;
import org.spring.intro.service.MUserService;
import org.spring.intro.exception.NotFountException;
import org.spring.intro.model.entity.Blog;
import org.spring.intro.model.dto.BlogDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class BlogMapper {
    private final MUserService userService;

    public Blog map(BlogDTO dto) throws NotFountException, IlligaleException {
        Blog entity = new Blog();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        MUser user = userService.findById(dto.getAuthorUserId());
        if (user != null) {
            entity.setAuthor(user);
        } else {
            throw new NotFountException("User  not  found by id: " + dto.getAuthorUserId());
        }

        if (dto.getCreatedAt() != null) {
            throw new IlligaleException("You are not allowed to modify this");
        } else {
            entity.setCreatedAt(new Date());
        }

        if (dto.getUpdatedAt() != null) {
            throw new IlligaleException("You are not allowed to modify this");
        } else {
            entity.setUpdatedAt(new Date());
        }

        return entity;


    }


    public BlogDTO map(Blog entity) {
        BlogDTO dto = new BlogDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setAuthorUserId(entity.getAuthor().getId());
        dto.setCreatedAt(entity.getCreatedAt().getTime());
        dto.setUpdatedAt(entity.getUpdatedAt().getTime());
        return dto;


    }
}
