import com.ex.annotions.NotEmpty;
import com.ex.api.AnnotationValidator;
import com.ex.rule.NotEmptyRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Tests {

    @Mock
    NotEmptyRule notEmptyRule;

    @BeforeEach
    public void init() {
        //when(notEmptyRule.getAnnotationClass()).thenReturn(NotEmpty.class);
    }

    @Test
    public void testRulesCall() {
        Entity entity = new Entity("1", "");
        notEmptyRule = new NotEmptyRule();
        AnnotationValidator annotationValidator
                = new AnnotationValidator(singletonList(notEmptyRule));
        annotationValidator.validate(entity);

//        verify(notEmptyRule).getAnnotationClass();
//        verify(notEmptyRule).check(any(NotEmpty.class), eq("firstString"), eq(entity.firstString));
//        verify(notEmptyRule).check(any(NotEmpty.class), eq("second"), eq(entity.second));
    }

    private class Entity {
        @NotEmpty
        private String firstString;

        @NotEmpty
        private String second;

        public Entity(String firstString, String second) {
            this.firstString = firstString;
            this.second = second;
        }
    }

}
