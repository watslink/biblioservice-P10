package com.sd.oc.API;

import com.sd.oc.Service.ConfigurationService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan("com.sd.oc.API")
@Import(ConfigurationService.class)
public class ConfigurationAPI {
}
