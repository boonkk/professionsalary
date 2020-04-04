public class Main {
    public static void main(String[] args) {
        String[] files = new String[]{"","fdsfsd","fdsfsdfs","fdsfsdfsd"};
        SalariesCalculator salariesCalculator = new SalariesCalculator(files);
        //SalariesCalculator salariesCalculator = new SalariesCalculator(args);
        salariesCalculator.start();
    }
}
