package ua.com.parser.htmlparser;

import ua.com.parser.htmlparser.fileworker.FileWorker;
import ua.com.parser.htmlparser.fileworker.FileWorkerImpl;

public class Main {
    public static void main(String[] args) {

        try {
            checkArgs(args);

            FileWorker fileWorker = new FileWorkerImpl(args[0], args[1]);
            LinkCollector linkCollector = new LinkCollector(fileWorker);
            linkCollector.run();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number input parameters");
        }
    }
}