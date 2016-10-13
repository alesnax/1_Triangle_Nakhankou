package by.alesnax.aircompany.action;

import by.alesnax.aircompany.entity.Plane;

import java.util.Comparator;


/**
 * Created by alesnax on 10.10.2016.
 */
public class DistanceComparator implements Comparator<Plane> {

    @Override
    public int compare(Plane o1, Plane o2) {
        double dist1 = o1.getFuelTank() / o1.getFuelConsumption();
        double dist2 = o2.getFuelTank() / o2.getFuelConsumption();
        if(dist1 < dist2){
            return -1;
        }else if(dist1 == dist2){
            return 0;
        }else{
            return 1;
        }
    }
}
