package bot;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONObject;


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

    public void fillArrayFromAPI(){
        try {
            countOfQuestions = 6;
            String url = "https://opentdb.com/api.php?amount=6&type=multiple";
            URL object = new URL(url);
            HttpURLConnection con = (HttpURLConnection) object.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String input;
            StringBuffer response = new StringBuffer();
            while ((input = in.readLine()) != null){
                response.append(input);
            }
            in.close();
            JSONObject myresp = new JSONObject(response.toString());
            JSONArray questionsJSONArray = (JSONArray) myresp.get("results");
            questions = new String[countOfQuestions];
            answers = new String[countOfQuestions];
            int index = 0;
            for (Object item: questionsJSONArray) {
                JSONObject questionJsonObj = (JSONObject) item;
                String question = (String) questionJsonObj.get("question") +
                    "\n1. "  + (String) questionJsonObj.get("correct_answer");
                JSONArray answersJSONArray = questionJsonObj.getJSONArray("incorrect_answers");
                for (int i = 0; i < 3; i++){
                    question += "\n" + (i+2) + ". " + answersJSONArray.get(i).toString();
                }
                questions[index] = question;
                answers[index] = "1";
                index++;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
