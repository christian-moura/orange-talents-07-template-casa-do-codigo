package br.com.zup.casadocodigo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValorUnicoValidator.class)
public @interface ValorUnico {

    String message() default "Atributo indisponível para cadastro. Informe outro";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
    Class<?> entidade();
    String atributo() ;
}
