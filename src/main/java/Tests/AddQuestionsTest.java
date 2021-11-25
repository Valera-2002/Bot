package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import bot.AddQuestions;
import bot.BasedOfQuestions;
import org.junit.jupiter.api.Test;

public class AddQuestionsTest {
    @Test
    void addQuestions() {
        AddQuestions questions = new AddQuestions();
        questions.inputQuestion("?");
        questions.inputVariantQuestion("?");
        assertEquals("Не получилось добавить вопрос: либо такой вопрос уже есть, либо вопрос введен некорректно", questions.inputAnswerQuestion("?"));
        assertEquals("Не получилось добавить вопрос: либо такой вопрос уже есть, либо вопрос введен некорректно", questions.addQuestion());
    }
    @Test
    void deleteQuestions() {
        AddQuestions questions = new AddQuestions();
        BasedOfQuestions based = new BasedOfQuestions();
        based.fillArray();
        int countBefore = based.getCountOfQuestions();

        questions.inputQuestion("example?");
        questions.inputVariantQuestion("1.1");
        assertEquals(questions.inputAnswerQuestion("3"), "Добавлен вопрос");
        based.fillArray();
        assertEquals(countBefore+1,based.getCountOfQuestions());

        assertEquals(questions.deleteQuestions(),"Вопрос удален");
        based.fillArray();
        assertEquals(countBefore,based.getCountOfQuestions());
        }

    }

