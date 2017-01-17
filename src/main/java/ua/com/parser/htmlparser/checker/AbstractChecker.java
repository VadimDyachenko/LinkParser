package ua.com.parser.htmlparser.checker;

import ua.com.parser.htmlparser.rule.Rule;

public abstract class AbstractChecker implements Checker {

    public abstract String getParseValue();




    public boolean check(Rule rule, int value) {

        switch (rule.getCondition()) {
            case "<": return rule.getValue() < value;

            case ">": return rule.getValue() > value;

            case "<=": return rule.getValue() <= value;

            case ">=": return rule.getValue() >= value;

            case "==": return rule.getValue() == value;

            case "!=": return rule.getValue() != value;

            default: return false;
        }
    }

}
