package br.com.fiap.ia_learning.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

@Configuration
public class I18nConfig extends AcceptHeaderLocaleResolver {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getHeader("Accept-Language");

        if (lang == null || lang.isBlank()) {
            return Locale.forLanguageTag("pt-BR");
        }

        return Locale.forLanguageTag(lang);
    }
}
