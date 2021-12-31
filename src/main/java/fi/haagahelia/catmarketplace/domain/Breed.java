package fi.haagahelia.catmarketplace.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Breed {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long breedId;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "breed")
    private List<Cat> cats;

    public Breed() {
    }

    public Breed(String name) {
        this.name = name;
    }

    public Long getBreedId() {
        return breedId;
    }

    public void setBreedId(Long breedId) {
        this.breedId = breedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    @Override
    public String toString() {
        return "Breed{" +
                "breedId=" + breedId +
                ", name='" + name + '\'' +
                '}';
    }
}
