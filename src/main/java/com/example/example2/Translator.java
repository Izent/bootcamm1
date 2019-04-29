package com.example.example2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component//para marcar los beans como componentes gestionados de Spring
public class Translator {

  private static ResourceBundleMessageSource messageSource;

  @Autowired
//inyc depend
  Translator(ResourceBundleMessageSource messageSource) {
    Translator.messageSource = messageSource;
  }

  //obtiene el header y reporta el mensaje segun el idioma
  public static String toLocale(String msgCode) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(msgCode, null, msgCode, locale);
  }
}
