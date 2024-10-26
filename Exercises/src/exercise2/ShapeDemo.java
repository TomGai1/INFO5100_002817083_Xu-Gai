package exercise2;

public class ShapeDemo {
    public static void main(String[] args) {
        // Demonstrate static field
        System.out.println("Default color for all shapes: " + Shape.getDefaultColor());
        Shape.setDefaultColor("Blue");
        System.out.println("New default color: " + Shape.getDefaultColor());

        // Create array of shapes to demonstrate polymorphism
        Shape[] shapes = new Shape[4];
        shapes[0] = new Triangle(3, 4, 5, "Red");
        shapes[1] = new Rectangle(4, 5, "Green");
        shapes[2] = new Square(5, "Yellow");
        shapes[3] = new Circle(3, "Purple");

        // Demonstrate polymorphism by processing all shapes uniformly
        for (Shape shape : shapes) {
            System.out.println("\n" + shape.getDescription());
            System.out.println("Area: " + shape.calculateArea());
            System.out.println("Perimeter: " + shape.calculatePerimeter());
        }
    }
}
