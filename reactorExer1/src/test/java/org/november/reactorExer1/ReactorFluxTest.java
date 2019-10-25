package org.november.reactorExer1;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;

public class ReactorFluxTest {

    @Test
    void fluxInitializeFromConstant() {
        Flux.just("A","B","C")
                .log()
                .subscribe();
    }

    //If you use just the entire list will be pass as one stream item.  If you want to treat each item
    //in the list as an item in the stream see the next test
    @Test
    void fluxInitializeJustFromArrayList() {
        Flux.just(Arrays.asList("A","B","C"))
                .log()
                .subscribe();
    }

    @Test
    void fluxInitializeFromIterable() {
        Flux.fromIterable(Arrays.asList("A","B","C"))
                .log()
                .subscribe(System.out::println);
    }

    @Test
    void fluxFromRange() {
        Flux.range(10, 5)
                .log()
                .subscribe(System.out::println);
    }

    @Test
    void fluxFromInterval() throws Exception {
        Flux.interval(Duration.ofSeconds(1))
                .log()
                .subscribe();
        //Without this you will not see anything printed because the main thread will be killed immediately
        //When the end the code block is reached.
        Thread.sleep(5000);
        //The test does not actually end by itself the only reason it stopped is because the main thread gets
        //killed after the thread sleep
    }

    @Test
    void fluxFromInternal_limitTo2Element() throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .log()
                .take(2)  //Cancels the subscription after processing 2 stream items
                .subscribe();
        Thread.sleep(5000);
    }


    //This limit the initial request of items from the stream to 3.
    //OnComplete is not called because the flux stream 5 items but we only 3
    @Test
    void fluxLimitRequest() {
        Flux.range(1, 5)
                .log()
                .subscribe(null,
                        null,
                        null,

                        s -> s.request(3));
    }


    //This test shows the use  CustomSubscriber.  upon subsription to the publisher
    //The subscriber from the hookOnNext method determine a random number of items to be push by publisher
    //Look at the result of the debug. call to reques() method seems to be non-blocking
    @Test
    void fluxCustomSubscriber() {
        Flux.range(1, 10)
                .log()
                .subscribe(new BaseSubscriber<Integer>() {
                    int elementsToProcess = 3;
                    int counter = 0;

                    public void hookOnSubcribe(Subscription subscription) {
                        System.out.println("Subscribed!");
                        request(elementsToProcess);
                    }


                    public void hookOnNext(Integer value) {
                        counter++;
                        if(counter == elementsToProcess) {
                            counter = 0;
                            Random r = new Random();
                            elementsToProcess = r.ints(1,4)
                                    .findFirst().getAsInt();
                            request(elementsToProcess);
                        };

                        System.out.println("Value: " + value);
                    }

                });
    }
}
