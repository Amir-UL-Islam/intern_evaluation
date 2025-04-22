package org.spring.intro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BlogDTO {
    @JsonProperty(namespace = "id")
    private Long id;

    @JsonProperty(namespace = "blog_title", required = true, defaultValue = "A Title")
    private String title;

    @JsonProperty(namespace = "blog_content", value = "It's about me.", required = true, defaultValue = "The Contents")
    private String content;

    @JsonProperty(namespace = "author_user_id")
    private Long authorUserId;

    @JsonProperty(namespace = "created_at")
    private Long createdAt;

    @JsonProperty(namespace = "updated_att")
    private Long updatedAt;

    @JsonProperty(namespace = "rating")
    private Double rating;

}
