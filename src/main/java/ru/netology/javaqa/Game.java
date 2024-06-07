package ru.netology.javaqa;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public List<Player> players = new ArrayList<>();


    public void register(Player player) {
        if (findByName(player.getName()) != null) {
            return;
        }
        players.add(player);
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);

        if (player1 == null && player2 == null) {
            throw new NotRegisteredException("Players with name " + playerName1 + " and " + playerName2 + " are not registred");
        }

        if (player1 == null) {
            throw new NotRegisteredException("player with name " + playerName1 + " is not registred");
        }
        if (player2 == null) {
            throw new NotRegisteredException("player with name " + playerName2 + " is not registred");
        }
        int strength1 = player1.getStrength();
        int strength2 = player2.getStrength();

        if (strength1 < strength2) {
            return 2;
        } else if (strength1 > strength2) {
            return 1;
        } else return 0;
    }

    public Player findByName(String name) {
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
}
