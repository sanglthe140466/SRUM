package com.srum.dto;

import java.util.Date;

/**
 * @author ThoNN1
 */
public class IssueDTO {
    private Long id;
    private String content;
    private Date date;

    public IssueDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
