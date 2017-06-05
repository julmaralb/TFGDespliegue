import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AlertService, UserService } from '../_services/index';
import { User } from '../_models/index';

@Component({
    moduleId: module.id,
    templateUrl: 'editar-usuario.component.html',
    styleUrls: ['../login/login.component.css']
})

export class EditarUsuarioComponent {
    model: any = {};
    loading = false;
    principal: User;
    color = 'primary';
  	mode = 'determinate';
  	value = 50;
  	cambios = false;
    

    constructor(private router: Router, private userService: UserService, private alertService: AlertService) { 
    	this.getUser();
    }
    
    getUser(): void {
    	this.userService.getPrincipal().subscribe(principal => this.principal = principal);
  	}

    editar() {
        this.loading = true;
        this.userService.update(this.principal)
            .subscribe(
                data => {
                	this.loading = false;
                    this.cambios = true;
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
}