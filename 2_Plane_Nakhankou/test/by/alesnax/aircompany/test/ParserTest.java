package by.alesnax.aircompany.test;

import by.alesnax.aircompany.parser.AirLinerParser;
import by.alesnax.aircompany.parser.PlaneParser;

import org.junit.*;

//static import
import static org.junit.Assert.*;


/**
 * Created by alesnax on 12.10.2016.
 */
public class ParserTest {

    private  PlaneParser parser;

    @Before
    public void initParser(){
        parser = new AirLinerParser();
    }

    @After
    public void destroyParser(){
        parser = null;
    }

    @Test
    public void airLinerParserShouldReturnCheckedInfo(){
        String[] info = {"Airliner", "312$&^$A", "Boe%*&ing-747",  "30*&00", "00",   "15^&0", "500^(*^0"};
        parser= new AirLinerParser();
        String[] plane = parser.parsePlane(info);
        assertNotNull(plane);
        assertTrue("parsePlane method in airLinerParser Should return 6 args", plane.length == 6);

        assertTrue("ID should be changed to ID + WARN! CHECK!", plane[0].equals(info[1] + "WARN! CHECK!"));
        assertTrue("Model should be changed to Model + WARN! CHECK!", plane[1].equals(info[2] + "WARN! CHECK!"));
        assertFalse("Fuel consumption should be chaned to default:", info[3].equals(plane[2]));
        assertFalse("TankVolume should be changed to default:", info[4].equals(plane[3]));
        assertFalse("Seats num should be changed to Default:", info[5].equals(plane[4]));
        assertFalse("Luggage weight should be changed to Default:", info[6].equals(plane[5]));
    }


    @Test
    public void airLinerParserShouldReturnSameInfo(){
        String[] info = {"Airliner", "312A", "Boeing-747",  "3", "10000",   "150", "5000"};
        parser= new AirLinerParser();
        String[] plane = parser.parsePlane(info);
        assertNotNull(plane);
        assertTrue("parsePlane method in airLinerParser Should return 6 args", plane.length == 6);

        assertTrue("ID should pass", plane[0].equals(info[1]));
        assertTrue("Model should pass:", plane[1].equals(info[2]));
        assertTrue("Fuel consumption should pass:", info[3].equals(plane[2]));
        assertTrue("TankVolume should pass:", info[4].equals(plane[3]));
        assertTrue("Seats num should pass:", info[5].equals(plane[4]));
        assertTrue("Luggage weight should pass:", info[6].equals(plane[5]));
    }

}
