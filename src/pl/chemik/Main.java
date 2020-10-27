package pl.chemik;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> instanceSizes = new ArrayList<>();
        instanceSizes.add(50);
        instanceSizes.add(100);
        instanceSizes.add(150);
        instanceSizes.add(200);
        instanceSizes.add(250);
        instanceSizes.add(300);
        instanceSizes.add(350);
        instanceSizes.add(400);
        instanceSizes.add(450);
        instanceSizes.add(500);

        Generator generator = new Generator();
        instanceSizes.forEach(instanceSize -> {
            ArrayList<Task> tasks = generator.generateInstances(instanceSize);
            String savePath = "";
            String filename = "136809_" + instanceSize + ".txt";
            try {
                FileWriter writer = new FileWriter(savePath + filename);
                writer.write(instanceSize + "\n" );
                for (Task task : tasks) {
                    writer.write(task.getP() + " " + task.getR() + " " + task.getD() + " " + task.getW() + "\n");
                }
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
