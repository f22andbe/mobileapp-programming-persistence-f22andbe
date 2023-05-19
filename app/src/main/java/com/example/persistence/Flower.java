package com.example.persistence;

public class Flower {

    private String name;
    private String species;
    private String genus;

    public Flower(String name, String species, String genus) {
        this.name = name;
        this.species = species;
        this.genus = genus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", genus='" + genus + '\'' +
                '}';
    }
}
