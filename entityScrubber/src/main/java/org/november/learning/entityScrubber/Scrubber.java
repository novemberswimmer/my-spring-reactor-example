package org.november.learning.entityScrubber;

import reactor.core.publisher.Flux;

import java.lang.reflect.Field;

public class Scrubber {

    public void scrubObject(Object obj) {
        if (obj.getClass().getAnnotation(Scrubable.class) != null) {
            Flux.fromArray(obj.getClass().getDeclaredFields())
                    .map(f -> {
                        f.setAccessible(true);
                        ScrubbingMethod scrubbingMethods = f.getAnnotation(ScrubbingMethod.class);
                        if (scrubbingMethods != null) {
                            switch (scrubbingMethods.type().toLowerCase())
                            {
                                case "masked" : scrubByMasking(obj, f);
                            }
                        }
                        return f;
                    }).subscribe();
        }
    }

    private void scrubByMasking(Object obj, Field f) {
        try {
            f.set(obj, "****");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
