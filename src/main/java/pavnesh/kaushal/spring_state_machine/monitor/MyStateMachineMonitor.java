package pavnesh.kaushal.spring_state_machine.monitor;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.monitor.AbstractStateMachineMonitor;
import org.springframework.statemachine.transition.Transition;
import pavnesh.kaushal.spring_state_machine.enums.Events;
import pavnesh.kaushal.spring_state_machine.enums.States;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class MyStateMachineMonitor extends AbstractStateMachineMonitor<States, Events> {

    @Override
    public void transition(StateMachine<States, Events> stateMachine, Transition<States, Events> transition, long duration) {
        System.out.println("[MyStateMachineMonitor] transition called ... ");
    }

    @Override
    public void action(StateMachine<States, Events> stateMachine, Function<StateContext<States, Events>, Mono<Void>> action, long duration) {
        System.out.println("[MyStateMachineMonitor] action called ... ");
    }
}
