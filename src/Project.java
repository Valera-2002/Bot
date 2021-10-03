public class Project
{
    public static void main(String[] args)
    {
        Response responser = new Response();
        Data_input reader = new Data_input();
        responser.sayHello();
        while (true)
        {
            String str_input = reader.read();
            responser.response(str_input);
        }
    }
}