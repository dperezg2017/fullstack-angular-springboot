import { Component, OnInit, Input } from '@angular/core';
import { Cliente } from '../cliente';
import { ClienteService } from '../cliente.service';
import { ModalService } from './modal.service';
// import { ActivatedRoute } from '@angular/router'; lo remplazara ModalService
import swal from 'sweetalert2';
import { HttpEventType } from '@angular/common/http';
import { AuthService } from '../../usuarios/auth.service';
import {FacturaService} from '../../facturas/services/factura.service';
import {Factura} from '../../facturas/models/factura';

@Component({
  selector: 'detalle-cliente',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {
  @Input() cliente: Cliente;
  titulo: string = "Detalle del cliente";
  private fotoSeleccionada: File;
  progreso: number = 0;

  constructor(
    private clienteService: ClienteService,
    public modalService: ModalService, // public para pase a produccion
    public authService: AuthService,  // public para pase a produccion
    private facturaService:FacturaService ) { }
  // private activatedRoute: ActivatedRoute) { }

  ngOnInit() {

  }
  // se comenta por que se hara con MODAL boostrap, yse uso el decorador @Input ()
  // ngOnInit() {
  //   this.activatedRoute.paramMap.subscribe(params => {
  //     let id: number = +params.get('id');
  //     if (id) {
  //       this.clienteService.getCliente(id).subscribe(cliente => {
  //         this.cliente = cliente;
  //       });
  //     }
  //   }
  //
  //   );
  // }

  seleccionarFoto(event) {
    this.fotoSeleccionada = event.target.files[0];
    this.progreso = 0;
    console.log(this.fotoSeleccionada);
    if (this.fotoSeleccionada.type.indexOf('image') < 0) {
      swal.fire('Error seleccionar imagen: ', ' El archivo debe ser de tipo imagen', 'error');
      this.fotoSeleccionada = null;
    }
  }

  // subirFoto() {
  //   if (!this.fotoSeleccionada) {
  //     swal.fire('Error Upload: ', ' debe seleccionar una foto', 'error');
  //   } else {
  //     this.clienteService.subirFoto(this.fotoSeleccionada, this.cliente.id)
  //       .subscribe(cliente => {
  //         this.cliente = cliente;
  //         swal.fire('La foto se ha subido completamente!', `La foto se ha subido con éxito: ${this.cliente.foto} `, 'success');
  //       })
  //   }
  // }

  subirFoto() {
    if (!this.fotoSeleccionada) {
      swal.fire('Error Upload: ', ' debe seleccionar una foto', 'error');
    } else {
      this.clienteService.subirFoto(this.fotoSeleccionada, this.cliente.id)
        .subscribe(event => {
          if (event.type === HttpEventType.UploadProgress) {
            this.progreso = Math.round((event.loaded / event.total) * 100);
          } else if (event.type === HttpEventType.Response) {
            let response: any = event.body;
            this.cliente = response.cliente as Cliente;

            this.modalService.notificarUpload.emit(this.cliente);

            swal.fire('La foto se ha subido completamente!', response.mensaje, 'success');
          }
        })
    }
  }

  cerrarModal() {
    this.modalService.cerrarModal();
    this.fotoSeleccionada = null;
    this.progreso = 0;
  }

  delete(factura:Factura):void{
    swal.fire({
      title: 'Esta seguro?',
      text: `¿Seguro que desea eliminar la factura ${factura.descripcion} ?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, Eliminar',
      cancelButtonText: 'No, cancelar!',
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass:'btn btn-danger',
      buttonsStyling: false,
      reverseButtons: true
    }).then((result) => {
      if (result.value) {

        this.facturaService.delete(factura.id).subscribe(
          () => {
            this.cliente.facturas = this.cliente.facturas.filter(f => f !== factura)
            swal.fire(
              'Factura Eliminado',
              `Factura ${factura.descripcion} eliminada con éxito `,
              'success'
            )
          }
        )
      }
    })
  }

}
