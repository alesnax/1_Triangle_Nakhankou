package by.alesnax.aircompany.runner;

import by.alesnax.aircompany.action.PlaneAction;
import by.alesnax.aircompany.entity.AirCompany;

import by.alesnax.aircompany.entity.Plane;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by alesnax on 12.10.2016.
 */
public class ReportResult {
    private static Logger logger = LogManager.getLogger(ReportResult.class);

    public static void printCompanyCapacityAndSeats(AirCompany company){
        int seats = PlaneAction.defineTotalPassengersOnBoard(company);
        double capacity = PlaneAction.defineTotalCarrying(company);

        logger.log(Level.INFO, "\n" + "Total number of seats on plane of company: " + seats + ", total capacity of aircraft: " + capacity + " kg.");
    }

    public static void printPlanesByFuelConsumption(AirCompany company, double lowLevel, double highLevel){
        ArrayList<Plane> planes = PlaneAction.findPlaneByFuelConsumption(company, lowLevel, highLevel);
        int count = 0;
        StringBuilder sb = new StringBuilder();

        for (Plane plane : planes) {
            count++;
            sb.append("\n").append(count).append(".  Plane Id: ").append(plane.getId()).append(",  model: ").append(plane.getModel())
                    .append(" has fuelConsumption ").append(plane.getFuelConsumption()).append(" kg/km");
        }

        logger.log(Level.INFO, "\n" + "Found " + count + " planes with required level of fuel consumption! " + sb.toString());
    }

    public static void printCompanyAirCraftSortByDistance(AirCompany company){
        ArrayList<Plane> airCraft = company.getAirCraft();
        PlaneAction.sortPlanesByDistance(airCraft);

        StringBuilder sb = new StringBuilder();
        for (Plane plane : airCraft) {
            sb.append("\n").append(plane);
        }

        logger.log(Level.INFO, "\n" + "List of planes sorted by distance: " + sb.toString());
    }
}
