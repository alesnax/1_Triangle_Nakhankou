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
        ArrayList<Plane> list = creator.createAirCraft("text/Belavia.txt");

        belavia.setAirCraft(list);

        double carrying = PlaneAction.defineTotalCarrying(belavia);
        int pass = PlaneAction.defineTotalPassengersOnBoard(belavia);
        System.out.println("Carrying: " + carrying + ", totalPass: " + pass);
    }
}
