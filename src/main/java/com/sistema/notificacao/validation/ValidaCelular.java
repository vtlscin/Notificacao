package com.sistema.notificacao.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidacaoCelular.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidaCelular {

    String message() default "Celular em formato inadequado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
