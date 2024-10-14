package com.sistema.notificacao.validation;

import com.sistema.notificacao.dto.NotificacaoDto;
import com.sistema.notificacao.model.Canal;
import com.sistema.notificacao.utils.Utils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class ValidacaoCanal implements ConstraintValidator<CanalValidation, NotificacaoDto> {

    List<Canal> canaisDisponiveis = Arrays.asList(Canal.EMAIL,Canal.SMS,Canal.WHATSAPP,Canal.PUSH);

    @Override
    public void initialize(CanalValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(NotificacaoDto notificacaoDto, ConstraintValidatorContext constraintValidatorContext) {

        if(!canaisDisponiveis.contains(notificacaoDto.getCanalEnvio())){
            return false;
        }
        else if(notificacaoDto.getCanalEnvio().equals(Canal.EMAIL) && !notificacaoDto.getDestinatario().contains("@")){
            return false;
        } else if (((notificacaoDto.getCanalEnvio().equals(Canal.WHATSAPP)) || (notificacaoDto.getCanalEnvio().equals(Canal.SMS)))
                  && !Utils.validarCelular(notificacaoDto.getDestinatario())) {
            return false;
        }
        return true;
    }

}
