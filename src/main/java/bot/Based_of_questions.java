package bot;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;

public class Based_of_questions {

    public String[] questions;
    public String[] answers;

    public String[] fillArrayOfQuestions(){
        return questions;
    }
    public String[] fillArrayOfAnswer(){
        return answers;
    }

    public void fillArray() {
        Сonnection_to_bd based = new Сonnection_to_bd();
        try {
            String query = "select * from questions";
            Statement statement = based.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            questions = new String[6];
            answers = new String[6];
            int i = 0;
            while (resultSet.next()) {
                questions[i] = resultSet.getString(1) + "\n" + resultSet.getString(2);
                answers[i] = resultSet.getString(3);
                i += 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
