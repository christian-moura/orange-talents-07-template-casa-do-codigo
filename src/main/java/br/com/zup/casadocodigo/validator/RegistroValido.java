package br.com.zup.casadocodigo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = RegistroValidoValidator.class)
public @interface RegistroValido {

    String message() default "Registro Inexistente/Inválido";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
    Class<?> entidade();
}
