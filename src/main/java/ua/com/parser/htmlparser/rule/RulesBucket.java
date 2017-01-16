package ua.com.parser.htmlparser.rule;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vadim on 16.01.17.
 */
public class RulesBucket {

    List<Rule> rules = new LinkedList<>();


    public void createRule(String str) {
        //vote>100
        //view<=1000
        //> < >= <= == !=
        Pattern pattern = Pattern.compile("[><=!]+");
        Matcher matcher = pattern.matcher(str);
        System.out.println(str);
        System.out.println(matcher.find() ?
                "found '"+matcher.group()+"' starting at index "+matcher.start()+" and ending at index "+matcher.end()+"." :
                "I found nothing!");
// I found the text '192.168.0.3' starting at index 0 and ending at index 11.
    }

    public List<Rule> getRules() {
        return rules;
    }
}
