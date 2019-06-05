import{Component} from '@angular/core';
import {AuthService} from '../usuarios/auth.service';
import {Router} from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent {
  title:string ='App Angular';

  constructor(
    public authService:AuthService,  // public para pase a produccion
    private router:Router){}

logout():void{
  let username=this.authService.usuario.username;
this.authService.logout();
swal.fire(
  'logout',
  `Hola ${username}, has cerrado sesion con exito! `,
  'success')
  this.router.navigate(['/login']);
}

}
