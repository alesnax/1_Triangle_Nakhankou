package by.alesnax.aircompany.parser;

import by.alesnax.aircompany.entity.SpecialPlane;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by alesnax on 09.10.2016.
 */
public class SpecialPlaneParser extends PlaneParser{
    private static Logger logger = LogManager.getLogger(SpecialPlaneParser.class);

    private static final String DEFAULT_USEFUL_WEIGHT = "1000";
    private static final double MIN_USEFUL_WEIGHT = 150;

    @Override
    public String[] parsePlane(String[] info) {
        String id = checkId(info);
        String model = checkModel(info);
        String fuelConsumption = checkFuelConsumtion(info);
        String fuelTank = checkTank(info);
        String cargoWeight = checkUsefulWeight(info);
        String type = checkType(info);
        return new String[]{id, model, fuelConsumption, fuelTank, cargoWeight, type};
    }


    private String checkUsefulWeight(String[] info){
        String usefulWeight = info[5];
        try {
            double weight = Double.parseDouble(usefulWeight);
            return (weight > MIN_USEFUL_WEIGHT) ? usefulWeight : DEFAULT_USEFUL_WEIGHT;
        }catch (NumberFormatException e){
            logger.log(Level.WARN, "Wrong useful weight, please check! Default weight " + DEFAULT_USEFUL_WEIGHT + "  was set in aircompany: " + info[0] + " " + info[1] + " " + info[2]);
        }
        return DEFAULT_USEFUL_WEIGHT;
    }

    private String checkType(String[] info){
        String type = info[6];
        try{
            SpecialPlane.TypeUse checkedType = SpecialPlane.TypeUse.valueOf(type.toUpperCase());
            return checkedType.toString();
        }catch (EnumConstantNotPresentException e){
            logger.log(Level.WARN, "Wrong specialPlane type, please check! Default type " + SpecialPlane.TypeUse.OTHER.toString()
                    + "  was set in aircompany: " + info[0] + " " + info[1] + " " + info[2]);
        }
        return SpecialPlane.TypeUse.OTHER.toString();
    }
}
