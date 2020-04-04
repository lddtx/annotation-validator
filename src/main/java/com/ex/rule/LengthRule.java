package com.ex.rule;

import com.ex.annotions.Length;

public class LengthRule implements Rule<Length> {
    @Override
    public void check(Length annotation, String fieldName, Object target) {
        int length = target == null ? 0 : target.toString().length();
        if (length < annotation.min() || length > annotation.max()) {
            throw new IllegalStateException("Invalid string: "
                    + fieldName + ", " + target
                    + " Min length: " + annotation.min()
                    + " Max length: " + annotation.max()
                    + " Actual: " + length
            );
        }
    }

    @Override
    public Class<Length> getAnnotationClass() {
        return Length.class;
    }
}
