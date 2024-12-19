package pavnesh.kaushal.spring_state_machine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.monitor.StateMachineMonitor;
import pavnesh.kaushal.spring_state_machine.enums.Events;
import pavnesh.kaushal.spring_state_machine.enums.States;
import pavnesh.kaushal.spring_state_machine.listener.StateMachineEventListener;
import pavnesh.kaushal.spring_state_machine.monitor.MyStateMachineMonitor;

import java.util.EnumSet;

@Configuration
@EnableStateMachine(contextEvents = false)
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter <States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
            throws Exception {
        config
                .withMonitoring()
                    .monitor(stateMachineMonitor());
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(stateMachineListener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.SI)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                    .source(States.SI)
                    .target(States.S1)
                    .event(Events.E1)
                    .guard(guard())
                    .action(action())
                    .and()
                .withExternal()
                    .source(States.S1)
                    .target(States.S2)
                    .event(Events.E2)
                    .guardExpression("true")
                    .action(action())
                    .and()
                .withInternal()
                    .source(States.S2)
                    .action(timerAction())
                    .timerOnce(2000);
    }

    @Bean
    public StateMachineMonitor<States, Events> stateMachineMonitor(){
        return new MyStateMachineMonitor();
    }

    @Bean
    public StateMachineListener<States, Events> stateMachineListener() {
        return new StateMachineEventListener();
    }

    @Bean
    public Action<States, Events> action() {
        return new Action<States, Events>() {

            @Override
            public void execute(StateContext<States, Events> context) {
                System.out.println("Action taken for event : "+ context.getEvent().name());
            }
        };
    }

    @Bean
    public TimerAction timerAction(){
        return new TimerAction();
    }
    public static class TimerAction implements Action<States, Events> {

        @Override
        public void execute(StateContext<States, Events> context) {
            System.out.println("TimerAction taken for event : "+ context.getEvent().name());
        }
    }

    @Bean
    public Guard<States, Events> guard() {
        return new Guard<States, Events>() {

            @Override
            public boolean evaluate(StateContext<States, Events> context) {
                System.out.println("Evaluating guard for event : "+ context.getEvent().name());
                return true;
            }
        };
    }
}
