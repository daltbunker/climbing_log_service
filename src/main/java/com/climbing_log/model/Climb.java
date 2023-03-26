package com.climbing_log.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.climbing_log.enums.ClimbType;
import com.climbing_log.enums.Grade;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "climb")
@EntityListeners(AuditingEntityListener.class)
public class Climb {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private ClimbType type;
    private Grade grade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "area_id")
    @JsonIgnore
    private Area area;

    @OneToMany(mappedBy = "climb") // "climb" refers to property name in Ascent Class
    @JsonIgnore
    private Set<Ascent> ascents;

    @CreationTimestamp
    private LocalDateTime createdDate;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<Ascent> getAscents() {
        return ascents;
    }
    public void setAscents(Set<Ascent> ascents) {
        this.ascents = ascents;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public ClimbType getType() {
        return type;
    }
    public void setType(ClimbType type) {
        this.type = type;
    }
    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    public Area getArea() {
        return area;
    }
    public void setArea(Area area) {
        this.area = area;
    }
}
