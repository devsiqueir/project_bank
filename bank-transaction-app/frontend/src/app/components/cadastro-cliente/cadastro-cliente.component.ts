import { Component } from '@angular/core';
import { ClienteService } from '../../services/cliente.service';


@Component({
  selector: 'app-cadastro-cliente',
  templateUrl: './cadastro-cliente.component.html',
  styleUrls: ['./cadastro-cliente.component.css']
})
export class CadastroClienteComponent {
  nome: string;
  idade: number;
  email: string;
  numeroConta: string;

  constructor(private clienteService: ClienteService) {}

  cadastrarCliente() {
    const novoCliente = {
      nome: this.nome,
      idade: this.idade,
      email: this.email,
      numeroConta: this.numeroConta
    };
    this.clienteService.cadastrarCliente(novoCliente).subscribe(response => {
      console.log('Cliente cadastrado com sucesso!', response);
      
      this.nome = '';
      this.idade = 0;
      this.email = '';
      this.numeroConta = '';
    }, error => {
      console.error('Erro ao cadastrar cliente:', error);
    });
  }
}
