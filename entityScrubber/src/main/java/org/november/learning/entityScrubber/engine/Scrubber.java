package org.november.learning.entityScrubber.engine;

import org.november.learning.entityScrubber.engine.annotations.Scrubable;
import org.november.learning.entityScrubber.engine.approach.MaskedScrub;
import org.november.learning.entityScrubber.engine.approach.ReplaceScrub;
import reactor.core.publisher.Flux;

import java.lang.annotation.Annotation;

public class Scrubber {

    public void scrubObject(Object obj) {
        if (obj.getClass().getAnnotation(Scrubable.class) != null) {
            Flux.fromArray(obj.getClass().getDeclaredFields())
                    .map(f -> {
                        f.setAccessible(true);
                        Annotation[] scrubbingMethods = f.getDeclaredAnnotations();

                        for (Annotation annotation : scrubbingMethods) {
                            switch (annotation.annotationType().getSimpleName().toLowerCase()) {
                                case "scrubbingbymasking" :
                                    MaskedScrub.scrubByMasking(obj, f);
                                    break;
                                case "scrubbingbyreplacing" :
                                    ReplaceScrub.scrubByReplace(obj, f, annotation);
                                    break;
                                default:
                                    break;
                            }
                        }
                        return f;
                    }).subscribe();
        }
    }



}
