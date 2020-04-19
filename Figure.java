package Semestr;

import java.util.Arrays;

public class Figure {

    private Type type;
    private int[] points;
    private Color color;

    Figure(String type, int ltX, int ltY, int brX, int brY, String clr) throws Exception {
        this.points = new int[]{ltX,ltY,brX,brY};

        for(Type t : Type.values()) {
            if(t.type.equals(type)) {
                this.type = t;
                break;
            }
        }
        if(this.type == null) throw new Exception("Uncorrect type of figure");

        for(Color c: Color.values()) {
            if(c.color.equals(clr)) {
                this.color = c;
            }
        }
        if(color == null) throw new Exception("Uncorrect color of figure");

    }

    public double getArea() {
        if(type.type.equals("circle")) {
            return Math.abs(Math.PI*Math.pow(points[2],2));
        }
        else if(type.type.equals("rectangle")) {
            return Math.abs((points[0]+points[2])*(points[1]+points[3]));
        }
        return -1;
    }

    public String getFigureInfo() {
        return type+" "+Arrays.toString(points)+" "+color;
    }
    public int[] getPoints(){
        return this.points;
    }
    public Type getType() {
        return this.type;
    }
    public Color getColor() {
        return this.color;
    }

    public enum Type {
        CIRCLE("circle"),
        RECTANGLE("rectangle"),
        SECTION("section");

        String type;

        Type(String type) {
            this.type = type;
        }
    }

    public enum Color {
        RED("red"),
        BLUE("blue"),
        GREEN("green"),
        BLACK("black"),
        PINK("pink"),
        WHITE("white"),
        YELLOW("yellow"),
        ORANGE("orange");

        String color;

        Color(String color) {
            this.color = color;
        }
    }

}