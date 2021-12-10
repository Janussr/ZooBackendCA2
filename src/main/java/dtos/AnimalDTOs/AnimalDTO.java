package dtos.AnimalDTOs;

import entities.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AnimalDTO {
    private int id;
    private String race;
    private int age;

    public static List<AnimalDTO> getFromList(List<Animal> animals) {
        return animals.stream()
                .map(animal -> new AnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public static List<AnimalDTO> getDtos(List<Animal> animals) {
        List<AnimalDTO> animalDTOS = new ArrayList();
        animals.forEach(rm -> animalDTOS.add(new AnimalDTO(rm)));
        return animalDTOS;
    }

    public AnimalDTO(Animal animal) {
        this.id = animal.getId();
        this.race = animal.getRace();
        this.age = animal.getAge();
    }



    public AnimalDTO(String race, int age) {
        this.race = race;
        this.age = age;
    }

    public AnimalDTO() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalDTO animalDTO = (AnimalDTO) o;
        return id == animalDTO.id && age == animalDTO.age && Objects.equals(race, animalDTO.race);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, race, age);
    }

    @Override
    public String toString() {
        return "AnimalDTO{" +
                "id=" + id +
                ", race='" + race + '\'' +
                ", age=" + age +
                '}';
    }
}
