package br.com.zup.casadocodigo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailUnicoValidator.class)
public @interface EmailUnico {
    String message() default "Email indisponível. Por favor, informe outro.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}