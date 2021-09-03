export interface AuthToken {
  sub: string;
  exp: Date;
  permissions: string[];
}