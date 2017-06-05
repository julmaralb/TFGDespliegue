import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Coincidencia, Propuesta, PropuestaDTO } from '../domain';

@Injectable()
export class PermutaService {

    private headers = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) {
    }

    getPermutas(): Rx.Observable<Permuta[]> {
        return this.http.get('/Portal-Permutas/api/permuta/principal').map(res => res.json());
    }