package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Kommune {

    @Id
    private Integer kommuneKode;
    private String kommuneNavn;

    public Kommune() {
    }

    public Integer getKommuneKode() {
        return kommuneKode;
    }

    public void setKommuneKode(Integer kommuneKode) {
        this.kommuneKode = kommuneKode;
    }

    public String getKommuneNavn() {
        return kommuneNavn;
    }

    public void setKommuneNavn(String kommuneNavn) {
        this.kommuneNavn = kommuneNavn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kommune kommune = (Kommune) o;
        return Objects.equals(kommuneKode, kommune.kommuneKode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kommuneKode);
    }

    @Override
    public String toString() {
        return "Kommune{" +
                "kommuneKode=" + kommuneKode +
                ", kommuneNavn='" + kommuneNavn + '\'' +
                '}';
    }
}
