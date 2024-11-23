package com.igomarcelino.gerenciamento_ordem_de_servico;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url = "/",description = "default Server URL")},info = @Info(title = "Gerenciamento-Ordem-De-Servico", version = "1", description = "API desenvolvida para pratica do Spring"))
public class GerenciamentoOrdemDeServicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoOrdemDeServicoApplication.class, args);
	}

}
