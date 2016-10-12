package by.alesnax.aircompany.test;

import by.alesnax.aircompany.entity.Plane;
import by.alesnax.aircompany.factory.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

//static import
import static org.junit.Assert.*;

/**
 * Created by alesnax on 12.10.2016.
 */
public class CreatorTest {

    private static Creator creator;
    private ArrayList<Plane> planes;
    private static String fileName;

    private String line;

    @BeforeClass
    public static void initFactory() {
        creator = new Creator();
    }

    @AfterClass
    public static void destroyFactory(){
        creator = null;
    }

    @Test(expected = RuntimeException.class)
    public void creatorShouldReturnExceptionIfWrongFileName() {
        fileName = "wrongName";
        creator.createAirCraft(fileName);

    }

    @Test
    public void creatorShouldReturnListOfPlanes() {
        fileName = "text/Belavia.txt";
        planes = creator.createAirCraft(fileName);
        assertNotNull(planes);
    }

    @Test
    public void creatorShouldReturnNotEmptyListOfPlanes() {
        fileName = "text/Belavia.txt";
        planes = creator.createAirCraft(fileName);
        assertNotNull(planes);
        assertTrue("List should have no 0 size!", planes.size() != 0);
    }


}
