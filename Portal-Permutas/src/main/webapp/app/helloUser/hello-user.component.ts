import { Component, OnInit, Input  } from '@angular/core';

@Component({
  moduleId:	module.id,
  selector: 'hello-user',
  template: ` 
      <h2> Hello {{name}} </h2>
      <div>
        <label>Username: </label>
        <input [value]="name" (input)="name = $event.target.value"/>
      </div>
  `
})

export class HelloUserComponent implements OnInit{ 
	  ngOnInit(): void {}
	  @Input()
	  name = "";
	 
	  	
}