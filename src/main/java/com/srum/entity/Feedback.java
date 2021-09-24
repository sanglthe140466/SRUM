package com.srum.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ThoNN1
 */
@Entity
@Table(name = "Feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "consult_date", columnDefinition = "date", nullable = false)
    private Date consultDate;

    @Column(name = "content", length = 500, nullable = false)
    private String content;

    @Column(name = "url", length = 500, nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id", nullable = false)
    private Clazz clazz;

    public Feedback() {
    }

    public Feedback(String name, Date consultDate, String content, String url, Clazz clazz) {
        this.name = name;
        this.consultDate = consultDate;
        this.content = content;
        this.url = url;
        this.clazz = clazz;
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

    public Date getConsultDate() {
        return consultDate;
    }

    public void setConsultDate(Date consultDate) {
        this.consultDate = consultDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
