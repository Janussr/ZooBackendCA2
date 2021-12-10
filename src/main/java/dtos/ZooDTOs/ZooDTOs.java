package dtos.ZooDTOs;

import dtos.AnimalDTOs.AnimalDTO;
import entities.Zoo;

import java.util.List;
import java.util.Objects;

public class ZooDTOs {

    private List<ZooDTO> zoos;


    public int getSize() {
        int counter = 0;
        for (ZooDTO a : zoos) {
            counter++;
        }
        return counter;
    }

    public ZooDTOs(List<Zoo> zoos) {
        this.zoos = ZooDTO.getFromList(zoos);
    }


    public List<ZooDTO> getZoos() {
        return zoos;
    }

    public void setZoos(List<ZooDTO> zoos) {
        this.zoos = zoos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZooDTOs zooDTOs = (ZooDTOs) o;
        return Objects.equals(zoos, zooDTOs.zoos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zoos);
    }

    @Override
    public String toString() {
        return "ZooDTOs{" +
                "zoos=" + zoos +
                '}';
    }
}
