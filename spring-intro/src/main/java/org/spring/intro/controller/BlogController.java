package org.spring.intro.controller;

import lombok.RequiredArgsConstructor;
import org.spring.intro.model.mapper.BlogMapper;
import org.spring.intro.service.BlogService;
import org.spring.intro.exception.IlligaleException;
import org.spring.intro.exception.NotFountException;
import org.spring.intro.model.entity.Blog;
import org.spring.intro.model.dto.BlogDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    private final BlogMapper blogMapper;

    @PostMapping("/create")
    public ResponseEntity<BlogDTO> create(@RequestBody BlogDTO dto) throws NotFountException, IlligaleException {

        Blog entity = blogMapper.map(dto);
        entity = blogService.save(entity);
        return ResponseEntity.ok(blogMapper.map(entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(blogMapper.map(blogService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        blogService.delete(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BlogDTO> update(
            @PathVariable("id") Long id,
            @RequestBody BlogDTO dto
    ) throws NotFountException, IlligaleException {
        Blog exEntity = blogService.findById(id);

        if (exEntity == null) {
            return ResponseEntity.notFound().build();
        } else {
            Blog entity = blogMapper.map(dto);
            entity = blogService.save(entity);
            return ResponseEntity.ok(blogMapper.map(entity));
        }
    }
}

