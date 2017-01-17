package ua.com.parser.htmlparser.rule;

import com.sun.org.apache.regexp.internal.CharacterArrayCharacterIterator;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RulesBucket {

    private List<Rule> rules = new LinkedList<>();

    public void createRule(List<String> requests) {

        Pattern pattern = Pattern.compile("[><=!]+");

        for (String str : requests) {

            Matcher matcher = pattern.matcher(str);

            if(matcher.find()) {

                String key = str.substring(0, matcher.start());
                String condition = str.substring(matcher.start(), matcher.end());
                int value = toInt(str.substring(matcher.end()).toCharArray());

                rules.add(new RuleImpl(key, condition, value));
            }
        }
    }

    private int toInt(char[] value) {
        String tmp = "";
        for (char aValueCh : value) {
            if (Character.isDigit(aValueCh)) {
                tmp = String.valueOf(aValueCh);
            }
        }
        return Integer.parseInt(tmp);
    }

    public List<Rule> getRules() {
        return rules;
    }
}
