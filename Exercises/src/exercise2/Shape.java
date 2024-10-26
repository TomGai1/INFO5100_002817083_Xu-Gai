package exercise2;

public abstract class Shape {
    protected static String defaultColor = "Black";
    protected String color;
    protected String name;

    public Shape(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    public static String getDefaultColor() {
        return defaultColor;
    }

    public static void setDefaultColor(String color) {
        defaultColor = color;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return "This is a " + color + " " + name;
    }
}
