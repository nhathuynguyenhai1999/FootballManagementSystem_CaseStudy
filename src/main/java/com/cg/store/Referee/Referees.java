package com.cg.store.Referee;

public class Referees {
    private String name;
    private int age;
    private String nationality;
    public Referees(String name, int age, String nationality) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return name + "," + age + "," + nationality;
    }
}
