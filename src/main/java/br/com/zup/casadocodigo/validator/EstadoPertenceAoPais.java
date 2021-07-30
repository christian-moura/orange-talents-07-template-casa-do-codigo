package br.com.zup.casadocodigo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EstadoPertenceAoPaisValidator.class)

public @interface EstadoPertenceAoPais {

    String message() default "Estado não pertence ao país";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
   // Class<?> entidade();
}
