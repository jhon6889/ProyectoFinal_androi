// src/user/user.service.ts
import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { User } from './entities/user.entity';
import { CreateUserDto } from './dto/create.user.dto';
import { ResponseUserDto } from './dto/respose.user.dto';
import { UpdateUserDto } from './dto/update.user.dto';
import { LoginUserDto } from './dto/login.user.dto';

@Injectable()
export class UserService {
  constructor(
    @InjectRepository(User)
    private usersRepository: Repository<User>,
  ) {}

  async createUser(user: CreateUserDto): Promise<ResponseUserDto> {
    // Verificar si el email ya existe
    const existingUser = await this.usersRepository.findOne({ where: { email: user.email } });
    if (existingUser) {
      throw new HttpException(
        {
          status: HttpStatus.BAD_REQUEST,
          message: 'Email already in use',
        },
        HttpStatus.BAD_REQUEST,
      );
    }

    const newUser = this.usersRepository.create(user);
    await this.usersRepository.save(newUser);
    
    return {
      message: 'User created successfully',
      status: HttpStatus.CREATED,
      data: {
        id: newUser.id,
        name: newUser.name,
        email: newUser.email,
      },
    };
  }

  async login(loginDto: LoginUserDto): Promise<ResponseUserDto> {
    const user = await this.usersRepository.findOne({ 
      where: { 
        email: loginDto.email,
        password: loginDto.password // Comparación directa (solo para pruebas)
      } 
    });

    if (!user) {
      throw new HttpException(
        {
          status: HttpStatus.UNAUTHORIZED,
          message: 'Invalid credentials',
        },
        HttpStatus.UNAUTHORIZED,
      );
    }

    return {
      message: 'Login successful',
      status: HttpStatus.OK,
      data: {
        id: user.id,
        name: user.name,
        email: user.email,
      },
    };
  }


  async userAll(): Promise<User[]> {
    return await this.usersRepository.find();
  }

  async userByID(id: number): Promise<User> {
    const user = await this.usersRepository.findOne({ where: { id } });
    if (!user) {
      throw new HttpException(
        {
          status: HttpStatus.NOT_FOUND,
          message: 'User not found',
        },
        HttpStatus.NOT_FOUND,
      );
    }
    return user;
  }

  async updateUserById(id: number, userData: UpdateUserDto): Promise<ResponseUserDto> {
    await this.usersRepository.update(id, userData);
    const updatedUser = await this.usersRepository.findOne({ where: { id } });
    
    return {
      message: 'User updated successfully',
      status: HttpStatus.OK,
      data: updatedUser,
    };
  }

  async deleteUserByID(id: number): Promise<ResponseUserDto> {
    const result = await this.usersRepository.delete(id);
    if (result.affected === 0) {
      throw new HttpException(
        {
          status: HttpStatus.NOT_FOUND,
          message: 'User not found',
        },
        HttpStatus.NOT_FOUND,
      );
    }
    
    return {
      message: 'User deleted successfully',
      status: HttpStatus.OK,
    };
  }
}











