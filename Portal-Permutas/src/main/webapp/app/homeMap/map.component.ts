import { Component, OnInit, NgZone } from '@angular/core';
import { Router} from '@angular/router';

import { PlazaService, AuthenticationService, GeocodingService } from '../_services/index';
import { PlazaPropia } from '../listadoPlazas/plazaPropia';


@Component({
    moduleId: module.id,
    selector: 'g-map',
    styleUrls: ['map.component.css'],
    templateUrl: 'map.component.html'
})

export class MapComponent implements OnInit {

    // google maps zoom level  
    zoom: number = 13;
    
    // initial center position for the map
    lat: number = 37.362444;
    lng: number = -5.9965;

    plazas: PlazaPropia[];
	address : string;

    constructor(
    private plazaService: PlazaService, 
    private router: Router, 
    private authenticationService: AuthenticationService,
    private geocodingService: GeocodingService,
    private zone: NgZone) { }
    
    ngOnInit() {
        this.getPlazas();
    }

    getPlazas(): void {
        this.plazaService.getPlazas().subscribe(plazas => this.plazas = plazas);
    }
    
    proponer(id : string) : void {
  		this.router.navigate(['/crearPropuesta', id]);
  	}
  	
  	search(){
  		this.geocodingService.getLatLon(this.address).subscribe(
    		data => {
    			this.zone.run(() => {
    				this.lat = data.lat();
                	this.lng = data.lng();
                	this.zoom = 18;
                }
            },
       		error => {
                console.log(error);
            });
  	}
  	
  	keyDownFunction($event) {
  		if(event.keyCode == 13) {
  		this.geocodingService.getLatLon(this.address).subscribe(
    		data => {
    			this.zone.run(() => {
    				this.lat = data.lat();
                	this.lng = data.lng();
                	this.zoom = 18;
                }
            },
       		error => {
                console.log(error);
            });
         }
  	}

    clickedMarker(label: string, index: number) {
        console.log(`clicked the marker: ${label || index}`)
    }
    
    changedCenter($event: MouseEvent){
        this.lat = $event.lat;
        this.lng = $event.lng;
    }

    mapClicked($event: MouseEvent) {
        this.markers.push({
            lat: $event.coords.lat,
            lng: $event.coords.lng
        });
    }

    markerDragEnd(m: marker, $event: MouseEvent) {
        console.log('dragEnd', m, $event);
    }

    markers: marker[] = [
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
    ]
}
// just an interface for type safety.
interface marker {
    lat: number;
    lng: number;
    label?: string;
    draggable: boolean;
}