import { Component, OnInit } from '@angular/core';
import { Response } from '@angular/http';
import { Router } from '@angular/router';
import * as Rx from 'rxjs/Rx';

import { PlazaService } from '../_services/index';
import { PlazaPropia } from '../domain';


@Component({
	moduleId: module.id,
    templateUrl: 'plazas-list.component.html'
})
export class PlazasListComponent implements OnInit {

    plazas: PlazaPropia[];   

    constructor(private plazaService: PlazaService, private router: Router) {

    }

    ngOnInit() {
        this.getPlazas();
    }
    
    getPlazas(): void {
    this.plazaService.getPlazas().subscribe(plazas => this.plazas = plazas);
  }
}