package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import bot.Game;

class GameTest {

  @org.junit.jupiter.api.Test
  void startGame(){
    Game newGame = new Game();
    newGame.fillArray();
    assertEquals("""
            Игра началась!
            Сколько лет прожил Жак Фреско?
            1. 1
            2. 101
            3. 78
            4. 53""",newGame.startGame());
  }

  @org.junit.jupiter.api.Test
  void isCorrect() {
    Game newGame = new Game();

    assertEquals(false, newGame.isCorrect("0", "7"));
    assertEquals(false, newGame.isCorrect("0", "sdfa"));
    assertEquals(true, newGame.isCorrect("2", "2"));
    assertEquals(true, newGame.isCorrect("3", "3"));

  }

  @org.junit.jupiter.api.Test
  void statistics() {
    Game newGame = new Game();
    assertEquals("\nВерных ответов:" + newGame.rightCount +
        "\nНеверных ответов:" + newGame.wrongCount +
                    "\nОбщее время: "+(newGame.time/1000)+ " c", newGame.statistics());
    newGame.isCorrect("2", "2");
    assertEquals("""

            Верных ответов:1
            Неверных ответов:0"""+
            "\nОбщее время: "+(newGame.time/1000)+ " c", newGame.statistics());
    newGame.isCorrect("3", "3");
    assertEquals("""

            Верных ответов:2
            Неверных ответов:0"""+
            "\nОбщее время: "+(newGame.time/1000)+ " c", newGame.statistics());
    newGame.isCorrect("2", "123");
    assertEquals("""

            Верных ответов:2
            Неверных ответов:1"""+
            "\nОбщее время: "+(newGame.time/1000)+ " c", newGame.statistics());
  }
}