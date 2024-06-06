package ru.netology.javaqa;

import java.util.HashMap;
import java.util.Map;

public class Game {
    public Map<String, Player> players = new HashMap<>();

    public void register(Player player) {
        players.put(player.getName(), player);
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = players.get(playerName1);
        Player player2 = players.get(playerName2);

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
        return players.get(name);
    }
}
