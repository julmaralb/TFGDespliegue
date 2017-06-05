import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { User } from '../_models/index';

@Injectable()
export class UserService {
    constructor(private http: Http) { }

    getAll() {
        return this.http.get('/api/users', this.jwt()).map((response: Response) => response.json());
    }

    getPrincipal() {
        return this.http.get('/Portal-Permutas/api/usuario', this.jwt()).map((response: Response) => response.json());
    }

    create(user: User) {
        return this.http.post('/Portal-Permutas/api/usuario', user).map(res => res.json());
    }

    update(user: User) {
        return this.http.put('/Portal-Permutas/api/usuario/modifica', user, this.jwt()).map((response: Response) => response.json());
    }

    delete(id: number) {
        return this.http.delete('/api/users/' + id, this.jwt()).map((response: Response) => response.json());
    }

    // private helper methods

    private jwt() {
        // create authorization header with jwt token
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.token) {
            let headers = new Headers({ 'Authorization': 'Bearer ' + currentUser.token });
            return new RequestOptions({ headers: headers });
        }
    }
     private extractData(res: Response) {
        let body = res.json();
        return body || {};
    }
}