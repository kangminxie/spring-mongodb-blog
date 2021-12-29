import React from 'react';
import { connect } from 'react-redux';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { AppState } from '../../types';

type Props = {
  isLoggedIn: boolean;
};

const RegisterAndLoginArea = (props: Props) => {
  if (props.isLoggedIn) {
    return null;
  }
  return (
    <React.Fragment>
      <div className='para-content'>
        New to this site?{' '}
        <Button variant='primary'>
          <Link to='/register'>Register a New Account</Link>
        </Button>
      </div>
      <div className='para-content'>
        Already a registered user?{' '}
        <Button variant='success'>
          <Link to='/login'>Login to Your Account</Link>
        </Button>
      </div>
    </React.Fragment>
  );
};

const mapStateToProps = (state: AppState) => {
  const { auth } = state;
  return {
    isLoggedIn: auth.isLoggedIn,
  };
};

export default connect(mapStateToProps)(RegisterAndLoginArea);
