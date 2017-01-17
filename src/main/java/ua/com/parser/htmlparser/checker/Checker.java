package ua.com.parser.htmlparser.checker;

import ua.com.parser.htmlparser.rule.Rule;

public interface Checker {

    String getParseValue();

    boolean check(Rule rule, int value);

}
