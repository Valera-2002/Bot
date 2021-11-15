package bot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Game {
  Data_input reader = new Data_input();
  BasedQuestions questions = new BasedQuestions();

  public int rightCount = 0;
  public int wrongCount = 0;

  public String nextQuestion(Update update){

  }
  public void goGame(Bot bot){
    try {
      String query = "select * from questions";
      Statement statement = questions.getConnection().createStatement();
      ResultSet resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        Response.onDisplay(resultSet.getString(1));
        Response.onDisplay(resultSet.getString(2));
        String usersAnswer = reader.read();

        if (isCorrect(resultSet.getString(3), usersAnswer)) {
          Response.onDisplay("\u001B[32m" + "Верно" + "\u001B[0m");
        }
        else {
          Response.onDisplay("\u001B[31m" + "Неверно" + "\u001B[0m");
          }
        }
        Response.onDisplay(statistics());


    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Boolean isCorrect(String answer, String usersAnswer){
    if (usersAnswer.equals(answer)){
      rightCount += 1;
      return true;
    } else{
      wrongCount += 1;
      return false;
    }
  }

  public String statistics() {
    return "\nВерных ответов:" + rightCount +
        "\nНеверных ответов:" + wrongCount;
  }
}