package bot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Questions {

  public static String[] fillArray() {
    String[] questions = new String[3];
    questions[0] =
        "Сколько лет прожил Жак Фреско?"
            + "\n1. 1"
            + "\n2. 101"
            + "\n3. 78"
            + "\n4. 53";
    questions[1] = "Десятый элемент таблицы Менделеева"
        + "\n1. Озон"
        + "\n2. Неон"
        + "\n3. Хлор"
        + "\n4. Алюминий";
    questions[2] = "Столица Армении"
        + "\n1. Екатеринбург"
        + "\n2. Североуральск"
        + "\n3. Ереван"
        + "\n4. Гюмри";

    return questions;
  }

  public static Integer[] fillArrayOfInteger() {
    Integer[] answers = new Integer[3];
    answers[0] = 2;
    answers[1] = 2;
    answers[2] = 3;

    return answers;
  }
}