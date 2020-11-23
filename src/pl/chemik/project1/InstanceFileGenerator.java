package pl.chemik.project1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class InstanceFileGenerator {
    private ArrayList<Integer> instanceSizes;

    public InstanceFileGenerator() {
        instanceSizes = new ArrayList<>();
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
    }

    public void generateFiles() {
        InstanceGenerator instanceGenerator = new InstanceGenerator();
        instanceSizes.forEach(instanceSize -> {
            ArrayList<Task> tasks = instanceGenerator.generateInstances(instanceSize);
            String filename = "project1/instances/136809_" + instanceSize + ".txt";
            try {
                FileWriter writer = new FileWriter(filename);
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
