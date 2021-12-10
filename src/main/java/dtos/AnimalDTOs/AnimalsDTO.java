package dtos.AnimalDTOs;

import entities.Animal;

import java.util.List;
import java.util.Objects;

public class AnimalsDTO {

    private List<AnimalDTO> animals;




    public int getSize() {
        int counter = 0;
        for (AnimalDTO a : animals) {
            counter++;
        }
        return counter;
    }

    public AnimalsDTO(List<Animal> animals) {
        this.animals = AnimalDTO.getFromList(animals);
    }

    public List<AnimalDTO> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalDTO> animals) {
        this.animals = animals;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalsDTO that = (AnimalsDTO) o;
        return Objects.equals(animals, that.animals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animals);
    }

    @Override
    public String toString() {
        return "AnimalsDTO{" +
                "animals=" + animals +
                '}';
    }
}
