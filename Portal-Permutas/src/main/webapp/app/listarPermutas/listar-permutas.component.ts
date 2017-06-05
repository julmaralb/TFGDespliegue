import { Component, OnInit } from '@angular/core';
import { Response } from '@angular/http';
import { Router } from '@angular/router';
import * as Rx from 'rxjs/Rx';

import 'rxjs/add/operator/switchMap';

import { PermutaService, PlazaService } from '../_services/index';
import { Permuta } from '../domain';


@Component({
    moduleId: module.id,
    templateUrl: 'listar-permutas.component.html'
})
export class ListarPermutasComponent implements OnInit {

    permutas: Permuta[];
    plaza: PlazaPropia;
    titulo: string;

    constructor(private permutaService: PermutaService, private plazaService: PlazaService, private router: Router) {

    }

    ngOnInit() {
        this.getPermutas();
    }

    getPermutas(): void {
        this.permutaService.getPermutas().subscribe(permutas => this.permutas = permutas);
    }
    
    getPlazaById(id : string){
        this.plazaService.getPlazaById(id).subscribe(plaza => this.plaza = plaza);
    }
    
    cerrar(){
        this.plaza = null;
    }
}