package by.alesnax.triangle.entity;

/**
 * Created by alesnax on 05.10.2016.
 */

public class Triangle {

    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "pointA=" + a +
                ", pointB=" + b +
                ", pointC=" + c +
                '}';
    }
}
