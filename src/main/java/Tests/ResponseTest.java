package Tests;

import static org.junit.jupiter.api.Assertions.*;

import bot.Game;
import bot.Response;
import org.junit.jupiter.api.Test;

class ResponseTest {
  Response resp = new Response();

  @Test
  void response() {
    long id = 123;
    resp.response("go",id);
    resp.response("2",id);
    resp.response("2",id);
    assertEquals("\nВерных ответов:" + "2" +
            "\nНеверных ответов:" + "0", resp.map.get(id).statistics());
  }

  @Test
  void getHelp() {
    assertEquals("""
        Для получения справочной информации напиши\s
        Для начала игры напиши команду "go\"""", resp.getHelp());
  }

  @Test
  void sayHello() {
    assertEquals("""
        Привет, я квиз-бот!
        Игра состоит из 3 вопросов
        Я могу проводить квиз в одиночном и совместном режиме
        Для начала игры напиши команду "go"
        Ответить на вопрос можно написав номер ответа\s
        Для повторного получения справочной информации напиши "help\"""", resp.sayHello());
  }
}