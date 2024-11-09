package exercise3b;

import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {
        // Create shape objects
        Shape[] shapes = {
                new Triangle(3, 4, 5, "Red"),
                new Rectangle(4, 5, "Green"),
                new Square(5, "Yellow"),
                new Circle(3, "Purple")
        };

        // Serialize objects
        try {
            FileOutputStream fileOut = new FileOutputStream("shapes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            // Serialize each shape
            for (Shape shape : shapes) {
                out.writeObject(shape);
                System.out.println("Serialized " + shape.getDescription());
            }

            out.close();
            fileOut.close();
            System.out.println("\nSerialization completed. Objects saved to shapes.ser\n");

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Deserialize objects
        try {
            FileInputStream fileIn = new FileInputStream("shapes.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // Read and display each shape
            for (int i = 0; i < shapes.length; i++) {
                Shape shape = (Shape) in.readObject();
                System.out.println("Deserialized " + shape.getDescription());
                System.out.println("Area: " + shape.calculateArea());
                System.out.println("Perimeter: " + shape.calculatePerimeter() + "\n");
            }

            in.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
