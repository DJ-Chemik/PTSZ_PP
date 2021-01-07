package pl.chemik.project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Validator {
    private int readedCriterion;
    private int calculatedCriterion;
    private ArrayList<Float> speadOfMachines;
    private ArrayList<Task> tasks;

    private ArrayList<ArrayList<Integer>> tasksIdsInMachine;

    public Validator() {
        reset();
    }

    private void reset() {
        readedCriterion = 0;
        calculatedCriterion = 0;
        tasks = new ArrayList<>();
        speadOfMachines = new ArrayList<>();
        tasksIdsInMachine = new ArrayList<>();
        tasksIdsInMachine.add(new ArrayList<>());
        tasksIdsInMachine.add(new ArrayList<>());
        tasksIdsInMachine.add(new ArrayList<>());
        tasksIdsInMachine.add(new ArrayList<>());
        tasksIdsInMachine.add(new ArrayList<>());
    }

    private void readInputFile(int index, int size) throws FileNotFoundException {
        String filepath = "project2/input/" + index + "/" + index + "_" + size + ".txt";
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        int numberOfTasks = scanner.nextInt();
        if (numberOfTasks != size) {
            System.out.println("Odczytana wartość ilosci zadań niezgodne z tą co powinna być!");
            throw new FileNotFoundException();
        }

        for (int i = 0; i < 5; i++) {
            String speed = scanner.next();
            float speedFloat = Float.parseFloat(speed);
            speadOfMachines.add(speedFloat);
        }

        int i = 1;
        while (scanner.hasNextInt()) {
            int p = scanner.nextInt();
            int r = scanner.nextInt();
            Task task = new Task(i, p, r);
            tasks.add(task);
            i++;
        }
        scanner.close();
    }

    private void readOneSolution(int index, int size, boolean isTestFile) throws FileNotFoundException {
        String path = "project2/output/";
        String filename = index + "_" + size + ".txt";
        if (isTestFile) {
            path = "project2/output/test_out";
            filename = "/test_out" + "_" + size + ".txt";
        }
        File file = new File(path + filename);
        Scanner scanner = new Scanner(file);
        this.readedCriterion = scanner.nextInt();
        scanner.nextLine(); // to read all rubbish signs
        int actualMachine = 0;
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            for (String sign : line) {
                int id = Integer.parseInt(sign);
                tasksIdsInMachine.get(actualMachine).add(id);
            }
            actualMachine++;
        }
        scanner.close();
    }

    private void calculateCriterion(int index, int size, boolean isTestFile) {
        int criterion = 0;
        int machineNumber = 0;
        for (ArrayList<Integer> machine : tasksIdsInMachine) {
            float time = 0;
            for (int taskId : machine) {
                Task task = tasks.stream().filter(task1 -> task1.getId() == taskId).findFirst().get();
                if (time < task.getR()) {
                    time = task.getR();
                }
                time += task.getP() / speadOfMachines.get(machineNumber);
                criterion += (time - task.getR());
            }
            machineNumber++;
        }


        //TODO Takie dziwne parsowania żeby zgadzało się z wynikami ludzi którzy pisali winnych językach. Mi zawsze wychodziło o 1 mniej
        calculatedCriterion = (int) (float) Math.ceil(criterion / (float) size);
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
        for (int size : Project2.sizesArray) {
            runValidationForIndexWithSize(index, size, isTestFile);
        }
    }
}
