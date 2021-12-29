import { createStore, applyMiddleware } from 'redux';
import reduxThunk, { ThunkMiddleware } from 'redux-thunk';
import { persistStore } from 'redux-persist';
import logger from 'redux-logger';

import rootReducer from '../reducers';
// import { forbiddenWordsMiddleware } from '../middlewares';

type ForbiddenWordsMiddleware = any;
type AppMiddleware = ThunkMiddleware & ForbiddenWordsMiddleware;

const middlewareList: AppMiddleware[] = [reduxThunk];

if (process.env.NODE_ENV === 'development') {
  middlewareList.push(logger);
}

export const store = createStore(
  rootReducer,
  applyMiddleware(...middlewareList)
);

export const persistor = persistStore(store);

const assembled = {
  store,
  persistor,
};

export default assembled;
