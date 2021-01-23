package pl.chemik.project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Validator {

    private int readedCriterion;
    private int calculatedCriterion;
    private ArrayList<Task> tasks;
    private ArrayList<Integer> scheduleIds;

    public Validator() {
        reset();
    }

    private void reset() {
        readedCriterion = 0;
        calculatedCriterion = 0;
        scheduleIds = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    private void readInputFile(int index, int size) throws FileNotFoundException {
        String filepath = "project3/input/" + index + "/" + index + "_" + size + ".txt";
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        int numberOfTasks = scanner.nextInt();
        if (numberOfTasks != size) {
            System.out.println("Odczytana wartość ilosci zadań niezgodne z tą co powinna być!");
            throw new FileNotFoundException();
        }
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

    private void readOneSolution(int index, int size, boolean isTestFile) throws FileNotFoundException {
        String path = "project3/output/";
        String filename = index + "_" + size + ".txt";
        if (isTestFile) {
            path = "project3/output/test_out";
            filename = "/test_out" + "_" + size + ".txt";
        }
        File file = new File(path + filename);
        Scanner scanner = new Scanner(file);
        this.readedCriterion = (int)Float.parseFloat(scanner.next());
        scanner.nextLine(); // to read all rubbish signs
        int actualMachine = 0;
        while (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            scheduleIds.add(id);
        }
        scanner.close();
    }

    private void calculateCriterion(int index, int size, boolean isTestFile) {

        float criterion = 0;
        float weightSum = 0;
        ArrayList<Integer> machinesTimes = new ArrayList<>();
        machinesTimes.add(0);
        machinesTimes.add(0);
        machinesTimes.add(0);

        for (int i = 0; i < size; i++) {
            int taskNumber = scheduleIds.get(i) - 1;

            for (int j = 0; j < 3; j++) {
                if (j > 0) {
                    machinesTimes.set(j, Math.max(machinesTimes.get(j), machinesTimes.get(j-1))) ;
                }
                machinesTimes.set(j, machinesTimes.get(j) + tasks.get(taskNumber).getP().get(j));
            }
            criterion += tasks.get(taskNumber).getW() * Math.max(0, machinesTimes.get(2) - tasks.get(i).getD());
            weightSum += tasks.get(taskNumber).getW();
        }
        calculatedCriterion = (int) (float) Math.ceil(criterion / weightSum);

        if (isTestFile) {
            System.out.println("Kryterium dla " + index + " (" + size + "): " + calculatedCriterion);
        } else {
            if (calculatedCriterion - readedCriterion == 1 || calculatedCriterion - readedCriterion == -1) {
                System.out.println("Kryterium dla " + index + " (" + size + "): " + calculatedCriterion + "  | odczytane: " + readedCriterion);
            } else if (calculatedCriterion != readedCriterion) {
                System.out.println("------------------------------------------------------------");
                System.out.println(">>> Wyliczona wartość kryterium niezgodna z odczytana z pliku!!!");
                System.out.println(">>> Ilośc tasków: " + size + "<<<");
                System.out.println(">>> Odczytana: " + readedCriterion);
                System.out.println(">>> Wyliczona: " + calculatedCriterion);
                System.out.println("------------------------------------------------------------");
            } else {
                System.out.println("Kryterium dla " + index + " (" + size + "): " + calculatedCriterion);
            }
        }
    }

    private void checkSolution(int index, int size, boolean isTestFile) throws FileNotFoundException {
        readInputFile(index, size);
        readOneSolution(index, size, isTestFile);
        calculateCriterion(index, size, isTestFile);
    }

    public void runValidationForIndexWithSize(int index, int size, boolean isTestFile) {
        reset();
        try {
            checkSolution(index, size, isTestFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void runValidation(int index, boolean isTestFile) {
        for (int size : Project3.sizesArray) {
            runValidationForIndexWithSize(index, size, isTestFile);
        }
    }
}
