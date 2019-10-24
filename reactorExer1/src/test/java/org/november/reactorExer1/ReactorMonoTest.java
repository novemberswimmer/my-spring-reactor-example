package org.november.reactorExer1;

import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
class ReactorMonoTest {

	@Test
	void loggingWithMono() {
		Mono.just("A")
				.log()
				.subscribe();
	}

	//Print the stream item coming from the Mono upon subscription
	@Test
	void monoWithConsumer() {
		Mono.just("A")
				.subscribe(System.out::println);
	}

	@Test
	void monoWithDoOn() {
		Mono.just("A")
				.doOnSubscribe(x -> System.out.println("onSubscribed: " + x))
				.doOnSubscribe(x -> System.out.println("onRequest: " + x))
				.doOnSuccess(x -> System.out.println("onComplete: " + x))
				.subscribe();
	}

	//What is the use of empty mono. This emulate a method returning void.
	// It does not return a value but it imits a signal that the processing of the stream is done.
	@Test
	void emptyMono() {
		Mono.empty()
				.log()
				.subscribe(System.out::println,
						null,
						() -> System.out.println("Processing is done"));
	}

	@Test
	void errorRuntimeExceptionMono() {
		Mono.error(new RuntimeException())
				.log()
				.subscribe();
	}

	@Test
	void errorConsumerMono() {
		Mono.error(new Exception())
				.log()  //Without the call to log() method you will not see the stack trace
				.subscribe(System.out::println,
						e -> System.out.println("Error: " + e),
						() -> System.out.println("Done"));
	}

	//this is equivalent to the test errorConsumerMono()
	@Test
	void errorDoOnErrorMono() {
		Mono.error(new Exception())
				.doOnError(e -> System.out.println("Error: " + e))
				.log()
				.subscribe();
	}

	//This test show how to swallow an exception and return a stream elment that
	//will be pass along on the subsequent step.
	@Test
	void errorOnErrorResumeMono() {
		Mono.error(new Exception())
				.onErrorResume( e ->
				{
					System.out.println("Caught : " + e);
					return Mono.just("B");
				})
				.log()
				.subscribe(System.out::println);
	}


	//This is sthe same test as errorOnErrorResumeMono() where an exception is swallowed
	//And a hard coded stream item (a default value) is return instead
	@Test
	void errorOnErrorReturnMon() {
		Mono.error(new Exception())
				.onErrorReturn("B")
				.log()
				.subscribe(System.out::println);
	}
}
