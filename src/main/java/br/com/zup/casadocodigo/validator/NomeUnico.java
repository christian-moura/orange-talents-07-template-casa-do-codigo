package br.com.zup.casadocodigo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NomeUnicoValidator.class)
public @interface NomeUnico {
    String message() default "Nome de categoria indisponível. Informe outro, por favor.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
