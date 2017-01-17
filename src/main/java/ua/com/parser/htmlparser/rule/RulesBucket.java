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

    private int toInt(char[] valueCh) {
        String tmp = "";
        for (int i = 0; i < valueCh.length; i++) {
            if (Character.isDigit(valueCh[i])) {
                tmp = String.valueOf(valueCh[i]);
            }
        }
        return Integer.parseInt(tmp);
    }

    public List<Rule> getRules() {
        return rules;
    }
}
