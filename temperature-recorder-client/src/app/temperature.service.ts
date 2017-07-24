import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs';

import { Temperature } from './temperature';

@Injectable()
export class TemperatureService {
    private temperatureUrl = 'http://localhost:5000/api/v1/temperature';

    constructor(private http: Http) { }

    // getCurrent(): Promise<Temperature> {
        // return this.http.get(this.temperatureUrl + '/current')
        //     .toPromise()
        //     .then(response => response.json() as Temperature)
        //     .catch(this.handleError);
    // }

    getCurrent = () => {
        return Observable
            .interval(2000)
            .flatMap((i) => this.http.get(this.temperatureUrl + '/current')
            .toPromise()
            .then(response => response.json() as Temperature)
            .catch(this.handleError)
        );
    }

    private handleError(error: any){
        console.error('An error occured', error);
        return Promise.reject(error.message || error);
    }
}
