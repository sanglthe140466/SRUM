package com.srum.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ThoNN1
 */
@Entity
@Table(name = "Trainer")
public class Trainer extends User {
    @OneToMany(mappedBy = "trainer")
    private Set<Subject> subjects = new HashSet<>();

    @OneToMany(mappedBy = "trainer")
    private Set<Clazz> classes = new HashSet<>();


    public Trainer() {
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Clazz> getClasses() {
        return classes;
    }

    public void setClasses(Set<Clazz> classes) {
        this.classes = classes;
    }

}
