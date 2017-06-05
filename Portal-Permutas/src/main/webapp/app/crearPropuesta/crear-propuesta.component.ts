import { Component, OnInit }      from '@angular/core';
import { Component, Input } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { AlertService, PropuestaService, PlazaService } from '../_services/index';
import { Propuesta } from '../_models/index';

@Component({
    moduleId: module.id,
    templateUrl: 'crear-propuesta.component.html',
    styleUrls: ['../login/login.component.css']
})

export class CrearPropuestaComponent implements OnInit {

    propuesta: Propuesta = {};
    loading = false;
    id: string;
    color = 'primary';
    mode = 'determinate';
    value = 50;
    plaza: PlazaPropia = {};
    prohibido = false;

    constructor(
        private router: Router, 
        private route: ActivatedRoute, 
        private propuestaService: PropuestaService, 
        private plazaService: PlazaService, 
        private alertService: AlertService) {}

    ngOnInit() {
        this.route.params.subscribe(params => {
            this.id = params['id'];
        });
        
        this.getPlaza();
    }
    
    getPlaza() {
        this.plazaService.getPlaza(this.id).subscribe(plaza => this.plaza = plaza);
    }
        

    crearPropuesta() {
        this.loading = true;
        this.propuesta.destinatarioId = this.id;
        this.propuestaService.create(this.propuesta).subscribe(
            data => {
                
                this.router.navigate(['/propuestasEnviadas']);
            },
            error => {
                this.prohibido = true;
                this.loading = false;
                console.log(error);
            });
    }

}