package com.cg.model;

import java.time.LocalDate;

public class Tournament {
    private int id;
    private String name;
    private LocalDate startAt;
    private LocalDate endAt;

    public Tournament(int id, String name, LocalDate startAt, LocalDate endAt) {
        this.id = id;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Tournament(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDate startAt) {
        this.startAt = startAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDate endAt) {
        this.endAt = endAt;
    }
}
