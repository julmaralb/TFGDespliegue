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
var CrearPropuestaComponent = (function () {
    function CrearPropuestaComponent(router, route, propuestaService, plazaService, alertService) {
        this.router = router;
        this.route = route;
        this.propuestaService = propuestaService;
        this.plazaService = plazaService;
        this.alertService = alertService;
        this.propuesta = {};
        this.loading = false;
        this.color = 'primary';
        this.mode = 'determinate';
        this.value = 50;
        this.plaza = {};
        this.prohibido = false;
    }
    CrearPropuestaComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params.subscribe(function (params) {
            _this.id = params['id'];
        });
        this.getPlaza();
    };
    CrearPropuestaComponent.prototype.getPlaza = function () {
        var _this = this;
        this.plazaService.getPlaza(this.id).subscribe(function (plaza) { return _this.plaza = plaza; });
    };
    CrearPropuestaComponent.prototype.crearPropuesta = function () {
        var _this = this;
        this.loading = true;
        this.propuesta.destinatarioId = this.id;
        this.propuestaService.create(this.propuesta).subscribe(function (data) {
            _this.router.navigate(['/propuestasEnviadas']);
        }, function (error) {
            _this.prohibido = true;
            _this.loading = false;
            console.log(error);
        });
    };
    CrearPropuestaComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: 'crear-propuesta.component.html',
            styleUrls: ['../login/login.component.css']
        }), 
        __metadata('design:paramtypes', [router_1.Router, router_1.ActivatedRoute, index_1.PropuestaService, index_1.PlazaService, index_1.AlertService])
    ], CrearPropuestaComponent);
    return CrearPropuestaComponent;
}());
exports.CrearPropuestaComponent = CrearPropuestaComponent;
//# sourceMappingURL=crear-propuesta.component.js.map