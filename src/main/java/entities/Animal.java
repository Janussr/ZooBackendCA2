package entities;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Animal.deleteAllRows", query = "DELETE from Animal ")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String race;
    private int age;

    @ManyToOne
    private Zoo zoo;

    public Animal() {
    }

    public Animal(Integer id, String race, int age) {
        this.id = id;
        this.race = race;
        this.age = age;
    }

    public Animal(String race, int age, Zoo zoo) {
        this.race = race;
        this.age = age;
        this.zoo = zoo;
    }

    public Animal(String race, int age) {
        this.race = race;
        this.age = age;
    }

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
