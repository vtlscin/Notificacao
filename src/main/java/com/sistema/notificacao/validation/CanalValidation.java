package com.sistema.notificacao.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidacaoCanal.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CanalValidation {

    String message() default "Canal inadequado para destinatario";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
