package ua.com.parser.htmlparser;

import ua.com.parser.htmlparser.fileworker.FileWorker;
import ua.com.parser.htmlparser.fileworker.FileWorkerImpl;

/**
 * Created by vadim on 15.01.17.
 */
public class Main {
    public static void main(String[] args) {

        checkArgs(args);
        FileWorker fileWorker = new FileWorkerImpl();

        try {

            LinkCollector collector = new LinkCollector(fileWorker.read(args[0]));
            fileWorker.write(collector.getLinks());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkArgs(String[] args) { //TODO избавиться от static
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number input parameters\n" +
                    "You must input two path of file:\n" +
                    "The first file should contain conditions for comparison\n" +
                    "The second file for output data");
        }
    }
}
