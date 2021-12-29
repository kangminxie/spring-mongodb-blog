import { Dispatch } from 'redux';
import { AuthAction, AuthorityRole } from '../actions/auth';

// common
export type Pageable = {
  offset: number;
  pageNumber: number;
  pageSize: number;
  paged: boolean;
  unpaged: boolean;
};

export type PageableWrapper = {
  pageable: Pageable;
  number: number;
  first: boolean;
  last: boolean;
  size: number;
  totalPages: number;
  totalElements: number;
  empty: boolean;
};

interface Loadable {
  loading: boolean;
}

// Each Individual State

export type AuthState = {
  isLoggedIn: boolean;
  username: string;
  authorities: AuthorityRole[];
  isAuthFailed: boolean | null;
};

export type ToDoItem = {
  id: string;
  title: string;
  detail: string;
  done: boolean;
  priority?: string;
  type?: string;
};

export type ToDoItemResponse = {
  content: ToDoItem[];
} & PageableWrapper;

export interface ToDoListState extends Loadable {
  items: ToDoItem[];
  pageSize: number;
  selected: ToDoItem;
  loading: boolean;
}

// Application State & Application GetState

export type AppState = {
  auth: AuthState;
  // todolist: ToDoListState;
};

export type AppGetState = () => AppState;

// Application PureAction and Action

type AppPureAction = AuthAction;

export type AppAction =
  | AppPureAction
  | ((dispatch: AppDispatch, getState: AppGetState) => AppPureAction);

export type AppDispatch = Dispatch<AppAction | any>;
