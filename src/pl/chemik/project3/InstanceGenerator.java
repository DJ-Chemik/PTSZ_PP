package pl.chemik.project3;

import pl.chemik.project1.Main;
import pl.chemik.project3.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class InstanceGenerator {

    private int MAX_SINGLE_P = 10;
    private int MAX_R_AFTER_P_FINISH = 10;
    private int MAX_W = 10;

    private Random random;
    private ArrayList<Task> tasks;

    public InstanceGenerator() {
        random = new Random();
        resetGeneratorFields();
    }

    private int nextInt(int from, int to) {
        return random.nextInt(to - from + 1) + from;
    }

    public void resetGeneratorFields() {
        tasks = new ArrayList<>();
    }

    private void generateOneInstance(int size) {
        for (int i = 0; i < size; i++) {
            Task task = new Task();
            task.setId(i + 1);
            int allPtimes = 0;
            ArrayList<Integer> processingTimes = new ArrayList<>();
            // Maszyny od 1 do 3
            for (int j = 0; j < 3; j++) {
                int p = nextInt(1, MAX_SINGLE_P);
                processingTimes.add(p);
                allPtimes += p;
            }
            task.setP(processingTimes);
            task.setD(allPtimes + nextInt(0, MAX_R_AFTER_P_FINISH));
            task.setW(nextInt(1, MAX_W));
            tasks.add(task);
        }
    }

    private void saveInstanceToFile(int size) {
        String savepath = "project3/generatedInstances/";
        String filename = "136809_" + size + ".txt";
        try {
            FileWriter writer = new FileWriter(savepath + filename);
            writer.write(size + "\n" );
            for (Task task : tasks) {
                writer.write(
                        task.getP().get(0) + " " +
                            task.getP().get(1) + " " +
                            task.getP().get(2) + " " +
                            task.getD() + " " +
                            task.getW() + "\n"
                );
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generate() {
        new ArrayList<Integer>(Arrays.asList(Main.sizesArray)).forEach(size -> {
            generateOneInstance(size);
            saveInstanceToFile(size);
            resetGeneratorFields();
        });
    }
}
