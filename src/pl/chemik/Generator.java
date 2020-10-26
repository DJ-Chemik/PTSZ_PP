package pl.chemik;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

    private Random random;

    private int minP = 10;
    private int maxP = 99;

    private int minR = 1;
    private int maxR;

    private int minD;
    private int maxD;

    private int minW = 1;
    private int maxW ;


    public Generator() {
        random = new Random();
    }

    private int generateNextInt(int from, int to) {
        return random.nextInt(to - from + 1) + from;
    }

    public ArrayList<Instance> generateInstances(int size) {
        maxW = size/10;
        maxR = (int) Math.round(size * maxP * 0.5);

        ArrayList<Instance> instances = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Instance instance = new Instance();
            int randomP = generateNextInt(minP, maxP);
            instance.setP(randomP);
            int randomR = generateNextInt(minR, maxR);
            instance.setR(randomR);
            minD = randomR + randomP;
            maxD = minD + 100;
            instance.setD(generateNextInt(minD, maxD));
            instance.setW(generateNextInt(minW, maxW));
        }
        return instances;
    }
}
