package org.november.learning.entityScrubber.engine.approach;

import org.november.learning.entityScrubber.Employee;

import java.lang.reflect.Field;

public class MaskedScrub implements Employee.TypeOfScrubbing {

    public static void scrubByMasking(Object obj, Field f) {
        try {
            f.set(obj, "****");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
