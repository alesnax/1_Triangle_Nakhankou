package by.alesnax.textparsing.interpreter;

import java.util.ArrayDeque;

/**
 * Created by alesnax on 12.11.2016.
 */

public class Context {
    public ArrayDeque<Double> contextValues = new ArrayDeque<>();

    public Double popValue(){
        return contextValues.pop();
    }

    public void pushValue(Double value){
        this.contextValues.push(value);
    }
}



