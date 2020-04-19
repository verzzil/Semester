package Semestr;


public class GraphicPicElement implements Comparable<Figure> {
    private GraphicPicElement next;
    private Figure figure;

    GraphicPicElement(Figure figure) {
        this.figure = figure;
        this.next = null;
    }

    @Override
    public int compareTo(Figure o) {
        if(this.figure.getType() == o.getType() &&
                isAllPointsEquals(o) &&
                this.figure.getColor() == o.getColor()) return 0;
        return -1;
    }

    private boolean isAllPointsEquals(Figure a) {
        int[] points = figure.getPoints(), apoints = a.getPoints();
        for(int i = 0; i < points.length; i++) {
            if(points[i] != apoints[i]) return false;
        }
        return true;
    }

    public GraphicPicElement getNext() {
        return this.next;
    }
    public Figure getFigure() {
        return this.figure;
    }

    public void setNext(GraphicPicElement figure) {
        this.next = figure;
    }

}
