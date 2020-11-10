package pl.chemik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class CostRelation {
    private int time;
    private int cost;

    public CostRelation(int time, int cost) {
        this.time = time;
        this.cost = cost;
    }

    public int getTime() {
        return time;
    }

    public int getCost() {
        return cost;
    }
}

public class Algorithm1 {

    private int index;
    private int size;
    private String inputPath;

    private int globalCriterion = 0;
    private int globalTime = 0;
    private ArrayList<Task> tasks = new ArrayList<>();
    private int numberOfTasks;

    public Algorithm1() {
    }

    public void readFile() {
        File file = new File(inputPath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        numberOfTasks = scanner.nextInt();

        this.tasks = new ArrayList<>();
        int i = 1;
        while (scanner.hasNextInt()) {
            int p = scanner.nextInt();
            int r = scanner.nextInt();
            int d = scanner.nextInt();
            int w = scanner.nextInt();
            Task task = new Task(i, p, r, d, w);
            this.tasks.add(task);
            i++;
        }
        scanner.close();
    }

    private CostRelation checkTaskCost(int time, Task task) {
        int r = task.getR();
        if (time < r) {
            time = r;
        }
        time += task.getP();
        int cost = 0;
        if (time > task.getD()) {
            cost = task.getW();
        }
        return new CostRelation(time, cost);
    }

    private CostRelation checkOrderingCost(int time, Task firstTask, Task secondTask) {
        CostRelation firstTaskCost = checkTaskCost(time, firstTask);
        CostRelation secondTaskCost = checkTaskCost(firstTaskCost.getTime(), secondTask);
        int resultCost = firstTaskCost.getCost() + secondTaskCost.getCost();
        return new CostRelation(secondTaskCost.getTime(), resultCost);
    }

    private void displayAllIterationOfOrderingInConsole() {
        for (Task t : tasks) {
            System.out.print(t.getId() + "  ");
        }
        System.out.println("----------------------------");
    }

    public void calculate() {
        for (int i = 0; i < numberOfTasks; i++) {
            int time = globalTime;

            for (int j = i + 1; j < numberOfTasks; j++) {
                Task left = tasks.get(i);
                Task right = tasks.get(j);

                CostRelation beforeSwap = checkOrderingCost(time, left, right);
                CostRelation afterSwap = checkOrderingCost(time, right, left);

                if (afterSwap.getCost() < beforeSwap.getCost()) {
                    Collections.swap(tasks, i, j);
                }

//                displayAllIterationOfOrderingInConsole();
            }

            Task taskToAppend = tasks.get(i);
            CostRelation result = checkTaskCost(globalTime, taskToAppend);

            globalTime = result.getTime();
            globalCriterion += result.getCost();
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
        this.globalTime = 0;
        this.globalCriterion = 0;

        readFile();
        // START TIME
        long startTime = System.currentTimeMillis();
        calculate();
        long stopTime = System.currentTimeMillis();
        //STOP TIME
//        System.out.println("REAL TIME: " + (stopTime - startTime) + "ms");
//        System.out.println("Criterion: " + globalCriterion + ", in time: " + globalTime);
        System.out.println("Size " + size + "   Criterion: " + globalCriterion + ", in time: " + (stopTime - startTime) + "ms");
        generateSolutionFile();
    }

    public void runAlgorithmOneForIndex(int index) {
        System.out.println("Algorithm One just started for index: " + index);
        for (int size : Main.sizesArray) {
            run(index, size);
        }
    }
}
