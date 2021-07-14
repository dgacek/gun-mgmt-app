import { Role } from "./Role";

export interface User {
    id: number;
    role: Role;
    email: string;
    phone: number;
    username: string;
}