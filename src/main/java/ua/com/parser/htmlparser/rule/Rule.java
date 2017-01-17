package ua.com.parser.htmlparser.rule;

/**
 * Created by vadim on 16.01.17.
 */
public interface Rule {

    String getKey();

    String getCondition();

    int getValue();
}
