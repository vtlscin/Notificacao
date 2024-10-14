package com.sistema.notificacao.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean validarCelular(String numero) {
        // Define o padrão regex para o número de celular brasileiro
        String regex = "^\\(?\\+?(55)?\\)?\\s?\\(?\\d{2}\\)?\\s?9\\d{4}-?\\d{4}$";
        // Compila o padrão
        Pattern pattern = Pattern.compile(regex);
        // Aplica o padrão ao número fornecido
        Matcher matcher = pattern.matcher(numero);
        // Retorna true se o número for válido
        return matcher.matches();
    }

}
