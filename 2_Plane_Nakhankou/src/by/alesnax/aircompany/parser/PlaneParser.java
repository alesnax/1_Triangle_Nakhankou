package by.alesnax.aircompany.parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by alesnax on 09.10.2016.
 */
public abstract class PlaneParser implements IParsePlane, ICheckable {
    private static Logger logger = LogManager.getLogger(PlaneParser.class);

    private static final String ID_PATTERN = "[A-Z0-9]{1,4}[-]?[A-Z0-9]{1,4}";
    private static final String MODEL_PATTERN = "[A-Za-z0-9]+[-]?[A-Za-z0-9]+";

    private static final String CHECK_WARN = "WARN! CHECK!";


    private static final String DEFAULT_FUEL_CONSUMPTION = "10";
    private static final double MIN_CONSUMPTION = 1;
    private static final double MAX_CONSUMPTION = 30;

    private static final String DEFAULT_TANK_VOLUME = "10000";
    private static final double MIN_TANK_VOLUME = 500;



    public String checkId(String[] info){
        String id = info[1];
        if(id.matches(ID_PATTERN)){
            return id;
        }else{
            logger.log(Level.WARN, "Warn! check id, aircompany: " + info[0] + " " + info[1] + " " + info[2]);
        }
        return id + CHECK_WARN;
    }

    public String checkModel(String[] info){
        String model = info[2];
        if(model.matches(MODEL_PATTERN)){
            return model;
        }else{
            logger.log(Level.WARN, "Warn! check model, aircompany: " + info[0] + " " + info[1] + " " + info[2]);
        }
        return model + CHECK_WARN;
    }

    public String checkFuelConsumtion(String[] info){
        String fuelConsumption = info[3];
        try {
            double cons = Double.parseDouble(fuelConsumption);
            return (cons > MIN_CONSUMPTION && cons < MAX_CONSUMPTION ) ? fuelConsumption : DEFAULT_FUEL_CONSUMPTION;
        }catch (NumberFormatException e){
            logger.log(Level.WARN, "Wrong fuelConsumption, please check! Default consumption " + DEFAULT_FUEL_CONSUMPTION + " kg/km was set in aircompany: " + info[0] + " " + info[1] + " " + info[2]);
        }
        return DEFAULT_FUEL_CONSUMPTION;
    }

    public String checkTank(String[] info){
        String tankVolume = info[4];
        try {
            double volume = Double.parseDouble(tankVolume);
            return (volume > MIN_TANK_VOLUME ) ? tankVolume : DEFAULT_TANK_VOLUME;
        }catch (NumberFormatException e){
            logger.log(Level.WARN, "Wrong tankVolume, please check! Default volume " + DEFAULT_TANK_VOLUME + " kg was set in aircompany: " + info[0] + " " + info[1] + " " + info[2] );
        }
        return DEFAULT_TANK_VOLUME;
    }

}
