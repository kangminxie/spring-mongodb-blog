import React from 'react';
import { Container, Jumbotron } from 'react-bootstrap';
import WelcomeArea from './WelcomeArea';
import RegisterAndLoginArea from './RegisterAndLoginArea';
import { ControlledCarousel } from '../dev/reactbootstrap/ControlledCarousel';

import './landingPage.styles.scss';

const LandingPage: React.FC = () => {
  return (
    <div id='landing-page'>
      <Jumbotron>
        <Container>
          <WelcomeArea />
          <RegisterAndLoginArea />
        </Container>
      </Jumbotron>
      <Container className='landing-carousel'>
        <ControlledCarousel />
      </Container>
    </div>
  );
};

export default LandingPage;
