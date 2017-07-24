import { Component, OnInit } from '@angular/core';

import { Temperature } from './temperature';
import { TemperatureService } from './temperature.service';

@Component({
  selector: 'current-temperature',
  templateUrl: './current-temperature.component.html',
  styleUrls: ['./current-temperature.component.css']
})
export class CurrentTemperatureComponent implements OnInit {

  currentTemperature: Temperature;

  constructor(private temperatureService: TemperatureService){}

  ngOnInit(): void {
    this.temperatureService.getCurrent()
      .then(temperature => this.currentTemperature = temperature);
  }
}
