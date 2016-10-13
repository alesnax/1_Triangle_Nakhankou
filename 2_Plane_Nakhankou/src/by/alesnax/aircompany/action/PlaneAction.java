package by.alesnax.aircompany.action;

import by.alesnax.aircompany.entity.AirCompany;
import by.alesnax.aircompany.entity.Airliner;
import by.alesnax.aircompany.entity.Plane;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by alesnax on 08.10.2016.
 */
public class PlaneAction {


    public static int defineTotalPassengersOnBoard(AirCompany company){
        ArrayList<Plane> airCraft = company.getAirCraft();
        int pass = 0;
        Plane plane = null;
        for (Plane anAirCraft : airCraft) {
            plane = anAirCraft;
            if (plane instanceof Airliner) {
                pass += ((Airliner) plane).getSeats();
            }
        }
        return pass;
    }

    public static double defineTotalCarrying(AirCompany company){
        ArrayList<Plane> airCraft = company.getAirCraft();
        double weight = 0;
        for (Plane plane : airCraft) {
            weight += plane.calcMaxCarrying();
        }
        return weight;
    }


    /*
        method sortPlanesByDistance has ArrayList<Plane> as parameter, not to change order of  company aircraft
        after every calling but to change just order of got local aircraft as in PrintResult class
    */

    public static void sortPlanesByDistance(ArrayList<Plane> airCraft){
        Collections.sort(airCraft, new DistanceComparator());
    }


    public static ArrayList<Plane> findPlaneByFuelConsumption(AirCompany company, double lowLevel, double highLevel){
        ArrayList<Plane> airCraft = company.getAirCraft();
        ArrayList<Plane> result = new ArrayList<>();

        for (Plane anAirCraft : airCraft) {
            double cons = anAirCraft.getFuelConsumption();
            if (cons >= lowLevel && cons <= highLevel) {
                result.add(anAirCraft);
            }
        }
        return result;
    }

}
