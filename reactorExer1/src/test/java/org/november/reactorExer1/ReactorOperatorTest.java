package org.november.reactorExer1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ReactorOperatorTest {

    @Test
    void testMap() {
        Flux.range(1, 5)
                //just like java stream in transforms an element into a new element
                .map(i -> i * 10)
                .subscribe(System.out::println);  //The element was transform, e.g. the 1st element is no longer 1 but 10,
    }

    @Test
    void testMapChangeTypeOfItemAfterMap() {
        Flux.range(1, 5)
                //The map takes a functional interface Function.  within it you can return a an item with a different type
                //in this example after the map the item on the subsequent set is now a flux<String> and not fux<Integer>
                .map(i ->  "Hello" + i)
                .subscribe(System.out::println);  //The element was transform, e.g. the 1st element is no longer 1 but 10,
    }

    @Test
    void testFlatMap() {
        Flux.range(1,5)
                .flatMap(i -> Flux.range(i*10,2 ))
                .subscribe(e -> System.out.println(e));
    }

    @Test
    void testFlatMapMany() {
        Mono.just(3)
                .flatMapMany(i -> Flux.range(1,i))
                .subscribe(System.out::println);
    }

    @Test
    void testFluxConcat() throws InterruptedException {
        Flux<Integer> oneToFive = Flux.range(1,5)
                .delayElements(Duration.ofMillis(200));  //Trigger a delay when elements are immited by the publisher
        Flux<Integer> sixToTen = Flux.range(6,5)
                .delayElements(Duration.ofMillis(400));

        Flux.concat(oneToFive, sixToTen)
                .subscribe(System.out::println);

        Thread.sleep(4000);
    }

    //Unlike concat merge does not combine publishers in sequential way.
    @Test
    void testMergeFlux() throws InterruptedException {
        Flux<Integer> oneToFive = Flux.range(1,5)
                .delayElements(Duration.ofMillis(200));  //Trigger a delay when elements are immited by the publisher
        Flux<Integer> sixToTen = Flux.range(6,5)
                .delayElements(Duration.ofMillis(400));

        Flux.merge(oneToFive, sixToTen)
                .subscribe(System.out::println);

        Thread.sleep(4000);
    }

    //The zip operator combines two or more publishers by waiting on for all scources to emit one combine the value
    //based on the BiFunction provided
    @Test
    void testZipFlux() {
        Flux<Integer> oneToFive = Flux.range(1, 5);
        Flux<Integer> sixToTen = Flux.range(6, 5);
        Flux.zip(oneToFive, sixToTen,

                (item1, item2) -> item1 + " " + item2)
                .subscribe(System.out::println);
    }
}
