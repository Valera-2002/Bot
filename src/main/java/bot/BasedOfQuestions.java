package bot;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BasedOfQuestions {

    public String[] questions;
    public String[] answers;
    public int countOfQuestions;

    public String[] getArrayOfQuestions(){return questions;}
    public String[] getArrayOfAnswer(){return answers;}
    public Integer getCountOfQuestions(){return countOfQuestions;}

    public void fillArray() {
        ConnectionToBd based = new ConnectionToBd();
        try {
            Statement statement = based.getConnection().createStatement();

            String count = "SELECT COUNT(*) FROM questions";
            ResultSet counter = statement.executeQuery(count);
            counter.next();
            countOfQuestions = counter.getInt(1);

            String query = "select * from questions";
            ResultSet resultSet = statement.executeQuery(query);

            questions = new String[countOfQuestions];
            answers = new String[countOfQuestions];
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
