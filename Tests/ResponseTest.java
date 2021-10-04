import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResponseTest {
  Response resp = new Response();

  @Test
  void response() {
  }

  @Test
  void getHelp() {
    assertEquals("Для получения справочной информации напиши " +
        "\nДля начала игры напиши команду \"go\"" +
        "\nДля завершения работы напиши \"stop\"", resp.getHelp());
  }

  @Test
  void sayHello() {
    assertEquals("Привет, я квиз-бот!" +
        "\nИгра состоит из 3 вопросов" +
        "\nЯ могу проводить квиз в одиночном и совместном режиме" +
        "\nДля начала игры напиши команду \"go\"" +
        "\nОтветить на вопрос можно написав номер ответа " +
        "\nЗакончить игру можно написав 0 " +
        "\nДля повторного получения справочной информации напиши \"help\" " +
        "\nДля завершения работы напиши \"stop\"", resp.sayHello());
  }
}