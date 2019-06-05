import { Injectable } from '@angular/core';
// import { formatDate, DatePipe, registerLocaleData } from '@angular/common';
// import { formatDate, DatePipe } from '@angular/common';
// import localeES from '@angular/common/locales/es-PE';
// import{ CLIENTES } from './clientes.json';
import { Cliente } from './Cliente';
import { Region } from './region';
// import {of,Observable} from 'rxjs';
//import { Observable} from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
// import { HttpClient, HttpHeaders, HttpRequest, HttpEvent } from '@angular/common/http';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
//import { map, catchError } from 'rxjs/operators';
// import swal from 'sweetalert2';
import { Router } from '@angular/router'
// import { AuthService } from '../usuarios/auth.service';

// @Injectable({
//   providedIn: 'root'
// })
@Injectable()
export class ClienteService {
  private urlEndPoint: string = 'http://localhost:8080/api/clientes';
  // private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(
    private http: HttpClient,
    private router: Router
    // private authService: AuthService
  ) { }

  // private agregarAuthorizationHeader() {
  //   let token = this.authService.token;
  //   if (token != null) {
  //     return this.httpHeaders.append('Authorization', 'Bearer' + token);
  //   }
  //   return this.httpHeaders;
  // }

  // private isNoAutorizado(e): boolean {
  //   // 401: unauthorized, requiere autenticacion
  //   // 403: el servidor denego
  //   if (e.status == 401) {
  //
  //     if (this.authService.isAuthenticated()) {
  //       this.authService.logout();
  //     }
  //     this.router.navigate(['/login']);
  //     return true;
  //   }
  //
  //   if (e.status == 403) {
  //     swal.fire(
  //       'Acceso denegado',
  //       `Hola ${this.authService.usuario.username}, no tienes acceso a este recurso! `,
  //       'warning'
  //     );
  //     this.router.navigate(['/clientes']);
  //     return true;
  //   }
  //   return false;
  // }


  getRegiones(): Observable<Region[]> {
    // return this.http.get<Region[]>(this.urlEndPoint + '/regiones', { headers: this.agregarAuthorizationHeader() }).pipe(
    return this.http.get<Region[]>(this.urlEndPoint + '/regiones');
    // .pipe(
    //   catchError(e => {
    //     this.isNoAutorizado(e);
    //     return throwError(e);
    //   })
    // );
  }

  // getClientes(): Observable<Cliente[]> {
  // getClientes(): Observable<any> {
  getClientes(page: number): Observable<any> {
    //  return of(CLIENTES);
    // return this.http.get<Cliente[]>(this.urlEndPoint);
    // return this.http.get(this.urlEndPoint).pipe(
    return this.http.get(this.urlEndPoint + '/page/' + page).pipe(
      // tap(response => {
      //   let clientes = response as Cliente[];
      //   console.log('ClienteService: tap 1')
      //   clientes.forEach(cliente => {
      //     console.log(cliente.nombre)
      //   })
      // }),
      // tap((response: any) => {
      //   console.log('ClienteService: tap 1');
      //   (response.content as Cliente[]).forEach(cliente => {
      //     console.log(cliente.nombre);
      //   })
      // }),
      // map((response) => response as Cliente[])
      // map(response => {
      //
      //   let clientes = response as Cliente[];
      //   return clientes.map(cliente => {
      //     cliente.nombre = cliente.nombre.toUpperCase();
      //     // registerLocaleData(localeES, 'es-PE');
      //     let datepipe = new DatePipe('es-PE');
      //     // cliente.createAt = datepipe.transform(cliente.createAt, 'fullDate');
      //     // cliente.createAt = datepipe.transform(cliente.createAt,'EEEE dd, MMMM yyyy');
      //     // cliente.createAt = formatDate(cliente.createAt, 'dd-MM-yyyy', 'en-US');
      //     return cliente;
      //   }
      //   );
      // }),
      map((response: any) => {
        (response.content as Cliente[]).map(cliente => {
          cliente.nombre = cliente.nombre.toUpperCase();
          return cliente;
        });
        return response;
      })
      // ,  //DESCOMENTAR
      // tap(response => {
      //   console.log('ClienteService: tap 2');
      //   (response.content as Cliente[]).forEach(cliente => {
      //     console.log(cliente.nombre);
      //   })
      // }
      // )
    );
  }
  //Crear Cliente
  create(cliente: Cliente): Observable<any> {
    // return this.http.post(this.urlEndPoint, cliente, { headers: this.agregarAuthorizationHeader() }).pipe(
    return this.http.post(this.urlEndPoint, cliente).pipe(
      map((response: any) => response.cliente as Cliente),
      catchError(e => {
        // if (this.isNoAutorizado(e)) {
        //   return throwError(e);
        // }
        if (e.status == 400) {
          return throwError(e);
        }
        if( e.error.mensaje){
            console.error(e.error.mensaje);
        }
        // swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
);
  }
  // create(cliente: Cliente): Observable<any> {
  //   return this.http.post<any>(this.urlEndPoint, cliente, { headers: this.httpHeaders }).pipe(
  //     catchError(e => {
  //       console.error(e.error.mensaje);
  //       swal.fire(e.error.mensaje,e.error.error,'error');
  //       return throwError(e);
  //     })
  //   );
  // }
  // create(cliente: Cliente): Observable<Cliente> {
  //   return this.http.post<Cliente>(this.urlEndPoint, cliente, { headers: this.httpHeaders }).pipe(
  //     catchError(e => {
  //       console.error(e.error.mensaje);
  //       swal.fire(e.error.mensaje, e.error.error, 'error');
  //       return throwError(e);
  //     })
  //   );
  // }
  //buscar por ID
  getCliente(id): Observable<Cliente> {
    // return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`, { headers: this.agregarAuthorizationHeader() }).pipe(
    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        if(e.status!=401 && e.error.mensaje){
            this.router.navigate(['/clientes']);
            console.error(e.error.mensaje);
        }
        // this.router.navigate(['/clientes'])
        // console.error(e.error.mensaje);
        // swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError(e);
      }));
  }
  // actualizar Cliente
  update(cliente: Cliente): Observable<any> {
    // return this.http.put<any>(`${this.urlEndPoint}/${cliente.id}`, cliente, { headers: this.agregarAuthorizationHeader() }).pipe(
    return this.http.put<any>(`${this.urlEndPoint}/${cliente.id}`, cliente).pipe(
      catchError(e => {
        // if (this.isNoAutorizado(e)) {
        //   return throwError(e);
        // }
        if (e.status == 400) {
          return throwError(e);
        }
        this.router.navigate(['/clientes'])
        if( e.error.mensaje){
            console.error(e.error.mensaje);
        }
        // swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }
  // update(cliente: Cliente): Observable<Cliente> {
  //   return this.http.put<Cliente>(`${this.urlEndPoint}/${cliente.id}`, cliente, { headers: this.httpHeaders }).pipe(
  //     catchError(e => {
  //       this.router.navigate(['/clientes'])
  //       console.error(e.error.mensaje);
  //       swal.fire(e.error.mensaje, e.error.error, 'error');
  //       return throwError(e);
  //     })
  //   );
  // }
  delete(id: number): Observable<Cliente> {
    // return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`, { headers: this.agregarAuthorizationHeader() }).pipe(
    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {

        // if (this.isNoAutorizado(e)) {
        //   return throwError(e);
        // }

        this.router.navigate(['/clientes'])
        if( e.error.mensaje){
            console.error(e.error.mensaje);
        }
        // swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  // subirFoto(archivo: File, id): Observable<Cliente> {
  //   let formData = new FormData();
  //   formData.append("archivo", archivo);
  //   formData.append("id", id);
  //
  //   return this.http.post(`${this.urlEndPoint}/upload`, formData).pipe(
  //     map((response: any) => response.cliente as Cliente),
  //     catchError(e => {
  //       console.error(e.error.mensaje);
  //       swal.fire(e.error.mensaje, e.error.error, 'error');
  //       return throwError(e);
  //     })
  //
  //   );
  // }

  subirFoto(archivo: File, id: any): Observable<HttpEvent<{}>> {
    let formData = new FormData();
    formData.append("archivo", archivo);
    formData.append("id", id);

    // let httpHeaders = new HttpHeaders();
    // let token = this.authService.token;
    // if (token != null) {
    //   httpHeaders = httpHeaders.append('Authorization', 'Bearer' + token);
    // }

    // const req = new HttpRequest('POST', `${this.urlEndPoint}/upload`, formData, {
    //   reportProgress: true,
    //   headers: httpHeaders
    // });
    const req = new HttpRequest('POST', `${this.urlEndPoint}/upload`, formData, {
      reportProgress: true
    });
    return this.http.request(req);
    // .pipe(
    //   catchError(e => {
    //     this.isNoAutorizado(e);
    //     return throwError(e);
    //   })
    // );
  }

}
