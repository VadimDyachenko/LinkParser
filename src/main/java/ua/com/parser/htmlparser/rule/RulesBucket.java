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

                String strKey = str.substring(0, matcher.start());
                if (isInvalid(strKey)) continue;
                Key key = Key.valueOf(strKey.toUpperCase());

                String condition = str.substring(matcher.start(), matcher.end());

                int value = 0;
                try {
                    value = Integer.parseInt(str.substring(matcher.end()));
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }

                rules.add(new RuleImpl(key, condition, value));
            }
        }
    }

    private boolean isInvalid(String key) {
        switch (key) {
            case "vote":
                return false;
            case "view":
                return false;
            case "favorite":
                return false;
            default:
                return true;
        }
    }

    public List<Rule> getRules() {
        return rules;
    }
}
