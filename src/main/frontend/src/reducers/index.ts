import storage from 'redux-persist/lib/storage';
import { combineReducers } from 'redux';
import { persistReducer } from 'redux-persist';

import { authReducer } from './auth/authReducer';
// import { notificationReducer } from './notification/notificationReducer';
// import { todolistReducer } from './todolist/todolistReducer';
import { AppState } from '../types';

const persistConfig = {
  key: 'root',
  storage,
  whitelist: [
    'auth',
    // 'posts',
    // 'todolist',
  ],
};

const rootReducer = combineReducers<AppState>({
  auth: authReducer,
});

export default persistReducer(persistConfig, rootReducer);
