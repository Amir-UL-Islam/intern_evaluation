package org.spring.intro;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BlogDTO {
    @JsonProperty(namespace = "blog_title", required = true, defaultValue = "A Title")
    private String title;
    @JsonProperty(namespace = "blog_content", value = "It's about me.", required = true, defaultValue = "The Contents")
    private String content;
}
