package ru.netology.javaqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
    private Game game;
    private final Player player1 = new Player(12, "Влад", 10);
    private final Player player2 = new Player(12, "Алекс", 5);

    @BeforeEach
    private void setUp() {
        game = new Game();
    }

    @Test
    public void registerTest() {
        game.register(player1);
        Assertions.assertEquals(player1, game.findByName("Влад"));
    }

    @Test
    public void roundOneTest() {
        game.register(player1);
        game.register(player2);
        Assertions.assertEquals(1, game.round(player1.getName(), player2.getName()));
    }

    @Test
    public void roundTwoTest() {
        game.register(player1);
        game.register(player2);
        Assertions.assertEquals(2, game.round(player2.getName(), player1.getName()));
    }

    @Test
    public void roundZeroTest() {
        Player player3 = new Player(12, "Саша", 5);
        game.register(player2);
        game.register(player3);
        Assertions.assertEquals(0, game.round(player2.getName(), player3.getName()));
    }

    @Test
    public void roundPlayerOneNotRegistredTest() {
        game.register(player2);
        NotRegisteredException e = Assertions.assertThrows(NotRegisteredException.class, () -> game.round(player1.getName(), player2.getName()));
        Assertions.assertEquals("player with name " + player1.getName() + " is not registred", e.getMessage());
    }

    @Test
    public void roundPlayerTwoNotRegistredTest() {
        game.register(player1);
        NotRegisteredException e = Assertions.assertThrows(NotRegisteredException.class, () -> game.round(player1.getName(), player2.getName()));
        Assertions.assertEquals("player with name " + player2.getName() + " is not registred", e.getMessage());
    }

    @Test
    public void roundPlayerBothNotRegistredTest() {
        NotRegisteredException e = Assertions.assertThrows(NotRegisteredException.class, () -> game.round(player1.getName(), player2.getName()));
        Assertions.assertEquals("Players with name " + player1.getName() + " and " + player2.getName() + " are not registred", e.getMessage());
    }

    @Test
    public void registeredTest() {
        Player player4 = new Player(11, "Алекс", 5);
        game.register(player1);
        game.register(player2);
        game.register(player4);
        Assertions.assertEquals(player2, game.findByName(player4.getName()));
    }
}
