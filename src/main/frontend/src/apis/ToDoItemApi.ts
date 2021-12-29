// src/apis/ToDoItemApi.ts
import axios from 'axios';
import JwtUtil from './JwtUtil';

export default axios.create({
  baseURL: 'http://localhost:8080/api/todolist/',
  headers: {
    Authorization: 'Bearer ' + JwtUtil.getCurrentJwtToken(),
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json',
  },
});
