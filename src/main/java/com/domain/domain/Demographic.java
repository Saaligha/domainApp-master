package com.domain.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Demographic {

    @Id
    @Column(name = "race")
    private String race;

    @Column(name="gender")
    private String gender;

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Demographic{" +
                "race='" + race + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
