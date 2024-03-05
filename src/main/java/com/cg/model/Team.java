package com.cg.model;

import java.util.List;

public class Team {
    private String id;
    private String name;
    private Player captain;
    private List<Player> players;

    public Team(String id, String name, Player captain) {
        this.id = id;
        this.name = name;
        this.captain = captain;
        this.players = players;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getCaptain() {
        return captain;
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}

