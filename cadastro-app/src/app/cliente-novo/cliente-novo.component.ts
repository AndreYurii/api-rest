import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  FormGroupDirective,
  NgForm,
  Validators
} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { ApiService } from 'src/app/api.service';
import {calcProjectFileAndBasePath} from "@angular/compiler-cli";

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-cliente-novo',
  templateUrl: './cliente-novo.component.html',
  styleUrls: ['./cliente-novo.component.scss']
})

export class ClienteNovoComponent implements OnInit {

  productForm: FormGroup;

  constructor(private router: Router, private api: ApiService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      'nome' : [null, Validators.required],
      'email' : this.emailFormControl,
      'sexo' : [null],
      'dataDeNascimento' : [null],
      'naturalidade' : [null],
      'nacionalidade' : [null],
      'cpf': this.cpfFormControl,
      'dataDoCadastramento' : [null],
      'dataDaAtualizacaoCadastral' : [null],
    });
  }

  addCliente(form: NgForm) {
    this.isLoadingResults = true;
    this.api.addCliente(form)
      .subscribe(res => {
        const id = res['id'];
        this.isLoadingResults = false;
        //this.router.navigate(['/cliente-detalhe', id]);
        this.router.navigate(['/clientes']);
      }, (err) => {
        console.log(err);
        this.isLoadingResults = false;
      });
  }
//
  static ValidaCpf(controle: AbstractControl){
    const cpf = controle.value;

    let soma: number = 0;
    let resto: number;
    let valido: boolean;

    const regex = new RegExp('[0-9]{11}');

    if (
      cpf == '00000000000' ||
      cpf == '11111111111' ||
      cpf == '22222222222' ||
      cpf == '33333333333' ||
      cpf == '44444444444' ||
      cpf == '55555555555' ||
      cpf == '66666666666' ||
      cpf == '77777777777' ||
      cpf == '88888888888' ||
      cpf == '99999999999' ||
      !regex.test(cpf)
    )
      valido = false;
    else {
      for (let i = 1; i <= 9; i++)
        soma = soma + parseInt(cpf.substring(i - 1, i)) * (11 - i);
      resto = (soma * 10) % 11;

      if (resto == 10 || resto == 11) resto = 0;
      if (resto != parseInt(cpf.substring(9, 10))) valido = false;

      soma = 0;
      for (let i = 1; i <= 10; i++)
        soma = soma + parseInt(cpf.substring(i - 1, i)) * (12 - i);
      resto = (soma * 10) % 11;

      if (resto == 10 || resto == 11) resto = 0;
      if (resto != parseInt(cpf.substring(10, 11))) valido = false;
      valido = true;
    }

    if (valido) return null;

    return { cpfInvalido: true };
  }

  //

  cpfFormControl = new FormControl('', [
    Validators.required,
    ClienteNovoComponent.ValidaCpf
  ])

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);
  matcher = new MyErrorStateMatcher;

  isLoadingResults = false;

}
