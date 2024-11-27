package com.igomarcelino.gerenciamento_ordem_de_servico.Enum;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Formas de pagamento disponíveis")
public enum FormaPagamento {
    @Schema(description = "Pagamento cartao de credito")
    CREDITO,
    @Schema(description = "Pagamento cartao de debito")
    DEBITO,
    @Schema(description = "Pagamento com Boleto")
    BOLETO,
    @Schema(description = "Pagamento com Pix")
    PIX,
    @Schema(description = "Pagamento em dinheiro")
    DINHEIRO
}
