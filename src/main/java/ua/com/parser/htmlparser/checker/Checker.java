package ua.com.parser.htmlparser.checker;

import ua.com.parser.htmlparser.rule.Key;
import ua.com.parser.htmlparser.rule.Rule;

public interface Checker {

    String getParseValue(Key key);

    boolean check(Rule rule, String value);

}
