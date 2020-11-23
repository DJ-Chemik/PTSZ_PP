package pl.chemik.project2;

import pl.chemik.project1.Main;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class InstanceGenerator {

    private Random random;
    private ArrayList<Float> machines;
    private ArrayList<Task> tasks;

    public InstanceGenerator() {
        random = new Random();
        resetGeneratorFields();
    }

    public void resetGeneratorFields() {
        machines = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    private float nextFloat(float from, float to) {
        float number = from + random.nextFloat() * (to - from);
        return Math.round(number * 100.0f) / 100.0f;
    }

    private int nextInt(int from, int to) {
        return random.nextInt(to - from + 1) + from;
    }

    private void randomMachinesSpeeds() {
        machines.add(1.0f);
        machines.add(nextFloat(0.5f, 0.9f));
        machines.add(nextFloat(0.25f, 0.8f));
        machines.add(nextFloat(0.25f, 0.5f));
        machines.add(0.25f);
    }

    private void generateOneInstance(int size) {
        for (int i = 0; i < size; i++) {
            Task task = new Task();
            task.setId(i + 1);
            task.setP(nextInt(1, 99));
            task.setR(nextInt(1, size));
            tasks.add(task);
        }
    }

    private void saveInstanceToFile(int size) {
        String savepath = "project2/generatedInstances/";
        String filename = "136809_" + size + ".txt";
        try {
            FileWriter writer = new FileWriter(savepath + filename);
            writer.write(size + "\n" );
            for (Float machine : machines) {
                writer.write(machine + " ");
            }
            writer.write("\n");
            for (Task task : tasks) {
                writer.write(task.getP() + " " + task.getR() + "\n");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generate() {
        new ArrayList<Integer>(Arrays.asList(Main.sizesArray)).forEach(size -> {
            randomMachinesSpeeds();
            generateOneInstance(size);
            saveInstanceToFile(size);
            resetGeneratorFields();
        });
    }
}
