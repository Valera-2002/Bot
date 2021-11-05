import static org.junit.jupiter.api.Assertions.*;
import bot.Response;
import org.junit.jupiter.api.Test;

class ResponseTest {
  Response resp = new Response();

  @Test
  void response() {
  }

  @Test
  void getHelp() {
    assertEquals("""
        Для получения справочной информации напиши\s
        Для начала игры напиши команду "go"
        Для завершения работы напиши "stop\"""", resp.getHelp());
  }

  @Test
  void sayHello() {
    assertEquals("""
        Привет, я квиз-бот!
        Игра состоит из 3 вопросов
        Я могу проводить квиз в одиночном и совместном режиме
        Для начала игры напиши команду "go"
        Ответить на вопрос можно написав номер ответа\s
        Закончить игру можно написав 0\s
        Для повторного получения справочной информации напиши "help"\s
        Для завершения работы напиши "stop\"""", resp.sayHello());
  }
}