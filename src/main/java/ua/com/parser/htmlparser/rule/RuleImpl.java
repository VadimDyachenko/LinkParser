package ua.com.parser.htmlparser.rule;

/**
 * Created by vadim on 16.01.17.
 */
public class RuleImpl implements Rule {

    private String key;
    private String condition;
    private int value;

    public RuleImpl(String key, String condition, int value) {

        this.key = key;
        this.condition = condition;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getCondition() {
        return condition;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return  "key='" + key + '\'' +
                ", condition='" + condition + '\'' +
                ", value='" + value + '\'';
    }
}
