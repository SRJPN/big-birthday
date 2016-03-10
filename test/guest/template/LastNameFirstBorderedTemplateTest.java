package guest.template;

import guest.specifics.Address;
import guest.specifics.Gender;
import guest.specifics.Name;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LastNameFirstBorderedTemplateTest {

    @Test
    public void testGenerate() throws Exception {
        Name name = new Name("Sooraj", "Parameswaran");
        Gender gender = Gender.defineGenderAs("MALE");
        Address address = new Address("Thrissur","Kerala","India");

        BorderedTemplate template = new LastNameFirstBorderedTemplate("+","-","|");

        String expected = "+--------------------------+\n" +
                          "| Mr. Parameswaran, Sooraj |\n" +
                          "| Thrissur, Kerala         |\n" +
                          "| India                    |\n" +
                          "+--------------------------+\n";

        assertEquals(expected,template.generate(name, gender, address));
    }
}