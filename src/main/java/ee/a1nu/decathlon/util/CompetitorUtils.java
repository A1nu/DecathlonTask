package ee.a1nu.decathlon.util;

import static java.lang.Double.parseDouble;

import java.util.logging.Logger;

public class CompetitorUtils {
    private static final Logger LOG = Logger.getLogger(CompetitorUtils.class.getName());
    private static final int SECONDS_IN_MINUTE = 60;
    
    public static String validateName(String name) {
        if (name.matches("([\\p{L}\\s.\\-()]{2,}){3,}")) {
            return name;
        }
        else {
            LOG.severe("Competitor's name is invalid.\n");
            throw new IllegalArgumentException();
        }
    }
    
    public static String stringifyFifteenHundredsResult(Double result) {
        int minutes = result.intValue() / SECONDS_IN_MINUTE;
        Double seconds = result - minutes * SECONDS_IN_MINUTE;
        return String.format("%s:%.2f", minutes, seconds);
    }
    
    public static Double validateAndParseCompetitionResult(String string) {
        String[] resultValues = string.split("[.:]");
    
        try {
            if (resultValues.length == 1 || resultValues.length == 2)
                return parseDouble(string);
            else if (resultValues.length == 3)
                return parseDouble(resultValues[0]) * SECONDS_IN_MINUTE + parseDouble(resultValues[1] + "." + resultValues[2]);
            else
                throw new NumberFormatException();
        }
        catch (NumberFormatException e) {
            LOG.severe("Competitor's performance result is not in a valid format.\n");
            throw new IllegalArgumentException(e);
        }
    }
}
