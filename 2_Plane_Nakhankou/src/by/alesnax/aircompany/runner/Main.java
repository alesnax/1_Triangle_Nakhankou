package by.alesnax.aircompany.runner;

import by.alesnax.aircompany.action.PlaneAction;
import by.alesnax.aircompany.entity.*;
import by.alesnax.aircompany.factory.*;

import java.util.ArrayList;

/**
 * Created by alesnax on 08.10.2016.
 */
public class Main {

    public static void main(String[] args) {

        AirCompany belavia = new AirCompany("Belavia", "BEL");

        Creator creator = new Creator();
        ArrayList<Plane> airCraft = creator.createAirCraft("text/Belavia.txt");
        belavia.setAirCraft(airCraft);

        ReportResult.printCompanyCapacityAndSeats(belavia);
        ReportResult.printPlanesByFuelConsumption(belavia, 2, 8);
        ReportResult.printCompanyAirCraftSortByDistance(belavia);


    }
}
