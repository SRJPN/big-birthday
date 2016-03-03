import person.Person;
import person.contact.MailTemplate;

import java.util.ArrayList;

public class Big_Birthday {
    public static final String FILE = "data/records";
    public static final String MAILTEMPLATE = "data/template";




    public static void main(String[] args) throws Exception {

        String data =  ReadFile.addFile(FILE).getContent();
        String template =  ReadFile.addFile(MAILTEMPLATE).getContent();

        PersonCreator personCreator = new PersonCreator(data);
        ArrayList persons = personCreator.createPersonFromCSV();



        MailTemplate mailTemplate = MailTemplate.createTemplate(template);

        for (Object person1 : persons) {
            Person person = (Person) person1;
            System.out.println(mailTemplate.generate(person.getMailAddress()));
        }

    }
}