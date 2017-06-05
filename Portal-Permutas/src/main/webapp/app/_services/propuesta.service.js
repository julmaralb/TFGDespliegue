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
require('rxjs/add/operator/toPromise');
var PropuestaService = (function () {
    function PropuestaService(http) {
        this.http = http;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
    }
    PropuestaService.prototype.getPropuestasEnviadas = function () {
        return this.http.get('/Portal-Permutas/api/propuesta/enviadas').map(this.extractData).publish().refCount();
    };
    PropuestaService.prototype.getPropuestasEnviadasN = function () {
        return this.http.get('/Portal-Permutas/api/propuesta/enviadasN').map(this.extractData).publish().refCount();
    };
    PropuestaService.prototype.getPropuestasRecibidas = function () {
        return this.http.get('/Portal-Permutas/api/propuesta/recibidas').map(function (res) { return res.json(); });
    };
    PropuestaService.prototype.getPropuestasRecibidasN = function () {
        return this.http.get('/Portal-Permutas/api/propuesta/recibidasN').map(function (res) { return res.json(); });
    };
    PropuestaService.prototype.create = function (propuesta) {
        return this.http.post('/Portal-Permutas/api/propuesta', propuesta).map(function (res) { return res.json(); });
    };
    PropuestaService.prototype.aceptarPropuesta = function (id) {
        return this.http.put('/Portal-Permutas/api/propuesta/aceptar/' + id).map(function (res) { return res.json(); });
    };
    PropuestaService.prototype.rechazarPropuesta = function (id) {
        return this.http.put('/Portal-Permutas/api/propuesta/rechazar/' + id).map(function (res) { return res.json(); });
    };
    PropuestaService.prototype.extractData = function (res) {
        var body = res.json();
        return body || {};
    };
    PropuestaService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], PropuestaService);
    return PropuestaService;
}());
exports.PropuestaService = PropuestaService;
//# sourceMappingURL=propuesta.service.js.map