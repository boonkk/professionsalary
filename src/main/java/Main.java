public class Main {
    public static void main(String[] args) {
        String[] files = new String[]{"","C:\\Users\\marci\\IdeaProjects\\professionsalary\\src\\main\\java\\data\\jsonTestFile.json","fdsfsdfs","fdsfsdfsd"};
        //SalariesCalculator salariesCalculator = new SalariesCalculator(files);
        SalariesCalculator salariesCalculator = new SalariesCalculator(args);
        salariesCalculator.start();
    }
}
