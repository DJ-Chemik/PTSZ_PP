package pl.chemik.project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Validator {
    private int readedCriterion;
    private int calculatedCriterion;
    private ArrayList<Integer> tasksIdsInMachine1;
    private ArrayList<Integer> tasksIdsInMachine2;
    private ArrayList<Integer> tasksIdsInMachine3;
    private ArrayList<Integer> tasksIdsInMachine4;
    private ArrayList<Integer> tasksIdsInMachine5;

    public Validator() {
        reset();
    }

    private void reset() {
        readedCriterion = 0;
        calculatedCriterion = 0;
        tasksIdsInMachine1 = new ArrayList<>();
        tasksIdsInMachine2 = new ArrayList<>();
        tasksIdsInMachine3 = new ArrayList<>();
        tasksIdsInMachine4 = new ArrayList<>();
        tasksIdsInMachine5 = new ArrayList<>();

    }

    private void readOneSolution(int index, int size, boolean isTestFile) throws FileNotFoundException {
        String path = "project2/output/" + index;
        String filename = "/" + index + "_" + size + ".txt";
        if (isTestFile) {
            path = "project2/output/test_out";
            filename = "/test_out" + "_" + size + ".txt";
        }
        File file = new File(path + filename);
        Scanner scanner = new Scanner(file);
        this.readedCriterion = scanner.nextInt();
        scanner.nextLine(); // to read all rubbish signs
        int actualMachine = 1;
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            for (String sign : line) {
                int id = Integer.parseInt(sign);
                if (actualMachine == 1) {
                    tasksIdsInMachine1.add(id);
                } else if (actualMachine == 2) {
                    tasksIdsInMachine2.add(id);
                } else if (actualMachine == 3) {
                    tasksIdsInMachine3.add(id);
                } else if (actualMachine == 4) {
                    tasksIdsInMachine4.add(id);
                } else if (actualMachine == 5) {
                    tasksIdsInMachine5.add(id);
                }
            }
            actualMachine++;
        }
        scanner.close();
    }

    private void checkSolution() {

    }

    public void runValidation() {
        try {
            readOneSolution(0, 50, true);
            tasksIdsInMachine3.forEach(task -> System.out.println(task));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
