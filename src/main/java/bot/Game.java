package bot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Game {
  BasedQuestions questions = new BasedQuestions();

  public int rightCount = 0;
  public int wrongCount = 0;

  public String goGame(String text){
    try {
      String query = "select * from questions";
      Statement statement = questions.getConnection().createStatement();
      ResultSet resultSet = statement.executeQuery(query);

      String result = "";

      if (text.equals("go")){
        return (resultSet.getString(1) + "\n" + resultSet.getString(2));
      }

      if (isCorrect(resultSet.getString(3), text)) {
        result += ("\u001B[32m" + "Верно" + "\u001B[0m");
      }
      else {
        result += ("\u001B[31m" + "Неверно" + "\u001B[0m");
      }
      if (resultSet.next()) {
        result += (resultSet.getString(1) + "\n" + resultSet.getString(2));
      }
      else {
        result += statistics();
      }
      return result;
    } catch (SQLException e) {
      e.printStackTrace();
      return "0";
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