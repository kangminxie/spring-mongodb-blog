// src/apis/AuthApi.ts
import axios from 'axios';

export default axios.create({
  baseURL: 'http://localhost:8080/api/auth',
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json',
  },
});
