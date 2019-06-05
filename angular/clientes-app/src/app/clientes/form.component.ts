import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { Region } from './region';
import { ClienteService } from './cliente.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  public cliente: Cliente = new Cliente(); // public para pase a produccion
  regiones: Region[];
  titulo: string = "Crear Cliente"
  errores: string[];

  constructor(
    private clienteService: ClienteService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.cargarCliente();
    this.clienteService.getRegiones().subscribe(regiones => {
      this.regiones = regiones;
    });
  }

  // cargarCliente(): void {
  //   this.activatedRoute.params.subscribe(params => {
  //     let id = params['id']
  //     // let id = +params.get('id');
  //     if (id) {
  //       this.clienteService.getCliente(id).subscribe((cliente) => this.cliente = cliente)
  //     }
  //   })
  // }
  cargarCliente(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      let id = +params.get('id');
      if (id) {
        this.clienteService.getCliente(id).subscribe((cliente) => this.cliente = cliente);
      }
    })
  }

  create(): void {
    console.log("clicked!")
    console.log(this.cliente);

    this.clienteService.create(this.cliente)
      .subscribe(cliente => {
        this.router.navigate(['./clientes'])
        swal.fire('Nuevo Cliente', `El Cliente ${cliente.nombre} ${cliente.apellido} ha sido  creado con éxito!`, 'success')
        // swal.fire('Nuevo Cliente', `Cliente ${json.cliente.nombre} ${json.cliente.apellido}  creado con éxito!`, 'success')
      },
        err => {
          this.errores = err.error.errors as string[];
          console.error('Código del error desde el backend: ' + err.status);
          console.error(err.error.errors);
        }
      );
  }
  // create(): void {
  //   console.log("clicked!")
  //   console.log(this.cliente)
  //
  //   this.clienteService.create(this.cliente)
  //     .subscribe(json => {
  //       this.router.navigate(['./clientes'])
  //               swal.fire('Nuevo Cliente', `${json.mensaje}: ${json.cliente.nombre}`, 'success')
  //       // swal.fire('Nuevo Cliente', `Cliente ${json.cliente.nombre} ${json.cliente.apellido}  creado con éxito!`, 'success')
  //     }
  //     );
  // }
  // create(): void {
  //   console.log("clicked!")
  //   console.log(this.cliente)
  //
  //   this.clienteService.create(this.cliente)
  //     .subscribe(cliente => {
  //       this.router.navigate(['./clientes'])
  //       swal.fire('Nuevo Cliente', `Cliente ${cliente.nombre} ${cliente.apellido}  creado con éxito!`, 'success')
  //     }
  //     );
  // }
  update(): void {
    console.log(this.cliente);

    this.cliente.facturas = null;

    this.clienteService.update(this.cliente)
      .subscribe(json => {
        this.router.navigate(['/clientes']);
        // let datepipe = new DatePipe('es-PE');
        // json.cliente.createAt = datepipe.transform(this.cliente.createAt,'yyyy-MM-dd');
        swal.fire('Cliente Actualizado', `${json.mensaje}: ${json.cliente.nombre}`, 'success');
      },
        err => {
          this.errores = err.error.errors as string[];
          console.error('Código del error desde el backend: ' + err.status);
          console.error(err.error.errors);
        }

      )
  }
  // update(): void {
  //   this.clienteService.update(this.cliente)
  //     .subscribe(cliente => {
  //       this.router.navigate(['/clientes'])
  //       swal.fire('Cliente Actualizado', `Cliente ${cliente.nombre} actualizado con exito!`, 'success')
  //     }
  //
  //     )
  // }
  compararRegion(o1: Region, o2: Region): boolean {

    if (o1 === undefined && o2 === undefined) {
      return true;
    }

    return o1 && o2 ? o1.id === o2.id : o1 === o2;
    //  return o1===null || o2===null || o1=== undefined || o2===undefined ? false:o1.id===o2.id ;
    //  return o1==null || o2==null ? false:o1.id===o2.id ;
  }
}
