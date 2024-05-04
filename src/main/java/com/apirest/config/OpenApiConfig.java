package com.apirest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
		info = @Info(
				title = "API Rest",
				description = "ejercicio de una API restfull creado con Spring boot",
				version = "0.0.1",
				license = @License(
						name = "Creative Commons NC",
						url = "https://creativecommons.org/licenses/by-nc/4.0/"
				)
		)
)
public class OpenApiConfig {

}
