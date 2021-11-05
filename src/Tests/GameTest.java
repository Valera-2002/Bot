import static org.junit.jupiter.api.Assertions.*;

import bot.Game;

import java.sql.ResultSet;
import java.sql.Statement;

class GameTest {

  @org.junit.jupiter.api.Test
  void isCorrect() {
    Game newGame = new Game();

    assertEquals(false, newGame.isCorrect("0", "7"));
    assertEquals(false, newGame.isCorrect("0", "mckemf"));
    assertEquals(true, newGame.isCorrect("0", "2"));
    assertEquals(true, newGame.isCorrect("1", "2"));
    assertEquals(true, newGame.isCorrect("2", "3"));
  }

  @org.junit.jupiter.api.Test
  void statistics() {
    Game newGame = new Game();
    assertEquals("\nВерных ответов:" + newGame.rightCount +
        "\nНеверных ответов:" + newGame.wrongCount, newGame.statistics());
    newGame.isCorrect("0", "2");
    assertEquals("\nВерных ответов:" + "1" +
        "\nНеверных ответов:" + "0", newGame.statistics());
    newGame.isCorrect("1", "2");
    assertEquals("\nВерных ответов:" + "2" +
        "\nНеверных ответов:" + "0", newGame.statistics());
    newGame.isCorrect("2", "123");
    assertEquals("\nВерных ответов:" + "2" +
        "\nНеверных ответов:" + "1", newGame.statistics());
  }
}