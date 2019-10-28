package org.november.learning.entityScrubber.engine.approach;

import org.november.learning.entityScrubber.Employee;
import org.november.learning.entityScrubber.engine.annotations.ScrubbingByReplacing;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ReplaceScrub implements Employee.TypeOfScrubbing {
    public static void scrubByReplace(Object obj, Field f, Annotation annotation) {
        try {
            f.set(obj, ((ScrubbingByReplacing) annotation).value());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
