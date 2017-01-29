package ua.com.parser.htmlparser.rule;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RulesBucket {

    private List<Rule> rules = new LinkedList<>();

    public void createRule(List<String> requests) throws RuntimeException {

        Pattern pattern = Pattern.compile("[><=!]+");

        for (String str : requests) {

            Matcher matcher = pattern.matcher(str);
            if(matcher.find()) {

                Key key = null;
                String condition = null;
                int value = 0;

                try {
                    key = getKey(str, matcher);
                    condition = str.substring(matcher.start(), matcher.end());
                    value = getValue(str, matcher);
                } catch (IllegalArgumentException e) {
                    System.out.println("Rule: \"" + str + "\" ignored, illegal argument: " + e.getMessage());
                    continue;
                }

                rules.add(new RuleImpl(key, condition, value));
            }
        }
    }

    private Key getKey(String str, Matcher matcher) {
        String strKey = str.substring(0, matcher.start());
        if (!isValid(strKey)) throw new IllegalArgumentException("Unsupported Key");

        return Key.valueOf(strKey.toUpperCase());
    }

    private int getValue(String str, Matcher matcher) throws NumberFormatException {
        return Integer.parseInt(str.substring(matcher.end()));
    }

    private boolean isValid(String key) {
        switch (key) {
            case "vote":
                return true;
            case "view":
                return true;
            case "favorite":
                return true;
            default:
                return false;
        }
    }

    public List<Rule> getRules() {
        return rules;
    }
}
