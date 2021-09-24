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
@Table(name = "ClassAdmin")
public class ClassAdmin extends User {

    public ClassAdmin() {
    }
}