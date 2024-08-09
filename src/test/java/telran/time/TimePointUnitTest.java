
package telran.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TimePointUnitTest {
    @Test
    public void TimeUnitTest (){
        TimePoint p1 = new TimePoint(1, TimeUnit.HOUR);
        TimePoint p2 = new TimePoint(90, TimeUnit.MINUTE);
    assertEquals(1800,TimeUnit.SECOND.between(p1,p2) );
    assertEquals(30,TimeUnit.MINUTE.between(p1,p2) );
    assertEquals(0.5f,TimeUnit.HOUR.between(p1,p2) );
    }

    @Test
    public void TimePointTest (){
        TimePoint p1 = new TimePoint(60, TimeUnit.SECOND);
        TimePoint p2 = new TimePoint(1, TimeUnit.MINUTE);
        assertEquals(0 ,p1.compareTo(p2));
        TimePoint p3 = new TimePoint(60, TimeUnit.SECOND);
        TimePoint p4 = new TimePoint(1, TimeUnit.HOUR);
        assertEquals(-1 ,p3.compareTo(p4));
        TimePoint p5 = new TimePoint(90, TimeUnit.MINUTE);
        TimePoint p6 = new TimePoint(1, TimeUnit.HOUR);
        assertEquals(1 ,p5.compareTo(p6));
        assertTrue(p1.equals(p2));
        assertFalse(p3.equals(p6));
        TimePoint expected = new TimePoint(1, TimeUnit.MINUTE);
        TimePoint expected2 = new TimePoint(1.5f, TimeUnit.HOUR);
        assertEquals(expected, p1.convert(TimeUnit.MINUTE));
        assertEquals(p1, p2.convert(TimeUnit.SECOND));
        assertEquals(expected2, p5.convert(TimeUnit.HOUR));
//Tests for all being written methods
}

@Test
public void PlusTimePointAdjusterTest (){
    TimePoint p1 = new TimePoint(60, TimeUnit.SECOND);
    TimePoint expected = new TimePoint(65, TimeUnit.SECOND);
    PlusTimePointAdjuster newAdjuster = new PlusTimePointAdjuster(5,TimeUnit.SECOND);
    assertEquals(expected, p1.with(newAdjuster));
    TimePoint p2 = new TimePoint(60, TimeUnit.MINUTE);
    TimePoint expected2 = new TimePoint(1.5f, TimeUnit.HOUR);
    PlusTimePointAdjuster newAdjuster2 = new PlusTimePointAdjuster(30,TimeUnit.MINUTE);
    assertEquals(expected2, p2.with(newAdjuster2));
}
@Test
public void FutureProximityAdjusterTest (){
    TimePoint p1 = new TimePoint(60, TimeUnit.SECOND);//60
        TimePoint p2 = new TimePoint(1.5f, TimeUnit.MINUTE);//90
        TimePoint p3 = new TimePoint(5, TimeUnit.SECOND);//5
        TimePoint p4 = new TimePoint(1, TimeUnit.HOUR);//3600
        TimePoint p5 = new TimePoint(90, TimeUnit.MINUTE);//5400
    TimePoint[] newArray = {p1, p2, p3, p4, p5};
    TimePoint given = new TimePoint(30, TimeUnit.MINUTE);//1800
    FutureProximityAdjuster newAdjuster = new FutureProximityAdjuster(newArray);
    assertEquals(p4, given.with(newAdjuster));
}
}