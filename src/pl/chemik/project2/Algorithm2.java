package pl.chemik.project2;

import pl.chemik.project1.Main;

public class Algorithm2 {

    private int globalCriterion;

    public Algorithm2() {
        resetFields();
    }

    private void resetFields() {
        this.globalCriterion = 0;
    }

    private void readFile() {

    }

    private void calculate() {

    }

    private void generateSolutionFile() {

    }

    private void runForIndexWithSize(int index, int size) {
        resetFields();
        readFile();
        long startTime = System.currentTimeMillis();
        calculate();
        long stopTime = System.currentTimeMillis();
        System.out.println("Size " + size + "   Criterion: " + globalCriterion + ", in time: " + (stopTime - startTime) + "ms");
        generateSolutionFile();
    }

    public void run(int index) {
        System.out.println("Algorithm Two just started for index: " + index);
        for (int size : Main.sizesArray) {
            runForIndexWithSize(index, size);
        }
    }

}
