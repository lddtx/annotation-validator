package com.ex.rule;

import com.ex.annotions.NotEmpty;

public class NotEmptyRule implements Rule<NotEmpty>{
    @Override
    public void check(NotEmpty annotation, String fieldName, Object target) {
        if (target == null || (target instanceof String && target.toString().isEmpty())) {
            throw new NullPointerException("Empty field: " + fieldName);
        }
    }

    @Override
    public Class<NotEmpty> getAnnotationClass() {
        return NotEmpty.class;
    }
}
