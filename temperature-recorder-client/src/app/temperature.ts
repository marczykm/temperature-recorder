export class Temperature {
    id: number;
    temperature: number;
    date: Date;

    constructor(id: number, temperature: number, date: Date){
        this.id = id;
        this.temperature = temperature;
        this.date = date;
    }
}