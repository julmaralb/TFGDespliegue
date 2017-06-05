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
var router_1 = require('@angular/router');
var index_1 = require('../_services/index');
var core_2 = require('angular2-google-maps/core');
var Observable_1 = require('rxjs/Observable');
var EditarPlazaComponent = (function () {
    function EditarPlazaComponent(router, plazaService, alertService, _wrapper, __loader) {
        this.router = router;
        this.plazaService = plazaService;
        this.alertService = alertService;
        this._wrapper = _wrapper;
        this.__loader = __loader;
        this.model = {};
        this.loading = false;
        this.color = 'primary';
        this.mode = 'indeterminate';
        this.value = 100;
        this.cambios = false;
        __loader.load().then(function () {
            console.log('google script loaded');
        });
        this.getPlaza();
    }
    EditarPlazaComponent.prototype.getPlaza = function () {
        var _this = this;
        this.plazaService.getPrincipal().subscribe(function (plaza) { return _this.plaza = plaza; });
    };
    EditarPlazaComponent.prototype.getLatLon = function (address) {
        console.log('Getting Address - ', address);
        var geocoder = new google.maps.Geocoder();
        return Observable_1.Observable.create(function (observer) {
            geocoder.geocode({ 'address': address }, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    observer.next(results[0].geometry.location);
                    observer.complete();
                }
                else {
                    console.log('Error - ', results, ' & Status - ', status);
                    observer.next({});
                    observer.complete();
                }
            });
        });
    };
    EditarPlazaComponent.prototype.editar = function () {
        this.setLatLon(this.plaza.direccion);
    };
    EditarPlazaComponent.prototype.setLatLon = function (address) {
        var _this = this;
        this.loading = true;
        this.getLatLon(address).subscribe(function (data) {
            _this.plaza.latitud = data.lat();
            _this.plaza.longitud = data.lng();
            _this.plazaService.update(_this.plaza)
                .subscribe(function (data) {
                _this.loading = false;
                _this.cambios = true;
            }, function (error) {
                _this.alertService.error(error);
                _this.loading = false;
            });
        }, function (error) {
            console.log(error);
        });
    };
    EditarPlazaComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: 'editar-plaza.component.html',
            styleUrls: ['../login/login.component.css']
        }), 
        __metadata('design:paramtypes', [router_1.Router, index_1.PlazaService, index_1.AlertService, core_2.GoogleMapsAPIWrapper, core_2.MapsAPILoader])
    ], EditarPlazaComponent);
    return EditarPlazaComponent;
}());
exports.EditarPlazaComponent = EditarPlazaComponent;
//# sourceMappingURL=editar-plaza.component.js.map