package ua.com.parser.htmlparser.rule;

public class RuleImpl implements Rule {

    private Key key;
    private String condition;
    private int value;

    public RuleImpl(Key key, String condition, int value) {

        this.key = key;
        this.condition = condition;
        this.value = value;
    }

    @Override
    public Key getKey() {
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
}
