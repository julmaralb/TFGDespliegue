import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { GoogleMapCircle } from '../domain';

@Injectable()
export class ZonaDeseadaService { 

	private headers = new Headers({'Content-Type': 'application/json'});
	
	constructor(private http: Http) {
	}
		
	getZonas(): Rx.Observable<ZonaDeseada[]> {
        return this.http.get('/Portal-Permutas/api/zonaDeseada/all').map(res => res.json());
    }
    
    createZone(circles : GoogleMapCircle[]) {
    	console.log(circles);
    	return this.http.post('/Portal-Permutas/api/zonaDeseada', circles).map(res => res.json());
    }
    
    deleteZone(id: string): Promise<void> {
    	return this.http.delete('/Portal-Permutas/api/zonaDeseada/' +id)
    	.toPromise()
    	.then(() => null).catch(this.handleError);  	
    }
    
    checkCoincidencias(): Rx.Observable<Coincidencia[]> {
    	return this.http.get('/Portal-Permutas/api/zonaDeseada/matchings').map(this.extractData).publish().refCount();
    }
    
    private extractData(res: Response) {
        let body = res.json();
        return body || {};
    }   
}