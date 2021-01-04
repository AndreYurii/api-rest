import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'app-cliente-novo',
  templateUrl: './cliente-novo.component.html',
  styleUrls: ['./cliente-novo.component.scss']
})
export class ClienteNovoComponent implements OnInit {

  productForm: FormGroup;
  isLoadingResults = false;
  constructor(private router: Router, private api: ApiService, private formBuilder: FormBuilder) { }

  addCliente(form: NgForm) {
    this.isLoadingResults = true;
    this.api.addCliente(form)
      .subscribe(res => {
        const id = res['id'];
        this.isLoadingResults = false;
        this.router.navigate(['/cliente-detalhe', id]);
      }, (err) => {
        console.log(err);
        this.isLoadingResults = false;
      });
  }

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      'nome' : [null, Validators.required],
      'email' : [null, Validators.required],
      'sexo' : [null],
      'dataDeNascimento' : [null],
      'naturalidade' : [null],
      'nacionalidade' : [null],
      'cpf' : [null],
      'dataDoCadastramento' : [null],
      'dataDaAtualizacaoCadastral' : [null]
    });
  }

}
