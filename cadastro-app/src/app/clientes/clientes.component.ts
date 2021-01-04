import { Component, OnInit } from '@angular/core';
import { ApiService } from "../api.service";
import { Cliente } from 'src/app/model/cliente'

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.scss']
})
export class ClientesComponent implements OnInit {

  private isLoadingResults: boolean;

  displayedColumns: string[] = [ 'nome', 'email', 'sexo', 'dataDeNascimento', 'naturalidade', 'nacionalidade', 'cpf', 'acao' ];
  dataSource: Cliente[];

  constructor(private _api: ApiService) { }

  ngOnInit(): void {
    this._api.getClientes()
      .subscribe(res => {
        this.dataSource = res;
        console.log(this.dataSource);
        this.isLoadingResults = false;
      }, err => {
        console.log(err);
        this.isLoadingResults = false;
      });
  }

}
