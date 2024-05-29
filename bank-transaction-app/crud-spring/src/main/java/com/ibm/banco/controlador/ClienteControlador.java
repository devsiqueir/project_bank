package com.ibm.banco.controlador;

import com.ibm.banco.modelo.Cliente;
import com.ibm.banco.servico.ClienteServico;
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

import java.util.List;

@Tag(name = "Clientes", description = "API de Clientes")
@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteControlador {

    @Autowired
    private ClienteServico clienteServico;

    @Operation(summary = "Obter todos os clientes")
    @ApiResponses(value = {@ApiResponse(responseCode = SwaggerStatusHelper.HTTP_BAD_REQUEST, description = SwaggerStatusHelper.BAD_REQUEST_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_ERROR, description = SwaggerStatusHelper.INTERNAL_SERVER_ERROR_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_NOT_CONTENT, description = SwaggerStatusHelper.NOT_CONTENT_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_OK, description = SwaggerStatusHelper.OK_STRING),
    })
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteServico.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Obter cliente por Id")
    @ApiResponses(value = {@ApiResponse(responseCode = SwaggerStatusHelper.HTTP_BAD_REQUEST, description = SwaggerStatusHelper.BAD_REQUEST_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_ERROR, description = SwaggerStatusHelper.INTERNAL_SERVER_ERROR_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_NOT_CONTENT, description = SwaggerStatusHelper.NOT_CONTENT_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_OK, description = SwaggerStatusHelper.OK_STRING),
    })    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        return clienteServico.buscarClientePorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar cliente")
    @ApiResponses(value = {@ApiResponse(responseCode = SwaggerStatusHelper.HTTP_BAD_REQUEST, description = SwaggerStatusHelper.BAD_REQUEST_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_ERROR, description = SwaggerStatusHelper.INTERNAL_SERVER_ERROR_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_NOT_CONTENT, description = SwaggerStatusHelper.NOT_CONTENT_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_OK, description = SwaggerStatusHelper.OK_STRING),
    })    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteServico.salvarClienteComConta(cliente));
    }

    @Operation(summary = "Editar cliente")
    @ApiResponses(value = {@ApiResponse(responseCode = SwaggerStatusHelper.HTTP_BAD_REQUEST, description = SwaggerStatusHelper.BAD_REQUEST_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_ERROR, description = SwaggerStatusHelper.INTERNAL_SERVER_ERROR_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_NOT_CONTENT, description = SwaggerStatusHelper.NOT_CONTENT_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_OK, description = SwaggerStatusHelper.OK_STRING),
    })    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente novoCliente) {
        Cliente clienteAtualizado = clienteServico.editarClienteComConta(id, novoCliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @Operation(summary = "Excluir cliente")
    @ApiResponses(value = {@ApiResponse(responseCode = SwaggerStatusHelper.HTTP_BAD_REQUEST, description = SwaggerStatusHelper.BAD_REQUEST_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_ERROR, description = SwaggerStatusHelper.INTERNAL_SERVER_ERROR_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_NOT_CONTENT, description = SwaggerStatusHelper.NOT_CONTENT_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_OK, description = SwaggerStatusHelper.OK_STRING),
    })    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        clienteServico.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }
}
