import static org.junit.jupiter.api.Assertions.*;

class GameTest {

  Game newGame = new Game();

  @org.junit.jupiter.api.Test
  void goGame() {
  }

  @org.junit.jupiter.api.Test
  void statistics() {
    assertEquals("\nВерных ответов:" + newGame.rightCount +
        "\nНеверных ответов:" + newGame.wrongCount, newGame.statistics());
  }
}