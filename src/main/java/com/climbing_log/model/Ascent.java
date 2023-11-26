package com.climbing_log.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.climbing_log.enums.ClimbAttempt;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "ascent")
@EntityListeners(AuditingEntityListener.class)
public class Ascent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "climb_id")
    @JsonIgnore
    private Climb climb;
    private String comment;
    private ClimbAttempt attempts;
    private LocalDateTime date;
    @CreationTimestamp
    private LocalDateTime createdDate;
    private String grade;
    private Boolean over200lbs;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Climb getClimb() {
        return climb;
    }
    public void setClimb(Climb climb) {
        this.climb = climb;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public ClimbAttempt getAttempts() {
        return attempts;
    }
    public void setAttempts(ClimbAttempt attempts) {
        this.attempts = attempts;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public Boolean getOver200lbs() {
      return over200lbs;
    }
    public void setOver200lbs(Boolean over200lbs) {
      this.over200lbs = over200lbs;
    }
}
