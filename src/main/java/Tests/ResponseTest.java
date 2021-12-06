package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import bot.Response;
import org.junit.jupiter.api.Test;

class ResponseTest {
  Response resp = new Response();

 @Test
  void responseAddQuestions() {
     assertEquals("Нет доступа", resp.response("add", 123));
  }
  @Test
  void responseGame() {
    long id = 123;
    resp.response("go",id);
    resp.response("2",id);
    resp.response("2",id);
    assertEquals("""

            Верных ответов:2
            Неверных ответов:0"""+
            "\nОбщее время: "+resp.gameMap.get(id).getTime()/1000+ " c", resp.gameMap.get(id).statistics());
 }
  @Test
  void getHelp() {
    assertEquals("""
            Справочная информация:\s
            Для начала игры напиши команду "go"\s
            Для добавления вопроса напиши "add\"""", resp.getHelp());
  }
}