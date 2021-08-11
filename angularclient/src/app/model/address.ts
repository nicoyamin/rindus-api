import { Coordinates } from '../model/coordinates';

export class Address {
    street: string;
    suite: string;
    city: string;
    zipcode: string;
    geo: Coordinates;
}
