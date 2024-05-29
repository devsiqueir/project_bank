package com.ibm.banco.controlador;


import com.ibm.banco.dto.TransacaoRequestDTO;
import com.ibm.banco.modelo.Transacao;
import com.ibm.banco.servico.TransacaoServico;
import com.ibm.banco.util.SwaggerStatusHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Transações", description = "API de Transações")
@RestController
@RequestMapping("/transacoes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TransacaoControlador {

    @Autowired
    private TransacaoServico transacaoServico;

    @Operation(summary = "Obter dados do extrato da conta por cliente")
    @ApiResponses(value = {@ApiResponse(responseCode = SwaggerStatusHelper.HTTP_BAD_REQUEST, description = SwaggerStatusHelper.BAD_REQUEST_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_ERROR, description = SwaggerStatusHelper.INTERNAL_SERVER_ERROR_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_NOT_CONTENT, description = SwaggerStatusHelper.NOT_CONTENT_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_OK, description = SwaggerStatusHelper.OK_STRING),
    })    @GetMapping
    public ResponseEntity<List<Transacao>> listarTransacoesDoCliente(@RequestParam Long contaId) {
        List<Transacao> extratoContaCliente = transacaoServico.obterExtratoContaCliente(contaId);
        return ResponseEntity.ok(extratoContaCliente);
    }

    @Operation(summary = "Obter dados do relatório de transações no dia")
    @ApiResponses(value = {@ApiResponse(responseCode = SwaggerStatusHelper.HTTP_BAD_REQUEST, description = SwaggerStatusHelper.BAD_REQUEST_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_ERROR, description = SwaggerStatusHelper.INTERNAL_SERVER_ERROR_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_NOT_CONTENT, description = SwaggerStatusHelper.NOT_CONTENT_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_OK, description = SwaggerStatusHelper.OK_STRING),
    })    @GetMapping("/relatorio")
    public ResponseEntity<List<Transacao>> listarTransacoesNoDia(@RequestParam LocalDate date) {
        List<Transacao> transacoesDoDia = transacaoServico.obterRelatorioTransacoesDoDia(date);
        return ResponseEntity.ok(transacoesDoDia);
    }

    @Operation(summary = "Lançar transferência financeiras entre contas")
    @ApiResponses(value = {@ApiResponse(responseCode = SwaggerStatusHelper.HTTP_BAD_REQUEST, description = SwaggerStatusHelper.BAD_REQUEST_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_ERROR, description = SwaggerStatusHelper.INTERNAL_SERVER_ERROR_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_NOT_CONTENT, description = SwaggerStatusHelper.NOT_CONTENT_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_OK, description = SwaggerStatusHelper.OK_STRING),
    })    @PostMapping
    public ResponseEntity<TransacaoRequestDTO> efetuarTransacao(@Valid @RequestBody TransacaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoServico.realizarTransferencia(dto));
    }
}
