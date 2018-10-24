package lk.universe.core.enums;
/*
* State of the URL
 */
public enum State {
    LIVE("LIVE"),
    BROKEN("BROKEN"),
    UNRESPONSIVE("UNRESPONSIVE"),
    DEAD("DEAD");

    private String state;

    State(String state) {
        this.state = state;
    }

    public String getMethodAsString() {
        return state;
    }
}
