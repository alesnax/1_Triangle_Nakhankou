package by.alesnax.aircompany.test;

import by.alesnax.aircompany.action.PlaneAction;
import by.alesnax.aircompany.entity.AirCompany;
import by.alesnax.aircompany.entity.Plane;
import by.alesnax.aircompany.factory.Creator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

//static import
import static org.junit.Assert.*;

/**
 * Created by alesnax on 12.10.2016.
 */
public class PlaneActionTest {

    private static AirCompany company;
    private static Creator creator;

    @BeforeClass
    public static void initCompany() {
        company = new AirCompany("Belavia", "Bel");
        creator = new Creator();
        company.setAirCraft(creator.createAirCraft("text/Belavia.txt"));
    }

    @AfterClass
    public static void destroyCompany() {
        creator = null;
        company = null;
    }

    @Test
    public void checkIfDefineTotalPassengersFunctionReturnTrueValue() {
        int actual = PlaneAction.defineTotalPassengersOnBoard(company);
        assertTrue("Actual num doesn't equal to expected number of passengers", actual == 150);
    }

    @Test
    public void checkIfTotalCarryingFunctionReturnValue() {
        double actual = PlaneAction.defineTotalCarrying(company);
        assertEquals("Actual num doesn't equal to expected number of passengers", 125000,actual, 0.01);
    }

    @Test
    public void checkIf_FindPlaneByFelConsumptionFunctionReturnTrueValue() {
        ArrayList<Plane> planes = PlaneAction.findPlaneByFuelConsumption(company, 3, 6);
        int actual = planes.size();
        assertTrue("Actual num " + actual + " doesn't equal to expected number of passengers", actual == 2);
    }

}
