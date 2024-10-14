package com.sistema.notificacao.validation;

import com.sistema.notificacao.dto.NotificacaoDto;
import com.sistema.notificacao.model.Canal;
import com.sistema.notificacao.utils.Utils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidacaoCelular implements ConstraintValidator<ValidaCelular, NotificacaoDto> {
    @Override
    public void initialize(ValidaCelular constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(NotificacaoDto notificacaoDto, ConstraintValidatorContext constraintValidatorContext) {
        return (!notificacaoDto.getCanalEnvio().equals(Canal.WHATSAPP) && !notificacaoDto.getCanalEnvio().equals(Canal.SMS))
                || Utils.validarCelular(notificacaoDto.getDestinatario());
    }
}
