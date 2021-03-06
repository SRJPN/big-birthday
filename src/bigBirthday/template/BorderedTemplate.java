package bigBirthday.template;

import bigBirthday.guest.specifics.Address;
import bigBirthday.guest.specifics.Gender;
import bigBirthday.guest.specifics.Name;

import static java.lang.String.format;

public class BorderedTemplate implements GuestTemplate {

    private final String corner;
    private final String side;
    private final String upDown;

    public BorderedTemplate(String corner, String upDown, String side) {
        this.corner = corner;
        this.upDown = upDown;
        this.side = side;
    }

    @Override
    public String generate(Name name, Gender gender, Address address) {
        String firstLine = format("%s. %s %s",gender.getInitial(),name.getFirstName(),name.getSecondName());
        String secondLine = format("%s, %s",address.getCity(),address.getState());
        String thirdLine = format("%s",address.getCountry());
        return generateBorder(firstLine, secondLine, thirdLine);
    }

    String generateBorder(String firstLine, String secondLine, String thirdLine) {
        int maxLength = findMaxLength(firstLine, secondLine, thirdLine);
        String upDown = generateUpDown(maxLength);
        String topLine = wrap(firstLine, maxLength);
        String midLine = wrap(secondLine, maxLength);
        String bottomLine = wrap(thirdLine, maxLength);

        return format("%s\n"+
                             "%s\n" +
                             "%s\n" +
                             "%s\n" +
                             "%s\n",upDown,topLine,midLine,bottomLine,upDown);
    }

    private String wrap(String line, int maxLength) {
        String result = side;
        result = result.concat(" "+line);
        result = result.concat(generateSymbolForLength(maxLength-line.length()," "));
        result = result.concat(" "+side);
        return result;
    }

    private String generateUpDown(int maxLength) {
        String upDown = "";
        upDown = upDown.concat(corner);
        upDown = upDown.concat(generateSymbolForLength(maxLength+2, this.upDown));
        upDown = upDown.concat(corner);
        return upDown;
    }

    private String generateSymbolForLength(int maxLength, String symbol) {
        String result = "";
        for (int i = 0; i < maxLength ; i++) {
            result = result.concat(symbol);
        }
        return result;
    }

    private int findMaxLength(String firstLine, String secondLine, String thirdLine) {
        String longestLine = firstLine.length() >= secondLine.length() ? firstLine : secondLine;
        return (thirdLine.length() >= longestLine.length()) ? thirdLine.length() : longestLine.length();
    }
}
