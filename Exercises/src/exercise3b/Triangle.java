package exercise3b;

public class Triangle extends Shape {
    private static final long serialVersionUID = 1L;
    private double side1, side2, side3;

    public Triangle(double side1, double side2, double side3, String color) {
        super("Triangle", color);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public double calculateArea() {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    public double calculatePerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with sides: " + side1 + ", " + side2 + ", " + side3;
    }
}