package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import bot.AddQuestions;
import org.junit.jupiter.api.Test;

public class AddQuestionsTest {
    @Test
    void AddQuestions(){
        AddQuestions questions = new AddQuestions();
        questions.inputQuestion("?");
        questions.inputVariantQuestions("?");
        assertEquals("Нет соеденения с базой", questions.inputAnswerQuestions("?"));
        assertEquals("Нет соеденения с базой", questions.AddQuestions());

    }
}
