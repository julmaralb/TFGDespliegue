import {Injectable} from '@angular/core';
import {webServiceEndpoint} from './commons';
import {Http, Response, URLSearchParams, RequestOptions} from '@angular/http';
import * as Rx from "rxjs/Rx";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/publish';

@Injectable()
export class PlazaService {

	constructor(private http: Http) {}
	
	getPlaza(usuarioId : string): Rx.Observable<PlazaPropia> {
        return this.http.get('/Portal-Permutas/api/plazaPropia/usuario/' +usuarioId).map(this.extractData).publish().refCount();
    }
    
    getPlazaById(plazaId : string): Rx.Observable<PlazaPropia> {
        return this.http.get('/Portal-Permutas/api/plazaPropia/' +plazaId).map(this.extractData).publish().refCount();
    }
	
	getPlazas(): Rx.Observable<PlazaPropia[]> {
        return this.http.get('/Portal-Permutas/api/plazaPropia/all').map(this.extractData).publish().refCount();
    }
    
    getPrincipal() {
        return this.http.get('/Portal-Permutas/api/plazaPropia').map(this.extractData).publish().refCount();
    }
    
    update(plaza: PlazaPropia) {
        return this.http.put('/Portal-Permutas/api/plazaPropia/modifica', plaza).map(this.extractData).publish().refCount();
    }
    
    private extractData(res: Response) {
        let body = res.json();
        return body || {};
    }
    
}