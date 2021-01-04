import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'app-cliente-editar',
  templateUrl: './cliente-editar.component.html',
  styleUrls: ['./cliente-editar.component.scss']
})
export class ClienteEditarComponent implements OnInit {

  _id: String = '';
  productForm: FormGroup;
  nome: String = '';
  email: String = '';
  sexo: string = '';
  isLoadingResults = false;
  constructor(private router: Router, private route: ActivatedRoute, private api: ApiService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getCliente(this.route.snapshot.params['id']);
    this.productForm = this.formBuilder.group({
      'nome' : [null, Validators.required],
      'email' : [null],
      'sexo' : [null],
      'dataDeNascimento' : [null],
      'naturalidade' : [null],
      'nacionalidade' : [null],
      'cpf' : [null]
    });
  }

  getCliente(id) {
    this.api.getCliente(id).subscribe(data => {
      this._id = data.id;
      this.productForm.setValue({
        nome: data.nome,
        email: data.email,
        sexo: data.sexo
      });
    });
  }

  updateCliente(form: NgForm) {
    this.isLoadingResults = true;
    this.api.updateCliente(this._id, form)
      .subscribe(res => {
          this.isLoadingResults = false;
          this.router.navigate(['/cliente-detalhe/' + this._id]);
        }, (err) => {
          console.log(err);
          this.isLoadingResults = false;
        }
      );
  }

}
