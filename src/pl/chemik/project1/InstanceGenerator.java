package pl.chemik.project1;

import java.util.ArrayList;
import java.util.Random;

public class InstanceGenerator {

    private Random random;

    private int minP = 10;
    private int maxP = 99;

    private int minR = 1;
    private int maxR;

    private int minD;
    private int maxD;

    private int minW = 1;
    private int maxW ;


    public InstanceGenerator() {
        random = new Random();
    }

    private int generateNextInt(int from, int to) {
        return random.nextInt(to - from + 1) + from;
    }

    public ArrayList<Task> generateInstances(int size) {
        maxW = size/10;
        maxR = (int) Math.round(size * maxP * 0.5);

        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Task task = new Task();
            int randomP = generateNextInt(minP, maxP);
            task.setP(randomP);
            int randomR = generateNextInt(minR, maxR);
            task.setR(randomR);
            minD = randomR + randomP;
            maxD = minD + 100;
            task.setD(generateNextInt(minD, maxD));
            task.setW(generateNextInt(minW, maxW));
            tasks.add(task);
        }
        return tasks;
    }
}
