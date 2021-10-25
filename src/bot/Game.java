package bot;

public class Game {

  Data_input reader = new Data_input();
  public int rightCount = 0;
  public int wrongCount = 0;
  public String[] questions;
  public Integer[] answers;


  public void fillArrayOfQuestions(){
    questions = Questions.fillArray();
    answers = Questions.fillArrayOfInteger();
  }

  public void goGame() {
    fillArrayOfQuestions();
    for (int i = 0; i < 3; i++) {
      Response.onDisplay(questions[i]);
      String usersAnswer = reader.read();

      if (usersAnswer.equals("0")) {
        Response.onDisplay(statistics());
        return;
      }

      if (isCorrect(i, usersAnswer)) {
        Response.onDisplay("\u001B[32m" + "Верно" + "\u001B[0m");
      } else {
        Response.onDisplay("\u001B[31m" + "Неверно" + "\u001B[0m");
      }
    }
    Response.onDisplay(statistics());
  }

  public Boolean isCorrect(Integer numberOfQuestion, String usersAnswer){
    if (usersAnswer.equals(answers[numberOfQuestion] + "")){
      rightCount += 1;
      return true;
    } else{
      wrongCount += 1;
      return false;
    }
  }

  public String[] getQuestion(Integer number){
    return new String[] {questions[number], Integer.toString(answers[number])};
  }

  public String statistics() {
    return "\nВерных ответов:" + rightCount +
        "\nНеверных ответов:" + wrongCount;
  }
}