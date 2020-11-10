package pl.chemik;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static Integer[] indexesArray = {132203, 132325, 136558, 136674, 136698, 136704,
            136748, 136751, 136760, 136800, 136809, 142192};

    public static Integer[] sizesArray = {50, 100, 150, 200, 250, 300, 350, 400, 450, 500};

    public static void main(String[] args) {
        Algorithm1 algorithm1 = new Algorithm1();

//        new InstanceFileGenerator().generateFiles();
//        new DummyAlgorithm().generateSolutions();
//        new Validator().runValidation();
        algorithm1.runAlgorithmOneForIndex(132325);
    }


}
