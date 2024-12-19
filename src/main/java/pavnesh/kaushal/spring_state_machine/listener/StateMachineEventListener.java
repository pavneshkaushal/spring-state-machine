package pavnesh.kaushal.spring_state_machine.listener;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import pavnesh.kaushal.spring_state_machine.enums.Events;
import pavnesh.kaushal.spring_state_machine.enums.States;

public class StateMachineEventListener extends StateMachineListenerAdapter<States, Events> {
    @Override
    public void stateChanged(State<States, Events> state, State<States, Events> state1) {
        System.out.println("State stateChanged " + state1.getId());
    }

    @Override
    public void stateEntered(State<States, Events> state) {
        System.out.println("State stateEntered " + state.getId());
    }

    @Override
    public void stateExited(State<States, Events> state) {
        System.out.println("State stateExited " + state.getId());
    }

    @Override
    public void eventNotAccepted(Message<Events> message) {
        System.out.println("State eventNotAccepted " + message.getPayload().name());
    }

    @Override
    public void transition(Transition<States, Events> transition) {
        System.out.println("State transition:" + transition.getName());
    }

    @Override
    public void transitionStarted(Transition<States, Events> transition) {
        System.out.println("State transitionStarted:" + transition.getName());
    }

    @Override
    public void transitionEnded(Transition<States, Events> transition) {
        System.out.println("State transitionEnded:" + transition.getName());
    }

    @Override
    public void stateMachineStarted(StateMachine<States, Events> stateMachine) {
        System.out.println("State stateMachineStarted:" + stateMachine.getId());
    }

    @Override
    public void stateMachineStopped(StateMachine<States, Events> stateMachine) {
        System.out.println("State stateMachineStopped:" + stateMachine.getId());
    }

    @Override
    public void stateMachineError(StateMachine<States, Events> stateMachine, Exception e) {
        System.out.println("State stateMachineError:" + stateMachine.getId());
    }

    @Override
    public void extendedStateChanged(Object o, Object o1) {
        System.out.println("State extendedStateChanged: ?");
    }

    @Override
    public void stateContext(StateContext<States, Events> stateContext) {
        System.out.println("State stateContext:" + stateContext.getStage().name());
    }
}
