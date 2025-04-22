package org.spring.intro.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<MUser> users;

    private String roleName;

    private String description;

}
