package pl.chemik.project3;


public class Project3 {
    public static Integer[] indexesArray = {
            132203, //--------
            132325, //-------
            136558, //0
            136674, //-------
            136698,
            136704,
            136748, //0
            136751,
            136760, //0
            136800, //-------
            136809,
            142192 //-------
    };
    public static Integer[] sizesArray = {50, 100, 150, 200, 250, 300, 350, 400, 450, 500};

    public static void main(String[] args) {
//        new InstanceGenerator().generate();
//        new Algorithm3().generateDummySolutionFile();
//        runAlgorithmToAllIndex(false);
        new Algorithm3().run(136809);
//        new Algorithm3().runForIndexWithSize(136809, 50);
//        new Validator().runValidation(136809, true);
    }

//    private static void runAlgorithmToAllIndex(boolean withValidation) {
//        Algorithm3 algorithm3 = new Algorithm3();
//        Validator validator = new Validator();
//        for (int index : indexesArray) {
//            algorithm3.run(index);
//            if (withValidation) {
//                validator.runValidation(index, false);
//            }
//            System.out.println("-------------------------------------------");
//        }
//    }

}
