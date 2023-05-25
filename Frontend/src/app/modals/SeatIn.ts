
export class SeatIn{
    
    seatId:number;
    seatNumber:number;
    seatStatus:String;
    seatType:String;
    isSelected:boolean;
    constructor(seatId:number,seatNumber:number,seatStatus:String,
        seatType:String,
        isSelected:boolean){
        this.seatId=seatId;
        this.seatNumber=seatNumber;
        this.seatStatus=seatStatus;
        this.seatType=seatType;
        this.isSelected=isSelected;
    }
}
    