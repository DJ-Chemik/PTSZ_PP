package pl.chemik;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Validator {

    private Map<String, ArrayList<Task>> tasksMap = new HashMap();
    private Map<String, ArrayList<Integer>> resultMap = new HashMap();

    private ArrayList<Task> readInputFile(int index, int size) throws FileNotFoundException {
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
        Integer[] indexesArray = {132203, 132325, 136558, 136674, 136698, 136704, 136748, 136751, 136760, 136800, 136809, 142192};
        ArrayList<Integer> indexes = new ArrayList<>(Arrays.asList(indexesArray));
        for (int index : indexes) {
            for (int size = 50; size <= 500; size += 50) {
                ArrayList<Task> tasks = readInputFile(index, size);
                tasksMap.put(index + "_" + size, tasks);
            }
        }
    }

    private ArrayList<Integer> readOneSolution(String filepath) throws FileNotFoundException {
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        int numberOfTasks = scanner.nextInt();

        ArrayList<Integer> tasksIds = new ArrayList<>();
        while (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            tasksIds.add(id);
        }
        scanner.close();
        return tasksIds;
    }

    private void readSolutions() throws FileNotFoundException {
        Integer[] indexesArray = {132203, 132325, 136558, 136674, 136698, 136704, 136748, 136751, 136760, 136800, 136809, 142192};
        ArrayList<Integer> indexes = new ArrayList<>(Arrays.asList(indexesArray));
        for (int index : indexes) {
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

    private int checkCriterion(String index, int size, boolean checkSolution) {
        ArrayList<Task> tasks = tasksMap.get(index + "_" + size);
        ArrayList<Integer> resultIds = resultMap.get(index + "_" + size);
        if (!checkSolution(index, size)) {
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

    public void runValidation() {
        try {
            readAllInputFiles();
            readSolutions();
            int criterion = checkCriterion("136809", 250, true);
            System.out.println(criterion);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
