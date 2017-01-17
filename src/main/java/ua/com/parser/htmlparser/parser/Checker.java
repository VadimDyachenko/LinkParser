package ua.com.parser.htmlparser.parser;

import ua.com.parser.htmlparser.rule.Rule;

/**
 * Created by vadim on 17.01.17.
 */
public abstract class Checker {

    private String getClassValue(Rule rule) {

        String result = "views-count_post";
        if (rule.getKey().equals("vote")) {
            result = "voting-wjt__counter-score js-score";
        } else if (rule.getKey().equals("favorite")) {
            result = "favorite-wjt__counter js-favs_count";
        }

        return result;
    }


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
