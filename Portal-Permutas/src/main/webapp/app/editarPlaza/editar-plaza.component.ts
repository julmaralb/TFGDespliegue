import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AlertService, PlazaService } from '../_services/index';
import { PlazaPropia } from '../domain';
import { GoogleMapsAPIWrapper, MapsAPILoader } from 'angular2-google-maps/core';
import { Observer } from 'rxjs/Observer';
import { Observable } from 'rxjs/Observable';

declare var google: any;

@Component({
    moduleId: module.id,
    templateUrl: 'editar-plaza.component.html',
    styleUrls: ['../login/login.component.css']
})

export class EditarPlazaComponent {
    model: any = {};
    loading = false;
    plaza: PlazaPropia;
    color = 'primary';
    mode = 'indeterminate';
    value = 100;
    cambios = false;


    constructor(private router: Router, private plazaService: PlazaService, private alertService: AlertService, private _wrapper: GoogleMapsAPIWrapper, private __loader: MapsAPILoader) {
        __loader.load().then(() => {
            console.log('google script loaded');
        });
        this.getPlaza();
    }

    getPlaza(): void {
        this.plazaService.getPrincipal().subscribe(plaza => this.plaza = plaza);
    }

    getLatLon(address: string) {
        console.log('Getting Address - ', address);
        let geocoder = new google.maps.Geocoder();
        return Observable.create(observer => {
            geocoder.geocode({ 'address': address }, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    observer.next(results[0].geometry.location);
                    observer.complete();
                } else {
                    console.log('Error - ', results, ' & Status - ', status);
                    observer.next({});
                    observer.complete();
                }
            });
        })
    }

    editar() {
        this.setLatLon(this.plaza.direccion);
    }

    setLatLon(address: string) {
        this.loading = true;
        this.getLatLon(address).subscribe(
            data => {
                this.plaza.latitud = data.lat();
                this.plaza.longitud = data.lng();
                this.plazaService.update(this.plaza)
                    .subscribe(
                    data => {
                        this.loading = false;
                        this.cambios = true;
                    },
                    error => {
                        this.alertService.error(error);
                        this.loading = false;
                    });
            },
            error => {
                console.log(error);
            });
    }
}