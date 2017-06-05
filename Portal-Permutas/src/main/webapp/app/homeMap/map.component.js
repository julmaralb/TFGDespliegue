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
var MapComponent = (function () {
    function MapComponent(plazaService, router, authenticationService, geocodingService, zone) {
        this.plazaService = plazaService;
        this.router = router;
        this.authenticationService = authenticationService;
        this.geocodingService = geocodingService;
        this.zone = zone;
        // google maps zoom level  
        this.zoom = 13;
        // initial center position for the map
        this.lat = 37.362444;
        this.lng = -5.9965;
        this.markers = [
            {
                lat: 37.382444,
                lng: -5.99625,
                label: 'A',
                draggable: true
            },
            {
                lat: 37.3541545,
                lng: -5.9885772,
                label: 'A',
                draggable: true
            }
        ];
    }
    MapComponent.prototype.ngOnInit = function () {
        this.getPlazas();
    };
    MapComponent.prototype.getPlazas = function () {
        var _this = this;
        this.plazaService.getPlazas().subscribe(function (plazas) { return _this.plazas = plazas; });
    };
    MapComponent.prototype.proponer = function (id) {
        this.router.navigate(['/crearPropuesta', id]);
    };
    MapComponent.prototype.search = function () {
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
    MapComponent.prototype.keyDownFunction = function ($event) {
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
    MapComponent.prototype.clickedMarker = function (label, index) {
        console.log("clicked the marker: " + (label || index));
    };
    MapComponent.prototype.changedCenter = function ($event) {
        this.lat = $event.lat;
        this.lng = $event.lng;
    };
    MapComponent.prototype.mapClicked = function ($event) {
        this.markers.push({
            lat: $event.coords.lat,
            lng: $event.coords.lng
        });
    };
    MapComponent.prototype.markerDragEnd = function (m, $event) {
        console.log('dragEnd', m, $event);
    };
    MapComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'g-map',
            styleUrls: ['map.component.css'],
            templateUrl: 'map.component.html'
        }), 
        __metadata('design:paramtypes', [index_1.PlazaService, router_1.Router, index_1.AuthenticationService, index_1.GeocodingService, core_1.NgZone])
    ], MapComponent);
    return MapComponent;
}());
exports.MapComponent = MapComponent;
//# sourceMappingURL=map.component.js.map