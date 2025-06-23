export interface Payement {
    id:number;
    amount:number;
    date: string;
    type:string;
    statut:string;
    student: {
        id:number;
        firstname: string;
        lastname: string;
    }
}