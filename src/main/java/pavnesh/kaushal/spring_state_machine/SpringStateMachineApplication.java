package pavnesh.kaushal.spring_state_machine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import pavnesh.kaushal.spring_state_machine.enums.Events;
import pavnesh.kaushal.spring_state_machine.enums.States;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class SpringStateMachineApplication implements CommandLineRunner {

	@Autowired
	private StateMachine<States, Events> stateMachine;

	public static void main(String[] args) {
		SpringApplication.run(SpringStateMachineApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		stateMachine.sendEvent(Events.E1);
		Thread.sleep(2000);
		stateMachine.sendEvent(Events.E2);
		Thread.sleep(2000);
//		stateMachine.sendEvent(Events.E3);
	}
}
