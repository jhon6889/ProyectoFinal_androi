export class ResponseUserDto {
  message: string;
  status: number;
  data?: {
    id?: number;
    name?: string;
    email?: string;
  };
}