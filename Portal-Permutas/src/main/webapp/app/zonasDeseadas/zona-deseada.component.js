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
var ZonaDeseadaComponent = (function () {
    function ZonaDeseadaComponent(zonaDeseadaService, plazaService, router, geocodingService, zone) {
        this.zonaDeseadaService = zonaDeseadaService;
        this.plazaService = plazaService;
        this.router = router;
        this.geocodingService = geocodingService;
        this.zone = zone;
        // google maps zoom level
        this.zoom = 13;
        // initial center position for the map
        this.lat = 37.362444;
        this.lng = -5.9965;
        this.circles = [];
        this.getCoincidencias();
        this.getPlazas();
        this.getZonas();
    }
    ZonaDeseadaComponent.prototype.getPlazas = function () {
        var _this = this;
        this.plazaService.getPlazas().subscribe(function (plazas) { return _this.plazas = plazas; });
    };
    ZonaDeseadaComponent.prototype.getZonas = function () {
        var _this = this;
        this.zonaDeseadaService.getZonas().subscribe(function (zonas) { return _this.zonas = zonas; });
    };
    ZonaDeseadaComponent.prototype.getCoincidencias = function () {
        var _this = this;
        this.zonaDeseadaService.checkCoincidencias().subscribe(function (coincidencias) { return _this.coincidencias = coincidencias; });
    };
    ZonaDeseadaComponent.prototype.proponer = function (id) {
        this.router.navigate(['/crearPropuesta', id]);
    };
    ZonaDeseadaComponent.prototype.clickedMarker = function (label, index) {
        console.log("clicked the marker: " + (label || index));
    };
    ZonaDeseadaComponent.prototype.deleteZone = function (id) {
        var _this = this;
        var r = confirm('¿Borrar esta zona?');
        if (r == true) {
            this.zonaDeseadaService.deleteZone(id).then(function () {
                _this.getZonas();
                _this.getCoincidencias();
            });
        }
    };
    ZonaDeseadaComponent.prototype.goToZone = function (lat, lng) {
        this.lat = lat;
        this.lng = lng;
    };
    ZonaDeseadaComponent.prototype.search = function () {
        var _this = this;
        this.geocodingService.getLatLon(this.address).subscribe(function (data) {
            _this.zone.run(function () {
                _this.lat = data.lat();
                _this.lng = data.lng();
                _this.zoom = 18;
            });
        }, function (error) {
            console.log(error);
        });
    };
    ZonaDeseadaComponent.prototype.keyDownFunction = function ($event) {
        var _this = this;
        if (event.keyCode == 13) {
            this.geocodingService.getLatLon(this.address).subscribe(function (data) {
                _this.zone.run(function () {
                    _this.lat = data.lat();
                    _this.lng = data.lng();
                    _this.zoom = 18;
                });
            }, function (error) {
                console.log(error);
            });
        }
    };
    ZonaDeseadaComponent.prototype.addCircle = function () {
        var circle = {};
        circle.latitude = this.lat;
        circle.longitude = this.lng;
        circle.radius = 300;
        circle.circleDraggable = true;
        circle.editable = true;
        this.circles.push(circle);
    };
    ZonaDeseadaComponent.prototype.changedRadius = function ($event, circle) {
        console.log($event);
        circle.radius = $event;
    };
    ZonaDeseadaComponent.prototype.changedPosition = function ($event, circle) {
        console.log($event.coords.lat);
        console.log($event.coords.lng);
        circle.latitude = $event.coords.lat;
        circle.longitude = $event.coords.lng;
    };
    ZonaDeseadaComponent.prototype.save = function () {
        var _this = this;
        this.zonaDeseadaService.createZone(this.circles).subscribe(function (data) {
            _this.getZonas();
            _this.getCoincidencias();
            _this.circles = [];
        }, function (error) {
            console.log(error);
        });
    };
    ZonaDeseadaComponent.prototype.changedCircleCenter = function ($event, circle) {
        circle.latitude = $event.lat;
        circle.longitude = $event.lng;
    };
    ZonaDeseadaComponent.prototype.changedCenter = function ($event) {
        this.lat = $event.lat;
        this.lng = $event.lng;
    };
    ZonaDeseadaComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            styleUrls: ['zona-deseada.component.css'],
            templateUrl: 'zona-deseada.component.html'
        }), 
        __metadata('design:paramtypes', [index_1.ZonaDeseadaService, index_1.PlazaService, router_1.Router, index_1.GeocodingService, core_1.NgZone])
    ], ZonaDeseadaComponent);
    return ZonaDeseadaComponent;
}());
exports.ZonaDeseadaComponent = ZonaDeseadaComponent;
//# sourceMappingURL=zona-deseada.component.js.map