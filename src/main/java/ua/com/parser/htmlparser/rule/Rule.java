package ua.com.parser.htmlparser.rule;

public interface Rule {

    Key getKey();

    String getCondition();

    int getValue();
}
