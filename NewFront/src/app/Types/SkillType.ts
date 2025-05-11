
export type TechNature = "Choose"|"FrontEnd"| "BackEnd" |"Infrastructure"|"Database"
export type Level ="Choose"|"Beginner" | "Intermediate" | "Advanced"

export type Skill =  {
   id : undefined | number,
   name : string,
   nature : TechNature,
   level : Level
   grade :number
}