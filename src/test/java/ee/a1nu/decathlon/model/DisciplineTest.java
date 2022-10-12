package ee.a1nu.decathlon.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.Matcher;
import org.junit.Test;

public class DisciplineTest {
    @Test
    public void calculateTrackEventScore() {
        assertThat(Discipline.HUNDRED_METERS_RUN.calculateTrackEventScore(12.61), is(536));
    }
    
    @Test
    public void calculateJumpScore() {
        assertThat(Discipline.LONG_JUMP.calculateJumpScore(4.53), is(295));
    }
    
    @Test
    public void calculateThrowScore() {
        assertThat(Discipline.SHOT_PUT.calculateThrowScore(7.79), is(354));
    }
    
    @Test
    public void calculateEachEvent() {
        assertThat(calculateEventScores(12.61, 5.0, 9.22, 1.5, 60.39, 16.43, 21.6, 2.6, 35.81, 325.72),
                are(536, 382, 439, 389, 400, 685, 302, 264, 382, 421));
    }
    
    private double[] calculateEventScores(double... results) {
        double[] scores = new double[10];
        int i = 0;
        
        for (Discipline event : Discipline.values()) {
            scores[i] = event.calculateEventScore(results[i]);
            i++;
        }
        
        return scores;
    }
    
    private Matcher<double[]> are(double... expected) {
        return is(expected);
    }
}