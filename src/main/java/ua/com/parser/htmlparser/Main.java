package ua.com.parser.htmlparser;

import ua.com.parser.htmlparser.fileworker.FileWorker;
import ua.com.parser.htmlparser.fileworker.FileWorkerImpl;

public class Main {
    public static void main(String[] args) {

        String input = "src/test/resources/input.txt";
        String output = "src/test/resources/output.txt";

//        checkArgs(args);
        FileWorker fileWorker = new FileWorkerImpl(input, output);
        LinkCollector linkCollector = new LinkCollector(fileWorker);
        linkCollector.run();
    }

    private static void checkArgs(String[] args) { // TODO избавиться от static
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number input parameters\n" +
                    "You must input two path of file:\n" +
                    "The first file should contain conditions for comparison\n" +
                    "The second file for output data");
        }
    }
}
