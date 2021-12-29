// src/middleware/index.ts
// import { todolistActionTypes } from '../constants/todolist/actionTypes';
import { AppDispatch } from '../types';
// import { findBadItem, ToDoListAction } from '../actions/todolist';

const forbiddenWords = ['spam', 'money'];

type Props = {
  dispatch: AppDispatch;
};

// export function forbiddenWordsMiddleware({ dispatch }: Props) {
//   return function (next: AppDispatch) {
//     return function (action: ToDoListAction) {
//       if (action.type === todolistActionTypes.CREATE_TO_DO_ITEM) {
//         const detectedWordList = forbiddenWords.filter(word =>
//           action.payload.title.includes(word)
//         );
//         if (detectedWordList.length) {
//           return dispatch(findBadItem());
//         }
//       }
//       return next(action);
//     };
//   };
// }
