package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Zoo.deleteAllRows", query = "DELETE from Zoo ")
public class Zoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private String adress;

    @OneToMany (mappedBy = "zoo", cascade = CascadeType.PERSIST)
    private  List<Animal> animals;

    @ManyToOne
    private User users;

    public Zoo() {
    }

    public Zoo(Integer id, String name, String adress, List<Animal> animals) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.animals = animals;
    }

    public Zoo(String name, String adress) {
        this.name = name;
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }



    public void addAnimalToZoo (Animal animal) {
        this.animals.add(animal);
        if (animal != null) {
            animal.setZoo(this);
        }
    }

}
