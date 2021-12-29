import { AuthState } from '../../types';
import { AuthAction, authActionTypes } from '../../actions/auth';

const INITIAL_STATE: AuthState = {
  isLoggedIn: false,
  username: '',
  authorities: ['ROLE_ANONYMOUS'],
  isAuthFailed: null,
};

export const authReducer = (
  state = INITIAL_STATE,
  action: AuthAction
): AuthState => {
  switch (action.type) {
    case authActionTypes.USER_AUTHENTICATED:
      return {
        ...state,
        isLoggedIn: action.payload.isLoggedIn,
        username: action.payload.username,
        authorities: action.payload.authorities,
        isAuthFailed: action.payload.isAuthFailed,
      };
    case authActionTypes.USER_LOGOUT:
      return {
        ...INITIAL_STATE,
      };
    default:
      return state;
  }
};
