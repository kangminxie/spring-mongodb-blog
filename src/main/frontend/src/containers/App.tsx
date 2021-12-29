import * as React from 'react';
import { connect } from 'react-redux';
import { BrowserRouter, Route, Redirect, Switch } from 'react-router-dom';
import TopNavigation from '../components/navigation/TopNavigation';
import LandingPage from './landing/LandingPage';
import HomePage from './home/HomePage';
import AdminPage from './admin/AdminPage';
import Footer from '../components/footer/Footer';
import { AppState } from '../types';

import './app.styles.scss';

type AppProps = {
  isLoggedIn: boolean;
};

const App: React.FC<AppProps> = (props) => {
  const { isLoggedIn } = props;
  console.log('###[debug] isLoggedIn:' + isLoggedIn);

  return (
    <BrowserRouter>
      <div className='app'>
        <TopNavigation />
        <Switch>
          <Route path='/' exact component={LandingPage} />
          <Route path='/home' exact component={HomePage} />
          <Route path='/admin' component={AdminPage} />
          <Redirect to='/404' />
        </Switch>
        <Footer />
      </div>
    </BrowserRouter>
  );
};

const mapStateToProps = (state: AppState) => {
  const { auth } = state;
  return {
    isLoggedIn: auth.isLoggedIn,
  };
};

export default connect(mapStateToProps)(App);
