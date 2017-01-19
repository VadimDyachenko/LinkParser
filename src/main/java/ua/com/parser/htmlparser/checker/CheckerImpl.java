package ua.com.parser.htmlparser.checker;

import ua.com.parser.htmlparser.rule.Key;
import ua.com.parser.htmlparser.rule.Rule;

public class CheckerImpl implements Checker {

    @Override
    public String getParseValue(Key key) {

        switch (key) {
            case VOTE: return "voting-wjt__counter-score js-score";
            case VIEW: return "views-count_post";
            case FAVORITE: return "favorite-wjt__counter js-favs_count";

            default: throw new IllegalArgumentException("Unsupported key");
        }
    }

    @Override
    public boolean check(Rule rule, String data) {

        int value = convert(data);

        switch (rule.getCondition()) {
            case "<": return value < rule.getValue();
            case ">": return value > rule.getValue();
            case "<=": return value <= rule.getValue();
            case ">=": return value >= rule.getValue();
            case "==": return value == rule.getValue();
            case "!=": return value != rule.getValue();

            default: return false;
        }
    }

    private int convert(String data) {

        if(data.contains("k")) {
            if (data.contains(",")) {
                data = data.replace(",","").replace("k","").concat("00");
            } else {
                data = data.replace("k","").concat("000");
            }
        }

        data = data.replace("–", "-");
        return Integer.parseInt(data);
    }
}
