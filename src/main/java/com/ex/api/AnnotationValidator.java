package com.ex.api;

import com.ex.rule.Rule;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnnotationValidator implements EntityValidator{

    private final Map<Class<? extends Annotation>, Rule<?>> rules;

    public AnnotationValidator(List<Rule<?>> rules) {
        this.rules = rules.stream()
                .collect(Collectors.toMap(Rule::getAnnotationClass,
                        Function.identity()));
    }

    @Override
    public void validate(Object entity) {
        FieldUtils.getAllFieldsList(entity.getClass())
                .stream()
                .peek(field -> field.setAccessible(true))
                .forEach(field -> {
                    Annotation[] annotations = field.getAnnotations();
                    if (annotations.length == 0) {
                        return;
                    }

                    for (Annotation annotation : annotations) {
                        Rule rule = rules.get(annotation.annotationType());
                        if (rule == null) {
                            return;
                        }
                        try {
                            rule.check(annotation, field.getName(), field.get(entity));
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException(e);
                        }
                    }
                });
    }

}
