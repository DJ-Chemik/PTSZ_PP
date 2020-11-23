package pl.chemik.project1;

import java.io.FileWriter;
import java.io.IOException;

public class DummyAlgorithm {
    public void generateSolutions() {
        for (int size = 50; size <= 500; size+=50) {
            String filename = "project1/filesOutputDummyAlgorithm/dummy_" + size + ".txt";
            FileWriter writer = null;
            try {
                writer = new FileWriter(filename);
                writer.write(size + "\n" );
                for (int i = 1; i <= size; i++) {
                    writer.write(i + " " );
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
