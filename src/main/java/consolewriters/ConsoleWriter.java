package consolewriters;

import java.util.Map;

public class ConsoleWriter {
    private ConsoleWriter() {
        throw new AssertionError();
    }

    public static void writeMap(String filePath,Map<?,?> map) {
        System.out.println(filePath + ":");
        map.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " - " + replaceDotsWithCommas(e.getValue())));
        System.out.println();
    }

    public static void writeInvalidContent(String filePath) {
        System.out.println(filePath + ":");
        System.out.println("\tInvalid file content or type.\n");

    }

    public static void writeFileNotFound(String filePath) {
        System.out.println("File not found:");
        if(filePath.isEmpty())
            System.out.println("\tEmpty file path.");
        else
            System.out.println("\t"+filePath);
        System.out.println();
    }

    private static String replaceDotsWithCommas(Object bd){
        return bd.toString().replaceAll("\\.",",");
    }
}
