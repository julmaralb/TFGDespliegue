import {Component, OnInit} from '@angular/core';
import {Response} from '@angular/http';
import {Router} from '@angular/router';
import * as Rx from 'rxjs/Rx';

import 'rxjs/add/operator/switchMap';

import { PropuestaService } from '../_services/index';
import { Propuesta } from '../domain';


@Component({
    moduleId: module.id,
    templateUrl: 'listar-propuestas-enviadas.component.html'
})
export class ListarPropuestasEnviadasComponent implements OnInit {

    propuestas: Propuesta[];   

    constructor(private propuestaService: PropuestaService, private router: Router) {

    }

    ngOnInit() {
        this.getPropuestasEnviadas();
    }
    
    getPropuestasEnviadas(): void {
    this.propuestaService.getPropuestasEnviadas().subscribe(propuestas => this.propuestas = propuestas);
  }
}