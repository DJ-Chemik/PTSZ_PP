package pl.chemik.project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Algorithm3 {

    private enum SORT_TASK {
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H,
        I,
        J,
        K,
        L,
        M,
        N,
        O,
        P,
        Q,
        R,
        S,
        T,
        U,
        V,
        W,
        X,
        Y,
        Z
    }

    private String inputPath;
    private int numberOfTasks;
    private ArrayList<Task> tasks;
    private float globalCriterion;

    public Algorithm3() {
    }

    private void resetFields() {
        this.globalCriterion = 999999999;
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

    private ArrayList<Task> sortTasks(SORT_TASK sortOption) {

        ArrayList<Task> tmp = new ArrayList<>();
        tmp.addAll(tasks);

        if (sortOption == SORT_TASK.A) {
            tmp.sort((o1, o2) -> {
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = o1.getD() / w1;
                int result2 = o2.getD() / w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.B) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = sumP1 / w1;
                int result2 = sumP2 / w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.C) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = 2 * sumP1 / w1;
                int result2 = 2 * sumP2 / w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.D) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int result1 = sumP1 - o1.getW();
                int result2 = sumP2 - o2.getW();
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.E) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int result1 = 2 * sumP1 - o1.getW();
                int result2 = 2 * sumP2 - o2.getW();
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.F) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int result1 = sumP1 + o1.getD() - 2 * o1.getW();
                int result2 = sumP2 + o2.getD() - 2 * o1.getW();
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.G) {
            tmp.sort((o1, o2) -> {
//                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
//                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int result1 = o1.getD() - o1.getW();
                int result2 = o2.getD() - o1.getW();
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.H) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = sumP1 + o1.getD() / w1;
                int result2 = sumP2 + o2.getD() / w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.I) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0);// + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0);// + o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = sumP1 + o1.getD() / w1;
                int result2 = sumP2 + o2.getD() / w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.J) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + 2 * o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + 2 * o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = sumP1 + o1.getD() / w1;
                int result2 = sumP2 + o2.getD() / w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.K) {
            tmp.sort((o1, o2) -> {
                int sumP1 = 2 * o1.getP().get(0) + 3 * o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = 2 * o2.getP().get(0) + 3 * o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = sumP1 + o1.getD() / w1;
                int result2 = sumP2 + o2.getD() / w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.L) {
            tmp.sort((o1, o2) -> {
                int sumP1 = 2 * o1.getP().get(0) + 2 * o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = 2 * o2.getP().get(0) + 2 * o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = sumP1 + o1.getD() / w1;
                int result2 = sumP2 + o2.getD() / w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.M) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + 3 * o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + 3 * o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = sumP1 + o1.getD() / w1;
                int result2 = sumP2 + o2.getD() / w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.N) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + 3 * o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + 3 * o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = sumP1 + 3 * o1.getD() / w1;
                int result2 = sumP2 + 3 * o2.getD() / w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.O) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = 2 * sumP1 + 2 * o1.getD() - 3 * w1;
                int result2 = 2 * sumP2 + 2 * o2.getD() - 3 * w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.P) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = sumP1 + w1;
                int result2 = sumP2 + w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.Q) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = sumP1 / o1.getD() - w1;
                int result2 = sumP2 / o2.getD() - w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.R) {
            tmp.sort((o1, o2) -> {
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = o1.getP().get(0) - w1;
                int result2 = o2.getP().get(0) - w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.S) {
            tmp.sort((o1, o2) -> {
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = o1.getP().get(0) - 2 * w1;
                int result2 = o2.getP().get(0) - 2 * w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.T) {
            tmp.sort((o1, o2) -> {
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = o1.getP().get(1) - 3 * w1;
                int result2 = o2.getP().get(1) - 3 * w2;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.U) {
            tmp.sort((o1, o2) -> {
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = w2;
                int result2 = w1;
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.V) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = sumP1 * o1.getD() / (w1 * w1);
                int result2 = sumP2 * o2.getD() / (w2 * w2);
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.W) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = 2 * sumP1 * o1.getD() / (w1 * w1);
                int result2 = 2 * sumP2 * o2.getD() / (w2 * w2);
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.X) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = sumP1 * sumP1 / (w1 * w1);
                int result2 = sumP2 * sumP2 / (w2 * w2);
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.Y) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = (sumP1 * sumP1) * o1.getD() / (w1 * w1);
                int result2 = (sumP2 * sumP2) * o2.getD() / (w2 * w2);
                return result1 - result2;
            });
        }

        if (sortOption == SORT_TASK.Z) {
            tmp.sort((o1, o2) -> {
                int sumP1 = o1.getP().get(0) + o1.getP().get(1) + o1.getP().get(2);
                int sumP2 = o2.getP().get(0) + o2.getP().get(1) + o2.getP().get(2);
                int w1 = o1.getW() != 0 ? o1.getW() : -1;
                int w2 = o2.getW() != 0 ? o2.getW() : -1;
                int result1 = (sumP1 + o1.getW()) / (w1 * w1);
                int result2 = (sumP2 + o2.getW()) / (w2 * w2);
                return result1 - result2;
            });
        }

        return tmp;
    }

    private float calculateCriterion(List<Task> sortedTasks) {
        float criterion = 0;
        float weightSum = 0;
        ArrayList<Integer> machinesTimes = new ArrayList<>();
        machinesTimes.add(0);
        machinesTimes.add(0);
        machinesTimes.add(0);

        ArrayList<Integer> sortedIds = new ArrayList<>();
        for (Task task : sortedTasks) {
            sortedIds.add(task.getId());
        }
        for (int i = 0; i < sortedTasks.size(); i++) {
            int taskNumber = sortedIds.get(i) - 1;

            for (int j = 0; j < 3; j++) {
                if (j > 0) {
                    machinesTimes.set(j, Math.max(machinesTimes.get(j), machinesTimes.get(j - 1)));
                }
                machinesTimes.set(j, machinesTimes.get(j) + tasks.get(taskNumber).getP().get(j));
            }
            criterion += tasks.get(taskNumber).getW() * Math.max(0, machinesTimes.get(2) - tasks.get(i).getD());
            weightSum += tasks.get(taskNumber).getW();
        }
        criterion = (int) (float) Math.ceil(criterion / weightSum);
        return criterion;
    }

    private void calculate() {
        ArrayList<Task> sortedTasks = null;
        float bestCriterion = this.globalCriterion;
        ArrayList<Task> bestSolution = null;
        for (Algorithm3.SORT_TASK sortType : Algorithm3.SORT_TASK.values()) {
            sortedTasks = sortTasks(sortType);
            float newResult = calculateCriterion(sortedTasks);
            if (newResult < bestCriterion) {
                bestSolution = new ArrayList<>();
                bestSolution.addAll(sortedTasks);
                bestCriterion = newResult;
            }
        }
        this.globalCriterion = bestCriterion;
        this.tasks = bestSolution;
    }

    private void generateSolutionFile(int index, int size) {
        String filename = "project3/output/" + index + "_" + size + ".txt";
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            writer.write(globalCriterion + "\n");
            for (Task task : tasks) {
                writer.write(task.getId() + " ");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateDummySolutionFile() {
        for (int size : Project3.sizesArray) {
            String filename = "project3/output/test_out/test_out_" + size + ".txt";
            FileWriter writer = null;
            try {
                writer = new FileWriter(filename);
                writer.write(0 + "\n");
                for (int i = 1; i <= size; i++) {
                    writer.write(i + " ");
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void runForIndexWithSize(int index, int size) {
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
