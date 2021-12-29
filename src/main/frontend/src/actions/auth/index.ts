import jwt_decode from 'jwt-decode';
import { AppDispatch } from '../../types';
import AuthApi from '../../apis/AuthApi';
import Names from '../../constants/names';
import * as H from 'history';

export enum authActionTypes {
  USER_AUTHENTICATED = 'auth/USER_AUTHENTICATED',
  USER_LOGOUT = 'auth/USER_LOGOUT',
  USER_REGISTER_SUCCESS = 'auth/USER_REGISTER_SUCCESS',
}

export type LoginRequest = {
  username: string;
  password: string;
};

export type RegisterRequest = {
  username: string;
  password: string;
  email: string;
  name: string;
};

export interface JwtDecodeResult {
  sub: string;
  authorities: [{ authority: AuthorityRole }];
}

export type AuthorityRole =
  | 'ROLE_ANONYMOUS'
  | 'ROLE_NORMAL'
  | 'ROLE_ADMIN'
  | 'ROLE_SUPER_ADMIN';

export type AuthenticatedPayLoad = {
  isLoggedIn: boolean;
  username: string;
  authorities: AuthorityRole[];
  isAuthFailed: boolean;
};

// Action type and payload declaration

type AuthenticatedAction = {
  type: authActionTypes.USER_AUTHENTICATED;
  payload: AuthenticatedPayLoad;
};

type LogoutAction = {
  type: authActionTypes.USER_LOGOUT;
};

export type AuthAction = AuthenticatedAction | LogoutAction;

// Action Creators - complicated
export const handleLoginRequest = (request: LoginRequest) => {
  return async (dispatch: AppDispatch) => {
    const { username, password } = request;
    try {
      const response = await AuthApi.post('/login', {
        username: username,
        password: password,
      });
      const { status } = response;
      if (status === 200) {
        const jwtToken = response.data['jwtToken'];
        localStorage.removeItem(Names.JWT_TOKEN);
        localStorage.setItem(Names.JWT_TOKEN, jwtToken);
        const decodeResult: JwtDecodeResult = jwt_decode(jwtToken);

        dispatch(
          authenticated({
            isLoggedIn: true,
            username: decodeResult.sub,
            authorities: decodeResult.authorities.map((each) => each.authority),
            isAuthFailed: false,
          })
        );
      } else {
        const { message } = response.data;
        // dispatch(notifyUser(NotifyLevel.ERROR, message));
      }
    } catch (error) {
      console.log('error:' + error);
      // dispatch(showLoginError("No response from auth server!"));
      // dispatch(notifyUser(NotifyLevel.ERROR, "Sorry, please retry or check network connection"));
    }
  };
};

export const authenticated = (
  authData: AuthenticatedPayLoad
): AuthenticatedAction => {
  return {
    type: authActionTypes.USER_AUTHENTICATED,
    payload: authData,
  };
};

export const logoutRequested = (): LogoutAction => {
  return {
    type: authActionTypes.USER_LOGOUT,
  };
};

export const logoutUser = (history: H.History) => {
  return async (dispatch: AppDispatch) => {
    localStorage.removeItem(Names.JWT_TOKEN);
    await dispatch(logoutRequested());
    history.push("/");
  };
};
