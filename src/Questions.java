import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Questions {
  public static String[] fillArray(){
    String[] questions = new String[3];
    questions[0] =
        "Сколько лет Жаку Фреско?"
            + "\n1. 1"
            + "\n2. 101"
            + "\n3. 78"
            + "\n4. он жив?";
    questions[1] = "How do you do?"
        + "\n1. 23"
        + "\n2. 6542"
        + "\n3. 888"
        + "\n4. 666";
    questions[2] = "Столица Армении"
        + "\n1. Екатеринбург"
        + "\n2. Североуральск"
        + "\n3. Ереван"
        + "\n4. ГородБесов";

    return questions;
  }

  public static Integer[] fillArrayOfInteger(){
    Integer[] answers = new Integer[3];
    answers[0] = 2;
    answers[1] = 2;
    answers[2] = 3;

    return answers;
  }
}