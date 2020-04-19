package Semestr;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;


class GraphicPicTest {

    protected ByteArrayOutputStream output;
    private PrintStream old;
    private static String path = "C:\\Users\\acer\\Desktop\\Exam\\src\\Semestr\\tests";
    private String[] inputFileNames = {"test1.txt", "test2.txt", "test3.txt", "test4.txt"};


    @BeforeEach
    public void setUpStreams() {
        old = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output)); // Перенаправление потока считывания с консоли на свой
    }
    @AfterEach
    public void cleanUpStreams() {
        System.setOut(old);
    }

    @Test
    void show() throws Exception {
        String[] outputFileNames = {"output1Show.txt","output2Show.txt","output3Show.txt", "output4Show.txt"};
        for(int i = 0; i < inputFileNames.length; i++) {
            output.reset(); // убираем все символы из потока, которые были добавлены на предыдущем шаге

            GraphicPic gp = new GraphicPic(path+"\\input\\"+inputFileNames[i]);
            gp.show();

            FileReader fr = new FileReader(path+"\\output\\show()\\"+outputFileNames[i]);
            Scanner scan = new Scanner(fr);

            String template = "";
            while(scan.hasNextLine()) {
                template += scan.nextLine()+"\n";
            }

            Assert.assertEquals(output.toString().strip(), template.strip());

            fr.close();
        }
    }

    @Test
    void insertWithoutRandom() throws Exception {
        String[] outputFileNames = {"output1Insert.txt", "output2Insert.txt",
                "output3Insert.txt", "output4Insert.txt"};
        for(int i = 0; i < inputFileNames.length; i++) {
            output.reset(); // убираем все символы из потока, которые были добавлены на предыдущем шаге

            GraphicPic gp = new GraphicPic(path+"\\input\\"+inputFileNames[i]);
            gp.insert(new Figure("circle", 0, 0, 1, 0,"black"));
            gp.show();

            FileReader fr = new FileReader(path+"\\output\\insert()\\"+outputFileNames[i]);
            Scanner scan = new Scanner(fr);

            String template = "";
            while(scan.hasNextLine()) {
                template += scan.nextLine()+"\n";
            }

            Assert.assertEquals(output.toString().strip(), template.strip());

            fr.close();
        }
    }

    @Test
    void delete() throws Exception {
        String[] outputFileNames = {"output1Delete.txt", "output2Delete.txt", "output3Delete.txt", "output4Delete.txt",};
        for(int i = 0; i < inputFileNames.length; i++) {
            output.reset(); // убираем все символы из потока, которые были добавлены на предыдущем шаге

            GraphicPic gp = new GraphicPic(path+"\\input\\"+inputFileNames[i]);
            gp.delete(Figure.Type.RECTANGLE);
            gp.show();

            FileReader fr = new FileReader(path+"\\output\\delete()\\"+outputFileNames[i]);
            Scanner scan = new Scanner(fr);

            String template = "";
            while(scan.hasNextLine()) {
                template += scan.nextLine()+"\n";
            }

            Assert.assertEquals(output.toString().strip(), template.strip());

            fr.close();
        }
    }

    @Test
    void commonWith() throws Exception {
        String[] outputFileNames = {"output1CommonWith.txt", "output2CommonWith.txt", "output3CommonWith.txt", "output4CommonWith.txt"};
        for(int i = 0; i < inputFileNames.length; i++) {
            output.reset(); // убираем все символы из потока, которые были добавлены на предыдущем шаге

            GraphicPic gp = new GraphicPic(path+"\\input\\"+inputFileNames[i]);
            gp = gp.commonWith(new Figure("rectangle", 4,9,0,8,"red"));
            gp.show();

            FileReader fr = new FileReader(path+"\\output\\commonWith()\\"+outputFileNames[i]);
            Scanner scan = new Scanner(fr);

            String template = "";
            while(scan.hasNextLine()) {
                template += scan.nextLine()+"\n";
            }

            Assert.assertEquals(output.toString().strip(), template.strip());

            fr.close();
        }
    }

    @Test
    void hasSquareBiggerThanS() throws Exception {
        String[] outputFileNames = {"output1HasSquareBiggerThanS.txt", "output2HasSquareBiggerThanS.txt",
                "output3HasSquareBiggerThanS.txt", "output4HasSquareBiggerThanS.txt"};
        for(int i = 0; i < inputFileNames.length; i++) {
            output.reset(); // убираем все символы из потока, которые были добавлены на предыдущем шаге

            GraphicPic gp = new GraphicPic(path+"\\input\\"+inputFileNames[i]);
            gp = gp.hasSquareBiggerThanS(2);
            gp.show();

            FileReader fr = new FileReader(path+"\\output\\hasSquareBiggerThanS()\\"+outputFileNames[i]);
            Scanner scan = new Scanner(fr);

            String template = "";
            while(scan.hasNextLine()) {
                template += scan.nextLine()+"\n";
            }

            Assert.assertEquals(output.toString().strip(), template.strip());

            fr.close();
        }
    }

    @Test
    void getByColor() throws Exception {
        String[] outputFileNames = {"output1GetByColor.txt", "output2GetByColor.txt",
                "output3GetByColor.txt", "output4GetByColor.txt"};
        for(int i = 0; i < inputFileNames.length; i++) {
            output.reset(); // убираем все символы из потока, которые были добавлены на предыдущем шаге

            GraphicPic gp = new GraphicPic(path+"\\input\\"+inputFileNames[i]);
            gp = gp.getByColor(new Figure.Color[]{Figure.Color.GREEN, Figure.Color.RED, Figure.Color.BLUE});
            gp.show();

            FileReader fr = new FileReader(path+"\\output\\getByColor()\\"+outputFileNames[i]);
            Scanner scan = new Scanner(fr);

            String template = "";
            while(scan.hasNextLine()) {
                template += scan.nextLine()+"\n";
            }

            Assert.assertEquals(output.toString().strip(), template.strip());

            fr.close();
        }
    }

}