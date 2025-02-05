package data;

public enum ColorData {
    RED("RED"),
    BLUE("BLUE"),
    GREEN("GREEN"),
    BROWN("BROWN");


    private final String name;

    ColorData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}