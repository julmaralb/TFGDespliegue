import { Component, OnInit } from '@angular/core';
import { Response } from '@angular/http';
import { Router} from '@angular/router';
import * as Rx from 'rxjs/Rx';

import 'rxjs/add/operator/switchMap';

import { PropuestaService, PlazaService, AuthenticationService } from '../_services/index';
import { Propuesta, PlazaPropia } from '../domain';


@Component({
    moduleId: module.id,
    styles: [`
    .sebm-google-map-container {
       height: 600px;
     }
  `],
    templateUrl: 'listar-propuestas-recibidas.component.html'
    
})
export class ListarPropuestasRecibidasComponent implements OnInit {

    loading = false;
    propuestas: Propuesta[];
    msg: string;
    plazaRemitente: PlazaPropia;
    textoPropuesta: string;
    color = 'primary';
    mode = 'indeterminate';
    value = 100;

    // initial center position for the map
    lat: number = 37.362444;
    lng: number = -5.9965;   
    zoom: number = 13;

    constructor(private propuestaService: PropuestaService, private router: Router, private plazaService: PlazaService, private authenticationService: AuthenticationService) {
    }

    ngOnInit() {
        this.getPropuestasRecibidas();
    }

    getPropuestasRecibidas(): void {
        this.propuestaService.getPropuestasRecibidas().subscribe(propuestas => { this.propuestas = propuestas });
    }

    getPlazaRemitente(id: string, texto: string) {
    	this.textoPropuesta = texto;
        this.plazaService.getPlazaById(id).subscribe(plazaRemitente => this.plazaRemitente = plazaRemitente);
    }
    
    goToZone(lat: number, lng: number){
        this.lat = lat;
        this.lng = lng;
        this.zoom = 15;
        this.cerrar();
    }
    
    cerrar(){
        this.plazaRemitente = null;
    }

    aceptar(id: string) {
        this.loading = true;
        this.propuestaService.aceptarPropuesta(id).subscribe(
            data => {
                this.loading = false;
                this.getPropuestasRecibidas();
                this.authenticationService.reseteaContador();
            },
            error => {
                this.loading = false;
            	console.log(error);
            });
    }

    rechazar(id: string) {
        this.loading = true;
        this.propuestaService.rechazarPropuesta(id).subscribe(
            data => {
                this.loading = false;
                this.getPropuestasRecibidas();
                this.authenticationService.notificacionVista();
            },
            error => {
                this.loading = false;
                console.log(error);
            });
    }
    
    changedCenter($event: MouseEvent){
        this.lat = $event.lat;
        this.lng = $event.lng;
    }

    clickedMarker(label: string, index: number) {
        console.log(`clicked the marker: ${label || index}`)
    }

}