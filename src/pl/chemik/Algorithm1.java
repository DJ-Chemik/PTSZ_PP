package pl.chemik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class TimeWeight {
    private int time;
    private int weight;

    public TimeWeight() {
    }

    public TimeWeight(int time, int weight) {
        this.time = time;
        this.weight = weight;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

public class Algorithm1 {

    private int index;
    private int size;
    private String inputPath;
    //    private String inputPath = "";
    private String outputPath = "";

    private int globalCriterion = 0;
    private int globalTime = 0;
    private ArrayList<Task> tasks = new ArrayList<>();
    private int numberOfTasks;

    public Algorithm1() {
    }

    public void readFile() throws FileNotFoundException {
        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        numberOfTasks = scanner.nextInt();

        tasks = new ArrayList<>();
        int i = 1;
        while (scanner.hasNextInt()) {
            int p = scanner.nextInt();
            int r = scanner.nextInt();
            int d = scanner.nextInt();
            int w = scanner.nextInt();
            Task task = new Task(i, p, r, d, w);
            tasks.add(task);
            i++;
        }
        scanner.close();
    }

    private TimeWeight getTimeWeight(int time, Task task) {
        if (time < task.getR()) {
            time = task.getR();
        }
        time += task.getP();
        int weight = time > task.getD() ? task.getW() : 0;
        return new TimeWeight(time, weight);
    }

    private TimeWeight getTimeWeights(int time, Task left, Task right) {
        TimeWeight leftTW = getTimeWeight(time, left);
        TimeWeight rightTW = getTimeWeight(leftTW.getTime(), right);
        int resultWeight = leftTW.getWeight() + rightTW.getWeight();
        return new TimeWeight(rightTW.getTime(), resultWeight);
    }

    public void calculate() {
        for (int j = 0; j < numberOfTasks; ++j) {
            int time = globalTime;
            int criterion = globalCriterion;

            for (int k = j; k < numberOfTasks; ++k) {
                Task left = tasks.get(j);
                Task right = tasks.get(k);

                TimeWeight beforeSwap = getTimeWeights(time, left, right);
                TimeWeight afterSwap = getTimeWeights(time, right, left);

                if (afterSwap.getWeight() < beforeSwap.getWeight()) {
                    Collections.swap(tasks, j, k);
                }
            }

            Task firstTask = tasks.get(j);
            TimeWeight globals = getTimeWeight(globalTime, firstTask);

            globalTime = globals.getTime();
            globalCriterion += globals.getWeight();
        }
    }

    public void generateSolutionFile() {
        String filename = "filesAlg1/out_" + index + "_" + size + ".txt";
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            writer.write(globalCriterion + "\n");
            for (int i = 0; i < size; i++) {
                writer.write(tasks.get(i).getId() + " ");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(int index, int size) {
        this.index = index;
        this.size = size;
        this.inputPath = inputPath = "filesInput/" + index + "/" + index + "_" + size + ".txt";
        try {
            readFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // START TIME
        calculate();
        //STOP TIME
        System.out.println("Criterion: " + globalCriterion + ", time: " + globalTime);
        generateSolutionFile();


    }
}
