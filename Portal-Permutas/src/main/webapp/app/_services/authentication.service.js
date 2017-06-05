"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var http_1 = require('@angular/http');
require('rxjs/add/operator/map');
var propuesta_service_1 = require('./propuesta.service');
var AuthenticationService = (function () {
    function AuthenticationService(http, propuestaService) {
        this.http = http;
        this.propuestaService = propuestaService;
        this.loggedIn = false;
        this.numPR = 0;
    }
    AuthenticationService.prototype.login = function (username, password) {
        var _this = this;
        return this.http.post('/Portal-Permutas/j_spring_security_check?username=' + username + '&password=' + password)
            .map(function (response) {
            var user = response.json();
            if (user.nombre) {
                _this.changeLoginStatus(true);
                _this.setCurrentUser(user);
                _this.propuestaService.getPropuestasRecibidasN().subscribe(function (numPR) { return _this.numPR = numPR; });
            }
        });
    };
    AuthenticationService.prototype.changeLoginStatus = function (status) {
        this.loggedIn = status;
    };
    AuthenticationService.prototype.setCurrentUser = function (user) {
        this.currentUser = user;
    };
    AuthenticationService.prototype.getCurrentUser = function () {
        return this.currentUser;
    };
    AuthenticationService.prototype.getNumPR = function () {
        return this.numPR;
    };
    AuthenticationService.prototype.reseteaContador = function () {
        this.numPR = 0;
    };
    AuthenticationService.prototype.notificacionVista = function () {
        this.numPR--;
    };
    AuthenticationService.prototype.isLoggedIn = function () {
        return this.loggedIn;
    };
    AuthenticationService.prototype.logout = function () {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    };
    AuthenticationService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http, propuesta_service_1.PropuestaService])
    ], AuthenticationService);
    return AuthenticationService;
}());
exports.AuthenticationService = AuthenticationService;
//# sourceMappingURL=authentication.service.js.map