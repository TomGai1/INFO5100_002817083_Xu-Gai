package exercise2;

public class Circle extends Shape {
    private double radius;
    private static final double PI = 3.14159;

    public Circle(double radius, String color) {
        super("Circle", color);
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * PI * radius;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with radius: " + radius;
    }
}
