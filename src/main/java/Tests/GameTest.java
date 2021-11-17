package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import bot.Game;

class GameTest {
  @org.junit.jupiter.api.Test
  void startGame(){
    Game newGame = new Game();
    newGame.fillArray();
    assertEquals("Сколько лет прожил Жак Фреско?"+"\n"+"1. 1\n" +
            "2. 101\n" +
            "3. 78\n" +
            "4. 53",newGame.startGame());
  }

  @org.junit.jupiter.api.Test
  void isCorrect() {
    Game newGame = new Game();

    assertEquals(false, newGame.isCorrect("0", "7"));
    assertEquals(false, newGame.isCorrect("0", "mckemf"));
    assertEquals(true, newGame.isCorrect("2", "2"));
    assertEquals(true, newGame.isCorrect("3", "3"));

  }

  @org.junit.jupiter.api.Test
  void statistics() {
    Game newGame = new Game();
    assertEquals("\nВерных ответов:" + newGame.rightCount +
        "\nНеверных ответов:" + newGame.wrongCount, newGame.statistics());
    newGame.isCorrect("2", "2");
    assertEquals("\nВерных ответов:" + "1" +
        "\nНеверных ответов:" + "0", newGame.statistics());
    newGame.isCorrect("3", "3");
    assertEquals("\nВерных ответов:" + "2" +
        "\nНеверных ответов:" + "0", newGame.statistics());
    newGame.isCorrect("2", "123");
    assertEquals("\nВерных ответов:" + "2" +
        "\nНеверных ответов:" + "1", newGame.statistics());
  }
}