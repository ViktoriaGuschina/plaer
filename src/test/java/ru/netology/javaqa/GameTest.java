package ru.netology.javaqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
    private Game game;

    @BeforeEach
    private void setUp() {
        game = new Game();
    }

    @Test
    public void registerTest() {
        Player player1 = new Player(12, "Влад", 10);
        game.register(player1);
        Assertions.assertEquals(player1, game.findByName("Влад"));
    }

    @Test
    public void roundOneTest() {
        Player player1 = new Player(12, "Влад", 10);
        Player player2 = new Player(12, "Алекс", 5);
        game.register(player1);
        game.register(player2);
        Assertions.assertEquals(1, game.round(player1.getName(), player2.getName()));
    }

    @Test
    public void roundTwoTest() {
        Player player1 = new Player(12, "Влад", 10);
        Player player2 = new Player(12, "Алекс", 5);
        game.register(player1);
        game.register(player2);
        Assertions.assertEquals(2, game.round(player2.getName(), player1.getName()));
    }

    @Test
    public void roundZeroTest() {
        Player player1 = new Player(12, "Саша", 10);
        Player player2 = new Player(12, "Алекс", 10);
        game.register(player1);
        game.register(player2);
        Assertions.assertEquals(0, game.round(player2.getName(), player1.getName()));
    }

    @Test
    public void roundPlayerOneNotRegistredTest() {
        Player player1 = new Player(12, "Влад", 10);
        Player player2 = new Player(12, "Алекс", 5);

        game.register(player2);
        NotRegisteredException e = Assertions.assertThrows(NotRegisteredException.class, () -> game.round(player1.getName(), player2.getName()));
        Assertions.assertEquals("player with name " + player1.getName() + " is not registred", e.getMessage());
    }

    @Test
    public void roundPlayerTwoNotRegistredTest() {
        Player player1 = new Player(12, "Влад", 10);
        Player player2 = new Player(12, "Алекс", 5);

        game.register(player1);
        NotRegisteredException e = Assertions.assertThrows(NotRegisteredException.class, () -> game.round(player1.getName(), player2.getName()));
        Assertions.assertEquals("player with name " + player2.getName() + " is not registred", e.getMessage());
    }

    @Test
    public void roundPlayerBothNotRegistredTest() {
        Player player1 = new Player(12, "Влад", 10);
        Player player2 = new Player(12, "Алекс", 5);

        NotRegisteredException e = Assertions.assertThrows(NotRegisteredException.class, () -> game.round(player1.getName(), player2.getName()));
        Assertions.assertEquals("Players with name " + player1.getName() + " and " + player2.getName() + " are not registred", e.getMessage());
    }
}
