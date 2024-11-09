package exercise3b;

public class Square extends Rectangle {
    private static final long serialVersionUID = 1L;

    public Square(double side, String color) {
        super(side, side, color);
        this.name = "Square";
    }
}
