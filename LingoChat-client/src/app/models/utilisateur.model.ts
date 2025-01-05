import { Langue } from "./langue.model";

export class Utilisateur {
    constructor(
        public id?:number,
        public userName?:string,
        public motDePasse?:string,
        public courriel?:string, 
        public photo?:string, 
        public status?:string, 
        public active:boolean=false, 
        public languesParle: Langue[]=[],
        public languesApprise: Langue[]=[],
    ){}
        
}