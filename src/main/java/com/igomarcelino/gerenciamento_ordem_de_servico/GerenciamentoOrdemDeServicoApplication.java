package com.igomarcelino.gerenciamento_ordem_de_servico;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//Ajusta a URL para que o swagger funcione
@OpenAPIDefinition(servers = {@Server(url = "/",description = "default Server URL")},info = @Info(title = "Gerenciamento-Ordem-De-Servico", version = "1", description = "API desenvolvida para pratica do Spring"))
@EnableAsync
public class GerenciamentoOrdemDeServicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoOrdemDeServicoApplication.class, args);
	}

}
