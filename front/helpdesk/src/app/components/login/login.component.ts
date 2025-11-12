import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Credenciais } from 'src/app/models/credenciais';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  creds: Credenciais = {
    email: '',
    senha: ''
  }

  email = new FormControl(null, Validators.email);
  senha = new FormControl(null, Validators.minLength(3));

  constructor(
    private toastService: ToastrService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  logar() {
    this.authService.authenticate(this.creds).subscribe(res => {
      this.authService.successfullyLogin(res.headers.get('Authorization').substring(7));
      this.router.navigate(['']);
    }, () => {
      this.toastService.error('Usuário e/ou senhas inválidos.');
    })
  }

  validaCampos(): boolean {
    return this.email.valid && this.senha.valid;
  }

}
