public class Game {
  Data_input reader = new Data_input();
  Response responser = new Response();
  int rightCount = 0;
  int wrongCount = 0;


  public void goGame() {
    String[] questions_ = Questions.fillArray();
    Integer[] answers_ = Questions.fillArrayOfInteger();

    for (int i = 0; i < 3; i++) {
      Response.onDisplay(questions_[i]);
      String usersAnswer = reader.read();

      if (usersAnswer.equals("0")) {
        statistics();
        return;
      }

      if (usersAnswer.equals(answers_[i] + "")) {
        Response.onDisplay("\u001B[32m" + "Верно" + "\u001B[0m");
        rightCount +=1;
      }
      else {
        Response.onDisplay("\u001B[31m" + "Неверно" + "\u001B[0m");
        wrongCount += 1;
      }
    }
    statistics();
  }

  public void statistics() {
    Response.onDisplay(
        "\nВерных ответов:" + rightCount +
            "\nНеверных ответов:" + wrongCount);
  }
}