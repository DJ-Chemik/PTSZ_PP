package pl.chemik;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Validator {

    public void readFromFile(int index, int size) throws FileNotFoundException {
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
    }

    public void readAllFiles() throws FileNotFoundException {
        Integer[] indexesArray = {132203, 132325, 136558, 136674, 136698, 136704, 136748, 136751, 136760, 136800, 136809, 142192};
        ArrayList<Integer> indexes = new ArrayList<>(Arrays.asList(indexesArray));
        for (int index : indexes) {
            for (int size = 50; size <= 500; size+=50) {
                readFromFile(index, size);
            }
        }
    }

    public void runValidation() {
        try {
            readAllFiles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
