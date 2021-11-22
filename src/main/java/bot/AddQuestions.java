package bot;
import java.sql.*;

public class AddQuestions {
    public Boolean append = false;
    public String question = "";
    public String variantQuestions = "";
    public String answerQuestions = "";

    public String instruction(){
        return "В следующих 3 сообщениях(в каждом по отдельности) " +
                "Введите:" +
                "\n1.Сам вопрос" +
                "\n2.Пронумерованные варианты ответа(их 4)" +
                "\n3.Номер правильного ответа";
    }

    public String inputQuestion(String text) {
        question = text;
        return "Вопрос:" + question;
    }

    public String inputVariantQuestions(String text) {
        variantQuestions = text;
        return "Варианты:" + variantQuestions;
    }

    public String inputAnswerQuestions(String text) {
        answerQuestions = text;
        append = false;
        return AddQuestions();
    }

    public String AddQuestions(){
        Сonnection_to_bd based = new Сonnection_to_bd();
        String insert = "INSERT INTO questions (question, variant, answer) Values(\""+question+"\" , \""+variantQuestions+"\", "+answerQuestions+")";
        try {
            PreparedStatement statement = based.getConnection().prepareStatement(insert);
            statement.executeUpdate(insert);
            return "Добавлен вопрос:"+
                    "\n"+ question +
                    "\n"+ variantQuestions +
                    "\nНомер правильного ответ:"+ answerQuestions;
            }
        catch (SQLException e) {
             e.printStackTrace();
            return "Нет соеденения с базой";

        }
    }


}

