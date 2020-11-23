package pl.chemik.project2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class InstanceGenerator {

    private Random random;
    private ArrayList<Float> machines;

    public InstanceGenerator() {
        random = new Random();
        resetGeneratorFields();
    }

    public void resetGeneratorFields() {
        machines = new ArrayList<>();
    }

    private float nextFloat(float from, float to) {
        float number = from + random.nextFloat() * (to - from);
        return Math.round(number * 100.0f) / 100.0f;
    }

    private void randomMachinesSpeeds() {
        machines.add(1.0f);
        machines.add(nextFloat(0.5f, 0.9f));
        machines.add(nextFloat(0.25f, 0.8f));
        machines.add(nextFloat(0.25f, 0.5f));
        machines.add(0.25f);
    }

    public void generate() {
        randomMachinesSpeeds();
        machines.forEach(m -> System.out.println(m));
    }
}
