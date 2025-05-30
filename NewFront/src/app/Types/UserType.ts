export type Role  ="Choose"| "Admin" | "visitor"


export type User= {
   id : number |undefined,
   firstName : string,
   lastName :string,
   phoneNumber :string ,
   emailAddress:string,
   occupation :string,
   role :Role
   password : string
}