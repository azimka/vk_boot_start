package model;

public class Stage {
    private String name;
    private String message;
    private String nextStageName;
    private Transition[] transitions = new Transition[0];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNextStageName() {
        return nextStageName;
    }

    public void setNextStageName(String nextStageName) {
        this.nextStageName = nextStageName;
    }

    public Transition[] getTransitions() {
        return transitions;
    }

    public void setTransitions(Transition[] transitions) {
        this.transitions = transitions;
    }
}
