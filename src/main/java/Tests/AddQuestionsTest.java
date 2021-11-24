package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import bot.AddQuestions;
import org.junit.jupiter.api.Test;

public class AddQuestionsTest {
    @Test
    void AddQuestions() {
        AddQuestions questions = new AddQuestions();
        questions.inputQuestion("?");
        questions.inputVariantQuestions("?");
        assertEquals("Не получилось добавить вопрос: либо такой вопрос уже есть, либо вопрос введен некорректно", questions.inputAnswerQuestions("?"));
        assertEquals("Не получилось добавить вопрос: либо такой вопрос уже есть, либо вопрос введен некорректно", questions.AddQuestions());
    }
    @Test
    void DeleteQuestions() {
        AddQuestions questions1 = new AddQuestions();
        questions1.question ="example?";
        questions1.variantQuestions ="1.1";
        questions1.answerQuestions ="3";
        assertEquals(questions1.AddQuestions(), "Добавлен вопрос");
        assertEquals(questions1.DeleteQuestions(),"Вопрос удален");
        }

    }

