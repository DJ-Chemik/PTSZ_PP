package pl.chemik;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Validator {

    private int readedCriterion;
    private Map<String, ArrayList<Task>> tasksMap = new HashMap();
    private Map<String, ArrayList<Integer>> resultMap = new HashMap();

    private ArrayList<Task> readInputInstanceFile(int index, int size) throws FileNotFoundException {
        String filepath = "filesInput/" + index + "/" + index + "_" + size + ".txt";
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        int numberOfTasks = scanner.nextInt();
        if (numberOfTasks != size) {
            throw new FileNotFoundException();
        }

        ArrayList<Task> tasks = new ArrayList<>();
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
        return tasks;
    }

    private void readAllInputFiles() throws FileNotFoundException {
        ArrayList<Integer> indexes = new ArrayList<>(Arrays.asList(Main.indexesArray));
        for (int index : indexes) {
            for (int size = 50; size <= 500; size += 50) {
                ArrayList<Task> tasks = readInputInstanceFile(index, size);
                tasksMap.put(index + "_" + size, tasks);
            }
        }
    }

    private ArrayList<Integer> readOneSolution(String filepath) throws FileNotFoundException {
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        this.readedCriterion = scanner.nextInt();

        ArrayList<Integer> tasksIds = new ArrayList<>();
        while (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            tasksIds.add(id);
        }
        scanner.close();
        return tasksIds;
    }

    private void readDummySolutions() throws FileNotFoundException {
        for (int index : Main.indexesArray) {
            for (int size = 50; size <= 500; size += 50) {
                ArrayList<Integer> tasksIds = readOneSolution("filesOutputDummyAlgorithm/dummy_" + size + ".txt");
                resultMap.put(index + "_" + size, tasksIds);
            }
        }
    }

    private boolean checkSolution(String index, int size) {
        ArrayList<Task> tasks = tasksMap.get(index + "_" + size);
        Set<Task> taskSet = new HashSet<>(tasks);
        if (taskSet.size() != size) {
            System.out.println("Nieprawidłowa liczba zadań w uszeregowaniu lub jakieś zadanie zostało użyte więcej niż raz");
            return false;
        }
        return true;
    }

    private int checkCriterion(String index, int size, boolean willCheckSolution) {
        ArrayList<Task> tasks = tasksMap.get(index + "_" + size);
        ArrayList<Integer> resultIds = resultMap.get(index + "_" + size);
        if (willCheckSolution && !checkSolution(index, size)) {
            System.out.println(">>>>>> Nieprawidłowa walidacja rozwiązania <<<<<<");
            return -1;
        }
        int criterion = 0;
        int time = 0;
        for (int id : resultIds) {
            Task task = tasks.stream().filter(t -> t.getId() == id).findAny().get();
            time = Math.max(time, task.getR()) + task.getP();
            boolean isDelayed = time > task.getD();
            if (isDelayed) {
                criterion += task.getW();
            }
        }
        return criterion;
    }

    public void runValidationForDummy(int index, int size) {
        try {
            readAllInputFiles();
            readDummySolutions();
            int criterion = checkCriterion(String.valueOf(index), size, true);
            System.out.println(criterion);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void runValidation(String filepath, int index, int size) {
        try {
            readAllInputFiles();
//            ArrayList<Integer> tasksIds = readOneSolution("filesAlg1/out_" + index + "_" + size + ".txt");
            ArrayList<Integer> tasksIds = readOneSolution(filepath);
            resultMap.put(index + "_" + size, tasksIds);
            int criterion = checkCriterion(String.valueOf(index), size, true);
            if (criterion != readedCriterion) {
                System.out.println("Wyliczona wartość kryterium niezgodna z odczytana z pliku!!!");
                System.out.println("Odczytana: " + readedCriterion);
                System.out.println("Wyliczona: " + criterion);
                System.out.println("------------------------------------------------------------");
                return;
            }
            System.out.println(criterion);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
