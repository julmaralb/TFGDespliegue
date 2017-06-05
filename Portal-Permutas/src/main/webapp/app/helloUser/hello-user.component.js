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
var HelloUserComponent = (function () {
    function HelloUserComponent() {
        this.name = "";
    }
    HelloUserComponent.prototype.ngOnInit = function () { };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Object)
    ], HelloUserComponent.prototype, "name", void 0);
    HelloUserComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'hello-user',
            template: " \n      <h2> Hello {{name}} </h2>\n      <div>\n        <label>Username: </label>\n        <input [value]=\"name\" (input)=\"name = $event.target.value\"/>\n      </div>\n  "
        }), 
        __metadata('design:paramtypes', [])
    ], HelloUserComponent);
    return HelloUserComponent;
}());
exports.HelloUserComponent = HelloUserComponent;
//# sourceMappingURL=hello-user.component.js.map