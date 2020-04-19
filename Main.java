package Semestr;


import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("Созданный список GraphicPic: ");
        GraphicPic graphicPic = new GraphicPic("C:\\Users\\acer\\Desktop\\Exam\\src\\Semestr\\tests\\input\\test1.txt");
        graphicPic.show();

        System.out.println("--------------------------------------");

        System.out.println("Результат метода insert() с изменением одного из текущих элементов");
        Figure z = new Figure("circle", 3,4,5,0, "yellow");
        graphicPic.insert(z);
        graphicPic.show();

        System.out.println("***");
        System.out.println("Результат метода insert() с добавлением нового элемента");
        System.out.println("***");
        Figure s = new Figure("circle", 0, 0, 1, 0,"black");
        graphicPic.insert(s);
        graphicPic.show();

        System.out.println("--------------------------------------");

        System.out.println("Результат метода hasSquareBiggerThanS()");
        GraphicPic second = graphicPic.hasSquareBiggerThanS(2);
        second.show();

        System.out.println("--------------------------------------");

        System.out.println("Результат метода getByColor()");
        GraphicPic third = graphicPic.getByColor(new Figure.Color[]{Figure.Color.GREEN, Figure.Color.RED, Figure.Color.BLUE});
        third.show();

        System.out.println("--------------------------------------");

        System.out.println("Результат метода delete()");
        graphicPic.delete(Figure.Type.RECTANGLE);
        graphicPic.show();

        System.out.println("--------------------------------------");

        System.out.println("Результат метода commonWith()");
        GraphicPic fourth = graphicPic.commonWith(new Figure("rectangle", 4,9,0,8,"red"));
        fourth.show();

    }

}
