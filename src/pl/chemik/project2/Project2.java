package pl.chemik.project2;


import pl.chemik.project2.Algorithm2.Algorithm2;

public class Project2 {
    public static Integer[] indexesArray = {132203, 132325, 136558, 136674, 136698, 136704,
            136748, 136751, 136760, 136800, 136809, 142192};
    public static Integer[] sizesArray = {50, 100, 150, 200, 250, 300, 350, 400, 450, 500};

    public static void main(String[] args) {
//        new InstanceGenerator().generate();
        runAlgorithmToAllIndex(false);
//        new Algorithm2().run(132203);
//        new Validator().runValidation(132203, false);
    }

    private static void runAlgorithmToAllIndex(boolean withValidation) {
        Algorithm2 algorithm2 = new Algorithm2();
        Validator validator = new Validator();
        for (int index : indexesArray) {
            algorithm2.run(index);
            if (withValidation) {
                validator.runValidation(index, false);
            }
            System.out.println("-------------------------------------------");
        }
    }

}
