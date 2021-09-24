package com.srum.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author ThoNN1
 */
public class MistakeDTO {
    private Long id;

    @NotBlank(message = "Mistake name is required")
    private String name;

    @NotBlank(message = "Mistake content is required")
    @Size(min = 10, message = "Content at least 10 characters")
    private String content;

    private String note;

    public MistakeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
