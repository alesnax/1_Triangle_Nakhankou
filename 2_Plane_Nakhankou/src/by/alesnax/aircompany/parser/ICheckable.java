package by.alesnax.aircompany.parser;

/**
 * Created by alesnax on 09.10.2016.
 */
public interface ICheckable {

    String checkId(String[] info);
    String checkModel(String[] info);
    String checkFuelConsumtion(String[] info);
    String checkTank(String[] info);

}
