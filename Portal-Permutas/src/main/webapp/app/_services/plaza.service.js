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
require('rxjs/add/operator/publish');
var PlazaService = (function () {
    function PlazaService(http) {
        this.http = http;
    }
    PlazaService.prototype.getPlaza = function (usuarioId) {
        return this.http.get('/Portal-Permutas/api/plazaPropia/usuario/' + usuarioId).map(this.extractData).publish().refCount();
    };
    PlazaService.prototype.getPlazaById = function (plazaId) {
        return this.http.get('/Portal-Permutas/api/plazaPropia/' + plazaId).map(this.extractData).publish().refCount();
    };
    PlazaService.prototype.getPlazas = function () {
        return this.http.get('/Portal-Permutas/api/plazaPropia/all').map(this.extractData).publish().refCount();
    };
    PlazaService.prototype.getPrincipal = function () {
        return this.http.get('/Portal-Permutas/api/plazaPropia').map(this.extractData).publish().refCount();
    };
    PlazaService.prototype.update = function (plaza) {
        return this.http.put('/Portal-Permutas/api/plazaPropia/modifica', plaza).map(this.extractData).publish().refCount();
    };
    PlazaService.prototype.extractData = function (res) {
        var body = res.json();
        return body || {};
    };
    PlazaService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], PlazaService);
    return PlazaService;
}());
exports.PlazaService = PlazaService;
//# sourceMappingURL=plaza.service.js.map