package pl.chemik.project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Algorithm3 {

    private String inputPath;
    private int numberOfTasks;
    private ArrayList<Task> tasks;
    private int globalCriterion;

    public Algorithm3() {
    }

    private void resetFields() {
//        this.globalCriterion = 999999999; //todo
        this.numberOfTasks = 0;
        this.tasks = new ArrayList<>();
    }

    private void readFile() {
        File file = new File(inputPath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        numberOfTasks = scanner.nextInt();
        int id = 1;
        while (scanner.hasNextInt()) {
            int p1 = scanner.nextInt();
            int p2 = scanner.nextInt();
            int p3 = scanner.nextInt();
            int d = scanner.nextInt();
            int w = scanner.nextInt();
            Task task = new Task(id, p1, p2, p3, d, w);
            tasks.add(task);
            id++;
        }
        scanner.close();
    }

    private void calculate() {

    }

    private void generateSolutionFile(int index, int size) {
        String filename = "project3/output/" + index + "_" + size + ".txt";
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(filename);
//            writer.write(globalCriterion + "\n");
//            for (Machine machine : machines) {
//                for (int taskId : machine.getTasksIDs()) {
//                    writer.write(taskId + " ");
//                }
//                writer.write('\n');
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void generateDummySolutionFile() {
        for (int size: Project3.sizesArray) {
            String filename = "project3/output/test_out/test_out_" + size + ".txt";
            FileWriter writer = null;
            try {
                writer = new FileWriter(filename);
                writer.write(0 + "\n");
                for (int i = 1; i <= size; i++) {
                    writer.write( i + " ");
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void runForIndexWithSize(int index, int size) {
        resetFields();
        this.inputPath = "project3/input/" + index + "/" + index + "_" + size + ".txt";
        readFile();
        long startTime = System.currentTimeMillis();
        calculate();
        long stopTime = System.currentTimeMillis();
        System.out.println("Size " + size + "   Criterion: " + globalCriterion + ", in time: " + (stopTime - startTime) + "ms");
        generateSolutionFile(index, size);
    }

    public void run(int index) {
        System.out.println("Algorithm Three just started for index: " + index);
        for (int size : Project3.sizesArray) {
            runForIndexWithSize(index, size);
        }
    }
}
