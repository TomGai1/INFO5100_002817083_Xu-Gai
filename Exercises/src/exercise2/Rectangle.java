package exercise2;

public class Rectangle extends Shape {
    protected double length;
    protected double width;

    public Rectangle(double length, double width, String color) {
        super("Rectangle", color);
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
}
