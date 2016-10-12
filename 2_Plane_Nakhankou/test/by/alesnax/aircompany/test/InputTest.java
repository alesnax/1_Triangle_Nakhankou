package by.alesnax.aircompany.test;

import by.alesnax.aircompany.input.AircraftReader;

import org.junit.*;

import java.util.ArrayList;

//static import
import static org.junit.Assert.*;

/**
 * Created by alesnax on 10.10.2016.
 */


public class InputTest {

    private static AircraftReader reader;

    @BeforeClass
    public static void initReader(){
        reader = new AircraftReader();
    }

    @AfterClass
    public static void destroyReader(){
        reader = null;
    }

    @Test (expected = RuntimeException.class)
    public void readerShouldThrowExceptionIfWrongFileName(){
        String fileName = "wrongFileName";
        reader.readFromTxtFile(fileName);
    }

    @Test
    public void readerShouldReturnListWith3Args(){
        String fileName = "text/Belavia.txt";
        ArrayList<String> plane = reader.readFromTxtFile(fileName);
        int expected = 3;
        int actual = plane.size();
        assertEquals("Size of List for 3 args is Wrong:", expected, actual);
    }

    @Test
    public void readerShouldNotReturnListWithNoArgs(){
        String fileName = "text/Belavia.txt";
        ArrayList<String> plane = reader.readFromTxtFile(fileName);
        int expected = 0;
        int actual = plane.size();
        assertNotEquals("Size of List for 0 args is Wrong:", expected, actual);
    }

}
