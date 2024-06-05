import { Component, OnInit, OnDestroy } from '@angular/core';
import { MessageService, ConfirmationService, PrimeNGConfig } from 'primeng/api';
import { ClienteService } from '../services/cliente.service';
import { cloneDeep } from 'lodash';
import { TransacaoService } from '../services/transacao.service';
import { formatStringDateDayMonthYear } from '../util/convert-dates';
import { catchError, of, tap } from 'rxjs';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css'],
  providers: [MessageService, ConfirmationService]
})
export class ClientesComponent implements OnInit, OnDestroy {

  clientList: any[] = [];
  selectedClients: any[] = [];
  clientDialog: boolean = false;
  refreshSubscription: any;

  newClient: any = {};
  selectedClientId: any;

  transferClientDialog: boolean = false;
  valorTransferencia: any;
  selectedClientsCopy: any[] = [];

  extratoDialog: boolean = false;
  extrato: any[] = [];
  saldoConta: any;

  searchText: string = '';

  constructor(
    private clienteService: ClienteService,
    private transacaoService: TransacaoService,
    private ms: MessageService,
    private confirmationService: ConfirmationService,
    private primengConfig: PrimeNGConfig
  ) {}

  ngOnInit() {
    this.loadClients();
    this.startAutoRefresh();
    this.primengConfig.ripple = true;
  }

  ngOnDestroy() {
    this.stopAutoRefresh();
  }

  // Client Methods
  loadClients() {
    this.clienteService.getTodosClientes().pipe(
      tap((data: any) => this.clientList = data),
      catchError((err: any) => this.handleError('Algo deu errado ao buscar os dados.', err))
    ).subscribe();
  }

  openNewClientDialog() {
    this.clientDialog = true;
    this.newClient = {
      nome: '',
      idade: 0,
      email: '',
      numeroConta: '',
      saldo: 0
    };
  }

  hideClientDialog() {
    this.clientDialog = false;
  }

  editClient(clientId: any) {
    this.selectedClientId = clientId;
    this.clienteService.getClienteById(clientId).subscribe(
      (client: any) => {
        this.newClient = { ...client, numeroConta: client.conta.numeroConta, saldo: client.conta.saldo };
        this.clientDialog = true;
      },
      (error) => this.handleError('Erro ao buscar o cliente por ID', error)
    );
  }

  deleteClient(client: any) {
    this.confirmationService.confirm({
      message: "Deseja excluir o registro selecionado?", 
      header: 'Exclusão', 
      icon: 'pi pi-trash',
      accept: () => {
        this.clienteService.deleteCliente(client.id).subscribe(
          () => this.handleSuccess('Cliente excluído com sucesso.', this.loadClients.bind(this)),
          (err: any) => this.handleError('Não foi possível deletar os dados. Verifique se há registros relacionados que impedem a exclusão.', err)
        );
      },
      reject: () => {}
    });
  }

  saveClient() {
    const param = {
      nome: this.newClient.nome,
      idade: this.newClient.idade,
      email: this.newClient.email,
      conta: {
        numeroConta: this.newClient.numeroConta,
        saldo: this.newClient.saldo
      }
    };

    if (this.selectedClientId == null) {
      this.clienteService.postClient(param).subscribe(
        () => this.handleSuccess('Cliente criado com sucesso.', this.loadClients.bind(this)),
        (err: any) => this.handleError('Algo deu errado ao salvar os dados.', err)
      );
    } else {
      this.clienteService.putCliente(this.selectedClientId, param).subscribe(
        () => this.handleSuccess('Cliente editado com sucesso.', this.loadClients.bind(this)),
        (err: any) => this.handleError('Algo deu errado ao editar os dados.', err)
      );
    }

    this.clientDialog = false;
  }

  // Transfer Methods
  openTransferClientDialog() {
    if (this.selectedClients.length === 2) {
      this.selectedClientsCopy = cloneDeep(this.selectedClients);
      this.transferClientDialog = true;
    } else {
      this.showMessage('warn', 'Atenção', 'Selecione exatamente dois clientes para realizar a transferência.');
    }
  }

  cancelTransfer() {
    this.transferClientDialog = false;
  }

  saveTransfer() {
    const transferData = {
      contaOrigemId: this.selectedClientsCopy[0]?.conta?.id,
      contaDestinoId: this.selectedClientsCopy[1]?.conta?.id,
      valor: this.valorTransferencia
    };

    this.transacaoService.postLancarTransferenciaFinanceira(transferData).subscribe(
      () => this.handleSuccess('Transferência realizada com sucesso.', this.loadClients.bind(this)),
      (error) => this.handleError('Ocorreu um erro ao realizar a transferência.', error)
    );

    this.transferClientDialog = false;
  }

  isTransferValid() {
    return this.valorTransferencia && this.valorTransferencia > 0;
  }

  // Extrato Methods
  openExtratoDialog(contaId: any, saldo: any) {
    this.saldoConta = saldo;
    this.extratoDialog = true;
    this.transacaoService.getExtratoContaCliente(contaId).subscribe(
      extrato => this.extrato = extrato
    );
  }

  closeExtratoDialog() {
    this.extratoDialog = false;
  }

  // Util Methods
  startAutoRefresh() {
    this.refreshSubscription = this.clienteService.getClientesAutoRefresh().subscribe(
      data => this.clientList = data,
      error => console.error('Erro ao atualizar clientes:', error)
    );
  }

  stopAutoRefresh() {
    if (this.refreshSubscription) {
      this.refreshSubscription.unsubscribe();
    }
  }

  handleSuccess(detail: string, callback: Function) {
    this.showMessage('success', 'Sucesso', detail);
    callback();
  }

  handleError(detail: string, error: any) {
    console.error(detail, error);
    this.showMessage('error', 'Erro', detail);
    return of([]); 
  }

  showMessage(severity: string, summary: string, detail: string) {
    this.ms.add({ severity, summary, detail });
  }

  formatarData(value: any) {
    return formatStringDateDayMonthYear(value);
  }

  // Search Methods 
  filterByName(event: any) {
    const value = event.target.value.toLowerCase();
    this.clientList = this.clientList.filter((client) => client.nome.toLowerCase().indexOf(value) !== -1);
  }
}
