export interface StudentData {
    id?: number;
    name: string;
    address: string;
}

export interface addStudentProp {
  addStudent: (e: any) => Promise<void>;
}