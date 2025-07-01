
import {Controller, Delete, Get, Param, Post,Body,Put,}

from '@nestjs/common';
import { UserService } from './user.service';
import { CreateUserDto } from './dto/create.user.dto';
import { ResponseUserDto } from './dto/respose.user.dto';
import { UpdateUserDto } from './dto/update.user.dto';
import { LoginUserDto } from './dto/login.user.dto';
import { User } from './entities/user.entity';




@Controller('user')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Post('register')
  async register(@Body() user: CreateUserDto): Promise<ResponseUserDto> {
    return this.userService.createUser(user);
  }

  @Post('login')
  async login(@Body() loginDto: LoginUserDto): Promise<ResponseUserDto> {
    return this.userService.login(loginDto);
  }

  
  @Post()
  async pushNewUser(@Body() user: CreateUserDto): Promise<ResponseUserDto> {
    return this.userService.createUser(user);
  }

  @Get()
  async getUsers(): Promise<User[]> {
    return this.userService.userAll();
  }

  @Get(':id') 
  async getUserById(@Param('id') id: number): Promise<User> {
    return this.userService.userByID(id);
  }

  @Put(':id')
  async updateUserById(
    @Param('id') id: number,
    @Body() newData: UpdateUserDto,
  ): Promise<ResponseUserDto> {
    return this.userService.updateUserById(id, newData);
  }

  @Delete(':id')
  async deleteUserById(@Param('id') id: number): Promise<ResponseUserDto> {
    return this.userService.deleteUserByID(id);
  }
}


