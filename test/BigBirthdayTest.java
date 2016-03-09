import guest.Guest;
import guest.MailTemplate;
import guest.specifics.Address;
import guest.specifics.Age;
import guest.specifics.Gender;
import guest.specifics.Name;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BigBirthdayTest {

    private BigBirthday birthday;
    private Guest sooraj;
    private Guest shibi;

    @Before
    public void setUp() throws Exception {
        birthday = new BigBirthday();
        sooraj = new Guest(new Name("Sooraj","Parameswaran"), Gender.defineGenderAs("Male"), new Age(21), new Address("Thrissur","Kerala","India"));
        shibi = new Guest(new Name("Shibi","Reguvaran"),Gender.defineGenderAs("Female"),new Age(20),new Address("Thrissur","Kerala","India"));
    }

    @Test
    public void testCreateGuest() throws Exception {
        Guest result = birthday.createGuest("Sooraj,Parameswaran,Male,21,Thrissur,Kerala,India");

        assertEquals(sooraj, result);
    }

    @Test
    public void testCreatePersonFromCSV() throws Exception {
        People result = birthday.createPersonFromCSV("Sooraj,Parameswaran,Male,21,Thrissur,Kerala,India\n"+
                                                     "Shibi,Reguvaran,Female,20,Thrissur,Kerala,India\n");

        for (Object person : result) {
            assertTrue(person.equals(sooraj) || person.equals(shibi));
        }
    }

    @Test
    public void testPrint() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        People people = new People();
        people.add(sooraj);
        people.add(shibi);

        MailTemplate template = MailTemplate.createTemplate("first_name last_name");

        birthday.print(template, people);
        assertEquals("Sooraj Parameswaran\n" +
                "Shibi Reguvaran\n",outContent.toString());
    }
}