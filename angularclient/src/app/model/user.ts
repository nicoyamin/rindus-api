import { Address } from '../model/address';
import { Company } from '../model/company';

export class User {

    id: number;
    name: string;
    username: string;
    email: string;
    address: Address;
    phone: string;
    website: string;
    company: Company;

}
