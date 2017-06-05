import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from '../domain';
import 'rxjs/add/operator/map'

import { PropuestaService } from './propuesta.service';

@Injectable()
export class AuthenticationService {

    loggedIn: boolean = false;
    currentUser : User;
    numPR : number = 0;

    constructor(private http: Http, private propuestaService: PropuestaService) {
    }

    login(username: string, password: string) {
        return this.http.post('/Portal-Permutas/j_spring_security_check?username=' + username + '&password=' + password)
            .map((response: Response) => {
                let user = response.json();
                if (user.nombre) {
                    this.changeLoginStatus(true);
                    this.setCurrentUser(user);
                    this.propuestaService.getPropuestasRecibidasN().subscribe(numPR => this.numPR = numPR);
                }
            });

    }
    changeLoginStatus(status: boolean) {
        this.loggedIn = status;
    }
    
    setCurrentUser(user : User){
    	this.currentUser= user;
    }
    
    getCurrentUser(){
    	return this.currentUser;
    }
    
    getNumPR(){
        return this.numPR;
    }
    
    reseteaContador(){
        this.numPR= 0;
    }

    notificacionVista(){
        this.numPR--;
    }

    isLoggedIn() {
        return this.loggedIn;
    }
    
    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }
}