import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import { ModalService } from './detalle/modal.service';
import swal from 'sweetalert2'
import { tap } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';
import {AuthService} from '../usuarios/auth.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html'
})
export class ClientesComponent implements OnInit {

  clientes: Cliente[];
  paginador: any;
  clienteSeleccionado: Cliente;
  // private clienteService: ClienteService;

  constructor(
    private modalService: ModalService,
    public clienteService: ClienteService, // public para pase a produccion
    public authService:AuthService, // public para pase a produccion
    private activatedRoute: ActivatedRoute) {
    // this.clienteService=clienteService;
  }

  // ngOnInit() {
  //   // this.clientes = this.clienteService.getCliente  s();
  //   this.clienteService.getClientes().pipe(
  //     tap(clientes => {
  //       this.clientes=clientes;
  //       console.log('ClientesComponent: tap3 ')
  //       clientes.forEach(cliente =>{
  //       console.log(cliente.nombre);
  //     });
  //     })
  //   ).subscribe(clientes => this.clientes= clientes
  //         // clientes => this.clientes=clientes
  //   );
  // }
  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      let page: number = +params.get('page');

      if (!page) {
        page = 0
      }
      this.clienteService.getClientes(page)
        // .pipe(
        //   tap(response => {
        //     (response.content as Cliente[]).forEach(
        //       cliente => console.log(cliente.nombre));
        //   })
        // )
        .subscribe(response => {
          this.clientes = response.content as Cliente[];
          this.paginador = response;
        });
    });

    this.modalService.notificarUpload.subscribe(cliente => {
      this.clientes = this.clientes.map(clienteOriginal => {
        if (cliente.id == clienteOriginal.id) {
          clienteOriginal.foto = cliente.foto;
        }
        return clienteOriginal;
      })
    });
  }




  delete(cliente: Cliente): void {
    const swalWithBootstrapButtons = swal.mixin({
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      buttonsStyling: false,
    })

    swalWithBootstrapButtons.fire({
      title: 'Esta seguro?',
      text: `¿Seguro que desea eliminar al cliente ${cliente.nombre} ?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, Eliminar',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {

        this.clienteService.delete(cliente.id).subscribe(
          response => {
            this.clientes = this.clientes.filter(cli => cli !== cliente)
            swal.fire(
              'Cliente Eliminado',
              `Cliente ${cliente.nombre} eliminado con éxito `,
              'success'
            )
          }
        )

        // swalWithBootstrapButtons.fire(
        //   'Deleted!',
        //   'Your file has been deleted.',
        //   'success'
        // )
      }
    })
  }

  abrirModal(cliente: Cliente) {
    this.clienteSeleccionado = cliente;
    this.modalService.abrirModal();
  }
}
