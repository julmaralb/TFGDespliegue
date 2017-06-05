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
var platform_browser_1 = require('@angular/platform-browser');
var forms_1 = require('@angular/forms');
var http_1 = require('@angular/http');
var material_1 = require('@angular/material');
var app_component_1 = require('./app.component');
var hello_user_component_1 = require('./helloUser/hello-user.component');
var map_component_1 = require('./homeMap/map.component');
var core_2 = require('angular2-google-maps/core');
var login_component_1 = require('./login/login.component');
var register_component_1 = require('./register/register.component');
var plazas_list_component_1 = require('./listadoPlazas/plazas-list.component');
var zona_deseada_component_1 = require('./zonasDeseadas/zona-deseada.component');
var editar_usuario_component_1 = require('./editarUsuario/editar-usuario.component');
var crear_propuesta_component_1 = require('./crearPropuesta/crear-propuesta.component');
var editar_plaza_component_1 = require('./editarPlaza/editar-plaza.component');
var listar_propuestas_enviadas_component_1 = require('./listarPropuestas/listar-propuestas-enviadas.component');
var listar_propuestas_recibidas_component_1 = require('./listarPropuestas/listar-propuestas-recibidas.component');
var listar_permutas_component_1 = require('./listarPermutas/listar-permutas.component');
var index_1 = require('./_directives/index');
var core_3 = require('angular2-google-maps/core');
var index_2 = require('./_services/index');
var app_routing_module_1 = require('./app-routing.module');
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            imports: [platform_browser_1.BrowserModule, forms_1.FormsModule, http_1.HttpModule, app_routing_module_1.AppRoutingModule, material_1.MaterialModule.forRoot(), core_2.AgmCoreModule.forRoot({
                    apiKey: 'AIzaSyA0Bx2IH546c7E3E5mqtSwQq8z-inqpWts'
                })],
            declarations: [
                app_component_1.AppComponent,
                index_1.AlertComponent,
                hello_user_component_1.HelloUserComponent,
                plazas_list_component_1.PlazasListComponent,
                map_component_1.MapComponent,
                login_component_1.LoginComponent,
                register_component_1.RegisterComponent,
                zona_deseada_component_1.ZonaDeseadaComponent,
                crear_propuesta_component_1.CrearPropuestaComponent,
                editar_usuario_component_1.EditarUsuarioComponent,
                editar_plaza_component_1.EditarPlazaComponent,
                listar_propuestas_enviadas_component_1.ListarPropuestasEnviadasComponent,
                listar_propuestas_recibidas_component_1.ListarPropuestasRecibidasComponent,
                listar_permutas_component_1.ListarPermutasComponent,
                index_1.EqualValidator
            ],
            providers: [
                index_2.AlertService,
                index_2.AuthenticationService,
                index_2.PlazaService,
                index_2.ZonaDeseadaService,
                index_2.GeocodingService,
                core_3.GoogleMapsAPIWrapper,
                index_2.PropuestaService,
                index_2.UserService,
                index_2.PermutaService
            ],
            bootstrap: [app_component_1.AppComponent]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map