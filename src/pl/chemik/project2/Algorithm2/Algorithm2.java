package pl.chemik.project2.Algorithm2;

import pl.chemik.project2.Project2;
import pl.chemik.project2.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Algorithm2 {

    private enum SORT_TASK {
        ByReadyTime,
        ByProcessingTime,
        ByEndTime,
        ByEndTimeWithDoubleR,
        ByEndTimeWithDoubleP,
    }

    private int globalCriterion;
    private String inputPath;
    private ArrayList<Machine> machines;
    private ArrayList<Task> tasks;
    private int numberOfTasks;

    public Algorithm2() {
        resetFields();
    }

    private void resetFields() {
        this.globalCriterion = 999999999;
        this.numberOfTasks = 0;
        machines = new ArrayList<>();
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

        for (int i = 0; i < 5; i++) {
            String speed = scanner.next();
            float speedFloat = Float.parseFloat(speed);
            Machine machine = new Machine(i, speedFloat);
            machines.add(machine);
        }

        int id = 1;
        while (scanner.hasNextInt()) {
            int p = scanner.nextInt();
            int r = scanner.nextInt();
            Task task = new Task(id, p, r);
            tasks.add(task);
            id++;
        }
        scanner.close();
    }

    private void displaymachines() {
        machines.forEach(machine -> System.out.printf(machine.getNumber() + "(" + machine.getSpeed() + "), "));
        System.out.println("");
    }

    private void sortTasks(SORT_TASK sortOption) {
        if (sortOption == SORT_TASK.ByReadyTime) {
            tasks.sort((o1, o2) -> {
                if (o1.getR() == o2.getR()) {
                    return o1.getP() - o2.getP();
                }
                return o1.getR() - o2.getR();
            });
        }
        if (sortOption == SORT_TASK.ByProcessingTime) {
            tasks.sort((o1, o2) -> {
                if (o1.getP() == o2.getP()) {
                    return o1.getR() - o2.getR();
                }
                return o1.getP() - o2.getP();
            });
        }
        if (sortOption == SORT_TASK.ByEndTime) {
            tasks.sort((o1, o2) -> {
                int end1 = o1.getR() + o1.getP();
                int end2 = o2.getR() + o2.getP();
                return end1 - end2;
            });
        }
        if (sortOption == SORT_TASK.ByEndTimeWithDoubleR) {
            tasks.sort((o1, o2) -> {
                int test1 = o1.getR()*2 + o1.getP();
                int test2 = o2.getR()*2 + o2.getP();
                return test1 - test2;
            });
        }
        if (sortOption == SORT_TASK.ByEndTimeWithDoubleP) {
            tasks.sort((o1, o2) -> {
                int test1 = o1.getR() + o1.getP()*2;
                int test2 = o2.getR() + o2.getP()*2;
                return test1 - test2;
            });
        }
    }

    private void sortMachinesBySpeed() {
        machines.sort((o1, o2) -> {
            int speed1 = (int) (o1.getSpeed() * 100);
            int speed2 = (int) (o2.getSpeed() * 100);
            return speed1 - speed2;
        });
    }

    private void sortMachinesById() {
        machines.sort(Comparator.comparingInt(Machine::getNumber));
    }

    private void calculate() {
        sortMachinesBySpeed();

        ArrayList<Machine> emptyMachines = new ArrayList<>();
        for (Machine machine : machines) {
            emptyMachines.add(machine.copyMachine());
        }

        for (SORT_TASK sortType : SORT_TASK.values()) {
            ArrayList<Machine> localMachines = new ArrayList<>();
            for (Machine emptyMachine : emptyMachines) {
                localMachines.add(emptyMachine.copyMachine());
            }

            sortTasks(sortType);
            int numberOfScheduledTasks = 0;
            boolean shouldSchedule = true;
            float time = 0;
            ArrayList<Task> readyTasks = new ArrayList<>();

            while (shouldSchedule) {
                // Update Ready tasks
                for (int i = numberOfScheduledTasks; i < tasks.size() && tasks.get(i).getR() <= time; i++) {
                    readyTasks.add(tasks.get(i));
                    numberOfScheduledTasks++;
                }

                // Add new task to each machine
                for (int machineID = 0; machineID < localMachines.size(); machineID++) {
                    Machine machine = localMachines.get(machineID);
                    boolean isMachineReady = machine.getTime() <= time + 1;
                    if (!isMachineReady || readyTasks.size() == 0) {
                        continue;
                    }

                    // Add task to machine
                    Task nextTask = readyTasks.get(0);
                    machine.addTaskToSchedule(nextTask.getId());
                    if (machine.getTime() < nextTask.getR()) {
                        machine.setTime(nextTask.getR());
                    }
                    float newMachineTime = nextTask.getP() / machine.getSpeed();
                    machine.addTime(newMachineTime);
                    machine.addSumScheduleTime(machine.getTime() - nextTask.getR());
                    // Delete task from ready tasks (because of it is assigned to machine)
                    readyTasks.remove(nextTask);
                }

                // Check is algorithm finished
                shouldSchedule = numberOfScheduledTasks < tasks.size() || readyTasks.size() > 0;
                time++;
            }

            float criterion = 0;
            for (int machineId = 0; machineId < localMachines.size(); machineId++) {
                criterion += localMachines.get(machineId).getSummaryTime();
            }
            int resultLocalCriterion = (int) (float) Math.ceil(criterion / (float) numberOfTasks);

            if (resultLocalCriterion < globalCriterion) {
//                System.out.println(sortType + " >>>> " + resultLocalCriterion + " < " + globalCriterion);
                globalCriterion = resultLocalCriterion;
                machines = localMachines;
            } else {
//                System.out.println(sortType + " >>>> " + resultLocalCriterion + " > " + globalCriterion);
            }
        }
        sortMachinesById();
    }

    private void generateSolutionFile(int index, int size) {
        String filename = "project2/output/" + index + "_" + size + ".txt";
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            writer.write(globalCriterion + "\n");
            for (Machine machine : machines) {
                for (int taskId : machine.getTasksIDs()) {
                    writer.write(taskId + " ");
                }
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runForIndexWithSize(int index, int size) {
        resetFields();
        this.inputPath = "project2/input/" + index + "/" + index + "_" + size + ".txt";
        readFile();
        long startTime = System.currentTimeMillis();
        calculate();
        long stopTime = System.currentTimeMillis();
        System.out.println("Size " + size + "   Criterion: " + globalCriterion + ", in time: " + (stopTime - startTime) + "ms");
        generateSolutionFile(index, size);
    }

    public void run(int index) {
        System.out.println("Algorithm Two just started for index: " + index);
        for (int size : Project2.sizesArray) {
            runForIndexWithSize(index, size);
        }
    }

}
