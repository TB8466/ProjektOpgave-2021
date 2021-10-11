package demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Region {

    @Id
    private Integer regionKode;
    private String regionNavn;
    private String regionHref;

    @OneToMany
    @JoinColumn(name = "region_kode")
    @JsonBackReference
    private Set<Kommune> kommuner = new HashSet<>();


    public Region() {
    }

    public Integer getRegionKode() {
        return regionKode;
    }

    public void setRegionKode(Integer regionKode) {
        this.regionKode = regionKode;
    }

    public String getRegionNavn() {
        return regionNavn;
    }

    public void setRegionNavn(String regionNavn) {
        this.regionNavn = regionNavn;
    }

    public String getRegionHref() {
        return regionHref;
    }

    public void setRegionHref(String regionHref) {
        this.regionHref = regionHref;
    }

    public Set<Kommune> getKommuner() {
        return kommuner;
    }

    public void setKommuner(Set<Kommune> kommuner) {
        this.kommuner = kommuner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(regionKode, region.regionKode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionKode);
    }

    @Override
    public String toString() {
        return "Region{" +
                "regionKode=" + regionKode +
                ", regionNavn='" + regionNavn + '\'' +
                ", regionHref='" + regionHref + '\'' +
                '}';
    }
}
