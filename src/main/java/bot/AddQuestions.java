package bot;
import java.sql.*;

public class AddQuestions {
    public Boolean append;
    public String question;
    public String variantQuestions;
    public String answerQuestions;

    public String getQuestion(){return question;}
    public String getVariantQuestions(){return variantQuestions;}
    public String getAnswerQuestions(){return answerQuestions;}
    public Boolean getAppend(){return append;}

    public String instruction(){
        append = true;
        question = "";
        variantQuestions = "";
        answerQuestions = "";
        return """
                В следующих 3 сообщениях(в каждом по отдельности) Введите:
                1.Сам вопрос
                2.Пронумерованные варианты ответа(их 4)
                3.Номер правильного ответа""";
    }

    public String inputQuestion(String text) {
        question = text;
        return "Вопрос:" + question;
    }

    public String inputVariantQuestion(String text) {
        variantQuestions = text;
        return "Варианты:" + variantQuestions;
    }

    public String inputAnswerQuestion(String text) {
        answerQuestions = text;
        append = false;
        return addQuestion();
    }

    public String addQuestion(){
        ConnectionToBd connection = new ConnectionToBd();
        String insert = "INSERT INTO questions (question, variant, answer) Values(\""+question+"\" , \""+variantQuestions+"\", "+answerQuestions+")";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(insert);
            statement.executeUpdate(insert);
            return "Добавлен вопрос";
            }
        catch (SQLException e) {
             e.printStackTrace();
            return "Не получилось добавить вопрос: либо такой вопрос уже есть, либо вопрос введен некорректно"; }

        }
    public String deleteQuestions(){
        ConnectionToBd connection = new ConnectionToBd();
        String insert = "DELETE FROM questions WHERE question=\""+question+"\"";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(insert);
            statement.executeUpdate(insert);
            return "Вопрос удален";
        }
        catch (SQLException e) {
            e.printStackTrace();
            return "Не получилось удалить вопрос"; }

    }
}



