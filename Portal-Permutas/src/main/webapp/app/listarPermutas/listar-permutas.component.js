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
require('rxjs/add/operator/switchMap');
var index_1 = require('../_services/index');
var ListarPermutasComponent = (function () {
    function ListarPermutasComponent(permutaService, plazaService, router) {
        this.permutaService = permutaService;
        this.plazaService = plazaService;
        this.router = router;
    }
    ListarPermutasComponent.prototype.ngOnInit = function () {
        this.getPermutas();
    };
    ListarPermutasComponent.prototype.getPermutas = function () {
        var _this = this;
        this.permutaService.getPermutas().subscribe(function (permutas) { return _this.permutas = permutas; });
    };
    ListarPermutasComponent.prototype.getPlazaById = function (id) {
        var _this = this;
        this.plazaService.getPlazaById(id).subscribe(function (plaza) { return _this.plaza = plaza; });
    };
    ListarPermutasComponent.prototype.cerrar = function () {
        this.plaza = null;
    };
    ListarPermutasComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: 'listar-permutas.component.html'
        }), 
        __metadata('design:paramtypes', [index_1.PermutaService, index_1.PlazaService, router_1.Router])
    ], ListarPermutasComponent);
    return ListarPermutasComponent;
}());
exports.ListarPermutasComponent = ListarPermutasComponent;
//# sourceMappingURL=listar-permutas.component.js.map