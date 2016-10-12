package by.alesnax.aircompany.entity;

import java.util.ArrayList;

/**
 * Created by alesnax on 10.10.2016.
 */
public class AirCompany {

    private String name;
    private String country;
    private ArrayList<Plane> airCraft;

    public AirCompany(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public AirCompany(String name, String country, ArrayList<Plane> airCraft) {
        this.name = name;
        this.country = country;
        this.airCraft = airCraft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList<Plane> getAirCraft() {
        return airCraft;
    }

    public void setAirCraft(ArrayList<Plane> airCraft) {
        this.airCraft = airCraft;
    }

    @Override
    public String  toString() {
        return "AirCompany{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", airCraft=" + airCraft +
                '}';
    }
}
