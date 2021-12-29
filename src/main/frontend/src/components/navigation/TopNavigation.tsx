import React from 'react';
import { connect } from 'react-redux';
import { Link, withRouter } from 'react-router-dom';
import * as H from 'history';
import {
  AdminIcon,
  BookIcon,
  CalendarIcon,
  CloudIcon,
  CoffeeIcon,
  HomeIcon,
  SignInIcon,
  SignOutIcon,
} from '../common/icon';
import { AppState } from '../../types';
import { logoutUser } from '../../actions/auth';

import './topNavigation.styles.scss';

type Props = {
  isLoggedIn: boolean;
  history: H.History;
  location: {
    pathname: string;
  };
  logoutUser: (history: H.History) => any;
};

const TopNavigation = (props: Props) => {
  const {
    isLoggedIn,
    location: { pathname },
    history,
    logoutUser,
  } = props;

  const getClass = (curr: string) => {
    const isActive = pathname.startsWith('/' + curr);
    return 'my-nav-item ' + (isActive ? 'is-active' : '');
  };

  const _tryLogout = (event: React.MouseEvent<HTMLElement>) => {
    event.preventDefault();
    logoutUser(history);
  };

  return (
    <div className='top-navigation'>
      <section className='left-section'>
        <div className='my-brand'>
          <Link to='/'>MongoBlog App</Link>
        </div>
        <div className={getClass('home')}>
          <Link to='/home'>
            <HomeIcon /> Home
          </Link>
        </div>
        <div className={getClass('course')}>
          <Link to='/course'>
            <BookIcon /> Course
          </Link>
        </div>
        <div className={getClass('todo')}>
          <Link to='/todo'>
            <CalendarIcon /> ToDoList
          </Link>
        </div>
        <div className={getClass('about')}>
          <Link to='/about'>
            <CoffeeIcon /> About
          </Link>
        </div>
      </section>

      <section className='middle-section'>
        <div className={getClass('admin')}>
          <Link to='/admin'>
            <AdminIcon /> Admin
          </Link>
        </div>
        <div className={getClass('dev')}>
          <Link to='/dev'>
            <CloudIcon /> Dev-UI
          </Link>
        </div>
      </section>

      <section className='right-section'>
        {isLoggedIn ? (
          <div className={getClass('logout')}>
            <Link to='/login' onClick={_tryLogout}>
              <SignOutIcon /> Logout
            </Link>
          </div>
        ) : (
          <div className={getClass('login')}>
            <Link to='/login'>
              <SignInIcon /> Login
            </Link>
          </div>
        )}
      </section>
    </div>
  );
};

const mapStateToProps = (state: AppState) => {
  const { auth } = state;
  return {
    isLoggedIn: auth.isLoggedIn,
  };
};

const mapDispatchToProps = {
  logoutUser: logoutUser,
};

const ConnectedTopNavigation = connect(
  mapStateToProps,
  mapDispatchToProps
)(TopNavigation);

export default withRouter(ConnectedTopNavigation);
