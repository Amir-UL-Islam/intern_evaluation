package org.spring.intro;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_comments")
@Data
public class BlogComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private MUser viewer;

    @Column(nullable = false)
    private String content;

}
