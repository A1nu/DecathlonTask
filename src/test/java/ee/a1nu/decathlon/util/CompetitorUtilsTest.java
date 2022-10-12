package ee.a1nu.decathlon.util;

import static ee.a1nu.decathlon.util.CompetitorUtils.stringifyFifteenHundredsResult;
import static ee.a1nu.decathlon.util.CompetitorUtils.validateAndParseCompetitionResult;
import static ee.a1nu.decathlon.util.CompetitorUtils.validateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class CompetitorUtilsTest {
    
    @Test
    public void testNameValidation() {
        assertThat(validateName("Henry  Ford"), is("Henry  Ford"));
        assertThat(validateName("Anna J.  Reyn"), is("Anna J.  Reyn"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void noSpecialCharactersAndNumbers() {
        validateName("John!Doe");
        validateName("John Doe 3");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void noNumbers() {
        validateName("John Doe 3");
    }
    
    @Test
    public void validateNumberStringification() {
        assertThat(stringifyFifteenHundredsResult(325.72), is("5:25.72"));
        assertThat(stringifyFifteenHundredsResult(0.0), is("0:0.00"));
    }
    
    @Test
    public void resultsAreCorrectlyParsing() {
        assertThat(validateAndParseCompetitionResult("5:25.72"), is(325.72));
        assertThat(validateAndParseCompetitionResult("12.61"), is(12.61));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void incorrectValuesShowError() {
        validateAndParseCompetitionResult("invalid.message");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void incorrectValuesWithColonShowError() {
        validateAndParseCompetitionResult("invalid:message.test");
    }
}