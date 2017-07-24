import { Component } from '@angular/core';

import { Temperature } from './temperature';

@Component({
  selector: 'current-temperature',
  templateUrl: './current-temperature.component.html',
  styleUrls: ['./current-temperature.component.css']
})
export class CurrentTemperatureComponent {
  temperature: Temperature = new Temperature(123, 20.4, new Date());
}
