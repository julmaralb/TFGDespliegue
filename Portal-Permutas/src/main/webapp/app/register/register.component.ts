import { Component, Directive } from '@angular/core';
import { Router } from '@angular/router';


import { AlertService, UserService, GeocodingService } from '../_services/index';





@Component({
    moduleId: module.id,
    templateUrl: 'register.component.html',
})

export class RegisterComponent {
    model: any = {};
    loading = false;
    color = 'primary';
  	mode = 'determinate';
  	value = 50;

    constructor(
        private router: Router,
        private userService: UserService,
        private alertService: AlertService,
        private geocodingService: GeocodingService) { }

    register() {
    	this.loading = true;
    	this.geocodingService.getLatLon(this.model.direccion).subscribe(
    		data => {
    			this.model.latitud= data.lat();
                this.model.longitud = data.lng();
                this.userService.create(this.model)
            		.subscribe(
                		data => {
                    		this.alertService.success('Registration successful', true);
                    		this.router.navigate(['/login']);
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