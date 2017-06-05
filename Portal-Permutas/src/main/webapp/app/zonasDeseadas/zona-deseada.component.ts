import { Component, NgZone } from '@angular/core';
import { Router } from '@angular/router';

import { PlazaService, ZonaDeseadaService, GeocodingService } from '../_services/index';
import { ZonaDeseada, Coincidencia, PlazaPropia, GoogleMapCircle } from '../domain';
import { GoogleMapsAPIWrapper } from 'angular2-google-maps/core';


@Component({
    moduleId: module.id,
    styleUrls: ['zona-deseada.component.css'],
    templateUrl: 'zona-deseada.component.html'
})

export class ZonaDeseadaComponent implements Table<PlazaPropia>{

    // google maps zoom level
    zoom: number = 13;

    // initial center position for the map
    lat: number = 37.362444;
    lng: number = -5.9965;

    zonas: ZonaDeseada[];
    plazas: PlazaPropia[];
    coincidencias: Coincidencia[];
    address: string;
    
    circles: GoogleMapCircle[] = [];

    constructor(
        private zonaDeseadaService: ZonaDeseadaService,
        private plazaService: PlazaService,
        private router: Router,
        private geocodingService: GeocodingService,
        private zone: NgZone) {
        this.getCoincidencias();
        this.getPlazas();
        this.getZonas();
    }

    getPlazas(): void {
        this.plazaService.getPlazas().subscribe(plazas => this.plazas = plazas);
    }

    getZonas(): void {
        this.zonaDeseadaService.getZonas().subscribe(zonas => this.zonas = zonas);
    }

    getCoincidencias(): void {
        this.zonaDeseadaService.checkCoincidencias().subscribe(coincidencias => this.coincidencias = coincidencias);
    }

    proponer(id: string): void {
        this.router.navigate(['/crearPropuesta', id]);
    }

    clickedMarker(label: string, index: number) {
        console.log(`clicked the marker: ${label || index}`)
    }

    deleteZone(id: string) {
        let r = confirm('¿Borrar esta zona?');
        if (r == true) {
            this.zonaDeseadaService.deleteZone(id).then(() => {
                this.getZonas();
                this.getCoincidencias();
            });
        }
    }

    goToZone(lat: number, lng: number) {
        this.lat = lat;
        this.lng = lng;
    }

    search() {
        this.geocodingService.getLatLon(this.address).subscribe(
            data => {
                this.zone.run(() => {
                    this.lat = data.lat();
                    this.lng = data.lng();
                    this.zoom = 18;
                }
            },
            error => {
                console.log(error);
            });
    }

    keyDownFunction($event) {
        if (event.keyCode == 13) {
            this.geocodingService.getLatLon(this.address).subscribe(
                data => {
                    this.zone.run(() => {
                        this.lat = data.lat();
                        this.lng = data.lng();
                        this.zoom = 18;
                    }
            },
                error => {
                    console.log(error);
                });
        }
    }
    
    addCircle(){
        let circle : GoogleMapCircle = {};
        circle.latitude = this.lat;
        circle.longitude = this.lng;
        circle.radius = 300;
        circle.circleDraggable = true;
        circle.editable = true;
        this.circles.push(circle);
    }
    
    changedRadius($event: MouseEvent, circle: GoogleMapCircle){
        console.log($event);
        circle.radius= $event;
    }
    
    changedPosition($event: MouseEvent, circle: GoogleMapCircle){
        console.log($event.coords.lat);
        console.log($event.coords.lng);
        circle.latitude = $event.coords.lat;
        circle.longitude= $event.coords.lng;    
    }
    
    save(){
        this.zonaDeseadaService.createZone(this.circles).subscribe(
            data => {
                this.getZonas();
                this.getCoincidencias();
                this.circles = [];
            },
            error => {
                console.log(error);
            });
    }
    
    changedCircleCenter($event: MouseEvent, circle: GoogleMapCircle){
        circle.latitude = $event.lat;
        circle.longitude= $event.lng;
    }
    
    
    changedCenter($event: MouseEvent){
        this.lat = $event.lat;
        this.lng = $event.lng;
    }

}