package by.alesnax.aircompany.factory;

import by.alesnax.aircompany.entity.Plane;

/**
 * Created by alesnax on 08.10.2016.
 */
public abstract class AbstractPlaneFactory {

     abstract Plane createPlane(String[] info);
}
