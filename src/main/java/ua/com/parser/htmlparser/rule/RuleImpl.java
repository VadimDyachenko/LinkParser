package ua.com.parser.htmlparser.rule;

public class RuleImpl implements Rule {

    private Key key;
    private String condition;
    private int value;

    public RuleImpl(String key, String condition, int value) {

        this.key = setKey(key);
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

    @Override
    public String toString() {
        return  "key='" + key + '\'' +
                ", condition='" + condition + '\'' +
                ", value='" + value + '\'';
    }

    private Key setKey(String key) {
        switch (key) {
            case "vote":
                return Key.VOTE;
            case "favorite":
                return Key.FAVORITE;
            case "view":
                return Key.VIEW;
            default:
                throw new RuntimeException("Unsupported key condition");
        }
    }
}
