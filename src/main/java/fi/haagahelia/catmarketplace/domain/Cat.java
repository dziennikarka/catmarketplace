package fi.haagahelia.catmarketplace.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cat {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long catId;
    private String name;
    private String description;
    private int age;
    private double price;

    public Cat() {
        this.name = null;
        this.description = null;
        this.age = 0;
        this.price = 0.0;
    }

    public Cat(String name, String description, int age, double price) {
        this.name = name;
        this.description = description;
        this.age = age;
        this.price = price;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAge() {
        return age;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + catId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", age=" + age +
                ", price=" + price +
                '}';
    }
}
