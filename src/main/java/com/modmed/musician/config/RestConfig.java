package com.modmed.musician.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import java.time.LocalDate;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {
  @Override
  public void configureConversionService(ConfigurableConversionService conversionService) {
    conversionService.addConverter(String.class, LocalDate.class, LocalDate::parse);
  }
}
