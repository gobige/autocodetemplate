package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 状态模式
 */
public class StatePattern {
    public static void main(String[] args) {
        Context context = new Context();
        StartState startState = new StartState();
        EndState endState = new EndState();
        startState.doAction(context);
        endState.doAction(context);
    }
}

class EndState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("context at end status");
        context.setState(this);
    }
}

class StartState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("context at start status");
        context.setState(this);
    }
}

interface State {
    void doAction(Context context);
}

class Context {
    private State state;

    Context() {
        state = null;
    }
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}