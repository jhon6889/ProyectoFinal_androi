import { Controller, Get } from '@nestjs/common';

@Controller()
export class AppController {
  @Get()
  getRoot(): string {
    return '✅ Bienvenido a la API de NestJS';
  }
}
