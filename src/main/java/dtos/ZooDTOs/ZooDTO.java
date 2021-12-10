package dtos.ZooDTOs;

import dtos.AnimalDTOs.AnimalDTO;
import entities.Animal;
import entities.Zoo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ZooDTO {
    private int id;
    private String adress;
    private String name;
    private List<AnimalDTO> animals;



    public static List<ZooDTO> getFromList(List<Zoo> zoos) {
        return zoos.stream()
                .map(zoo -> new ZooDTO(zoo))
                .collect(Collectors.toList());
    }

    public ZooDTO(Zoo zoo){
        this.id = zoo.getId();
        this.adress = zoo.getAdress();
        this.name = zoo.getName();
        this.animals = AnimalDTO.getFromList(zoo.getAnimals());
    }

    public ZooDTO(int id, String adress, String name, List<AnimalDTO> animals) {
        this.id = id;
        this.adress = adress;
        this.name = name;
        this.animals = animals;
    }

    public ZooDTO(String name, String adress) {
        this.name = name;
        this.adress = adress;
    }

    public ZooDTO() {
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        ZooDTO zooDTO = (ZooDTO) o;
        return id == zooDTO.id && Objects.equals(adress, zooDTO.adress) && Objects.equals(name, zooDTO.name) && Objects.equals(animals, zooDTO.animals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adress, name, animals);
    }

    @Override
    public String toString() {
        return "ZooDTO{" +
                "id=" + id +
                ", adress='" + adress + '\'' +
                ", name='" + name + '\'' +
                ", animals=" + animals +
                '}';
    }
}


