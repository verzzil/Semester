package Semestr;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Scanner;

public class GraphicPic {

    private GraphicPicElement head;
    private GraphicPicElement tail;

    GraphicPic() {
        this.head = null;
        this.tail = this.head;
    }
    GraphicPic(String fileName) throws Exception {
        this.head = null;
        this.tail = null;

        FileReader fr = new FileReader(fileName);
        Scanner scan = new Scanner(fr);


        while(scan.hasNextLine()) {
            String[] dataAboutNewFigure = scan.nextLine().split(" ");
            Figure nFig = new Figure(
                    dataAboutNewFigure[0],Integer.parseInt(dataAboutNewFigure[1]),
                    Integer.parseInt(dataAboutNewFigure[2]),Integer.parseInt(dataAboutNewFigure[3]),
                    Integer.parseInt(dataAboutNewFigure[4]), dataAboutNewFigure[5]
            );

            GraphicPicElement temp = new GraphicPicElement(nFig);


            this.add(temp);

        }

        fr.close();
    }

    public void show() {
        GraphicPicElement temp = this.head;
        while(temp != null) {
            System.out.print(temp.getFigure().getFigureInfo()+"\n");
            temp = temp.getNext();
            if(head == tail) break;
        }
    }
    public void insert(Figure f) throws Exception {
        if(head == null) {
            this.add(new GraphicPicElement(f));
            return;
        }
        GraphicPicElement temp = head;
        while(temp.getNext() != null && temp.compareTo(f) == -1) {
            temp = temp.getNext();
            if(temp == tail) break;
        }
        Figure f2 = temp.getFigure();
        if(temp.compareTo(f) == 0) {
            Class figure = f2.getClass();
            Field a = figure.getDeclaredFields()[getRandomInteger(0,figure.getDeclaredFields().length-1)];

            a.setAccessible(true);
            if(a.getName().equals("type")) {
                Object temp2 = a.get(f2);
                while(temp2.equals(a.get(f2))){
                    a.set(f2, Figure.Type.values()[getRandomInteger(0,Figure.Type.values().length-1)]);
                }
            }
            else if(a.getName().equals("points")) {
                int id = getRandomInteger(0,((int[])a.get(f2)).length-1);
                int temp2 = ((int[])a.get(f2))[id];
                while(temp2 == ((int[])a.get(f2))[id]) {
                    ((int[])a.get(f2))[id] = getRandomInteger(-1000,1000);
                }
            }
            else if(a.getName().equals("color")) {
                Object temp2 = a.get(f2);
                while(temp2.equals(a.get(f2))) {
                    a.set(f2, Figure.Color.values()[getRandomInteger(0,Figure.Color.values().length-1)]);
                }
            }
            else {
                throw new Exception("Такого поля не существует");
            }
            a.setAccessible(false);
        }

        else {
            temp.setNext(new GraphicPicElement(f));
            tail = temp.getNext();
        }
    }
    public void delete(Figure.Type i) {
        GraphicPicElement temp = head;
        while(temp != null && temp.getFigure().getType() == i) {
            head = temp.getNext();
            temp = temp.getNext();
        }
        while(temp != null && temp.getNext() != null) {
            while(temp.getNext() != null && temp.getNext().getFigure().getType() == i) {
                temp.setNext(temp.getNext().getNext());
            }
            if(head == tail) break;
            temp = temp.getNext();
        }
    }
    public GraphicPic commonWith(Figure r) throws Exception {
        GraphicPic newGraphicPic = new GraphicPic();
        GraphicPicElement temp = this.head;

        int[] xRectCoords = new int[]{r.getPoints()[0],r.getPoints()[2]};
        int[] yRectCoords = new int[]{r.getPoints()[1],r.getPoints()[3]};

        while(temp != null) {
            Figure tempFig = temp.getFigure();
            int[] xTempCoords = new int[]{tempFig.getPoints()[0], tempFig.getPoints()[2]};
            int[] yTempCoords = new int[]{tempFig.getPoints()[1], tempFig.getPoints()[3]};

            int minXcoord =Math.min(xRectCoords[1],xRectCoords[0]);
            int maxXcoord = Math.max(xRectCoords[1],xRectCoords[0]);
            int minYcoord = Math.min(yRectCoords[1],yRectCoords[0]);
            int maxYcoord = Math.max(yRectCoords[1],yRectCoords[0]);

            String type = tempFig.getType().type;

            if (type.equals("rectangle")) {
                if ( ((xTempCoords[0] < minXcoord && xTempCoords[1] < minXcoord) ||
                        (xTempCoords[0] > maxXcoord && xTempCoords[1] > maxXcoord)) ||
                        ((yTempCoords[0] < minYcoord && yTempCoords[1] < minYcoord) ||
                        (yTempCoords[0] > maxYcoord && yTempCoords[1] > maxYcoord)) ) {
                } else {
                    newGraphicPic.add(temp);
                }
            }
            else if(type.equals("circle")) {
                int coordXLeft = tempFig.getPoints()[0]-tempFig.getPoints()[2];
                int coordXRight = tempFig.getPoints()[0]+tempFig.getPoints()[2];
                int coordYTop = tempFig.getPoints()[1]+tempFig.getPoints()[2];
                int coordYBottom = tempFig.getPoints()[1]-tempFig.getPoints()[2];
                int[] center = new int[]{tempFig.getPoints()[0],tempFig.getPoints()[1]};
                if( ((coordXLeft < minXcoord && coordXRight < minXcoord) ||
                        (coordXLeft > maxXcoord && coordXRight > maxXcoord)) ||
                        ((coordYTop < minYcoord && coordYBottom < minYcoord) ||
                                (coordYBottom > maxYcoord && coordYTop > maxYcoord))) {}
                else{
                    if(((center[0] >= minXcoord && center[0] <= maxXcoord) ||
                            (center[1] >= minYcoord && center[1] <= maxYcoord)) ||
                            ( (int)Math.sqrt((center[0]-xRectCoords[0])*(center[0]-xRectCoords[0])+(center[1]-yRectCoords[0])*(center[1]-yRectCoords[0])) < tempFig.getPoints()[2] ) ||
                            (int)Math.sqrt((center[0]-xRectCoords[1])*(center[0]-xRectCoords[1])+(center[1]-yRectCoords[1])*(center[1]-yRectCoords[1])) < tempFig.getPoints()[2] ||
                            (int)Math.sqrt((center[0]-(xRectCoords[0]+xRectCoords[1]))*(center[0]-(xRectCoords[0]+xRectCoords[1]))+(center[1]-yRectCoords[0])*(center[1]-yRectCoords[0])) < tempFig.getPoints()[2] ||
                            (int)Math.sqrt((center[0]-xRectCoords[0])*(center[0]-xRectCoords[0])+(center[1]-(yRectCoords[0]+yRectCoords[1]))*(center[1]-(yRectCoords[0]+yRectCoords[1]))) < tempFig.getPoints()[2]
                    )
                        newGraphicPic.add(temp);
                }
            }
            else if(type.equals("section")) {
                double leftLineY = (double)(-(yTempCoords[0]-yTempCoords[1])*minXcoord -
                        (xTempCoords[0]*yTempCoords[1]-xTempCoords[1]*yTempCoords[0])) /
                        (xTempCoords[1]-xTempCoords[0]);
                double rightLineY = (double)(-(yTempCoords[0]-yTempCoords[1])*maxXcoord -
                        (xTempCoords[0]*yTempCoords[1]-xTempCoords[1]*yTempCoords[0])) /
                        (xTempCoords[1]-xTempCoords[0]);
                double topLineX = (double)(-(xTempCoords[1]-xTempCoords[0])*maxYcoord -
                        (xTempCoords[0]*yTempCoords[1]-xTempCoords[1]*yTempCoords[0])) /
                        (yTempCoords[0]-yTempCoords[1]);
                double bottomLineX = (double)(-(xTempCoords[1]-xTempCoords[0])*minYcoord -
                        (xTempCoords[0]*yTempCoords[1]-xTempCoords[1]*yTempCoords[0])) /
                        (yTempCoords[0]-yTempCoords[1]);

                int minSectionX = Math.min(xTempCoords[0],xTempCoords[1]);
                int maxSectionX = Math.max(xTempCoords[0],xTempCoords[1]);
                int minSectionY = Math.min(yTempCoords[0],yTempCoords[1]);
                int maxSectionY = Math.max(yTempCoords[0],yTempCoords[1]);


                if( (leftLineY <= maxYcoord && leftLineY >= minYcoord && leftLineY <= maxSectionY && leftLineY >= minSectionY) ||
                        (rightLineY <= maxYcoord && rightLineY >= minYcoord && rightLineY <= maxSectionY && rightLineY >= minSectionY) ||
                        (topLineX <= maxXcoord && topLineX >= minXcoord && topLineX <= maxSectionX && topLineX >= minSectionX) ||
                        (bottomLineX <= maxXcoord && bottomLineX >= minXcoord && bottomLineX <= maxSectionX && bottomLineX >= minSectionX) ) {
                    newGraphicPic.add(temp);
                }
            }
            else {
                throw new Exception("Что-то непонятное(непонятная фигура)");
            }

            if(head == tail) break;
            temp = temp.getNext();
        }

        return newGraphicPic;
    }
    public GraphicPic hasSquareBiggerThanS(double s) {
        GraphicPic newGraphiPic = new GraphicPic();
        GraphicPicElement temp = this.head;
        while(temp != null) {
            if(temp.getFigure().getArea() > s)
                newGraphiPic.add(temp);
            if(head == tail) break;
            temp = temp.getNext();
        }
        return newGraphiPic;
    }
    public GraphicPic getByColor(Figure.Color[] colors) {
        GraphicPic newGraphicPic = new GraphicPic();
        GraphicPicElement temp = head;
        while(temp != null) {
            for(int i = 0; i < colors.length; i++) {
                if(colors[i].color.equals(temp.getFigure().getColor().color))
                    newGraphicPic.add(temp);
            }
            if(head == tail) break;
            temp = temp.getNext();
        }
        return newGraphicPic;
    }

    public static int getRandomInteger(int a, int b) {
        return (int)(Math.random()*(b-a+1))+a;
    }

    private void add(GraphicPicElement nGPEtemp) {
        GraphicPicElement nGPE = new GraphicPicElement(nGPEtemp.getFigure());
        if(head == null) {
            this.head = nGPE;
            this.tail = nGPE;
            this.head.setNext(this.tail);
        }
        else {
            this.tail.setNext(nGPE);
            this.tail = this.tail.getNext();
        }
    }

}