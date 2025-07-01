import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  await app.listen(3000, '0.0.0.0');
  console.log(`✅ API disponible en: http://localhost:3000 y http://192.168.1.1:3000
 `);
}
bootstrap();

