import React from 'react';
import { withRouter } from 'react-router-dom';
import { Col, Container, Row } from 'react-bootstrap';
import Clock from '../widget/Clock';

import './footer.styles.scss';

type Props = {
  location: {
    pathname: string;
  };
};

const LinkContent = () => {
  return (
    <div className='py-4 bg-light'>
      <Container>
        <Row className='text-center'>
          <Col sm={10} md={6} lg={4}>
            <h5>Tech Stacks</h5>
            <ul className='links list-unstyled'>
              <li>
                <a
                  href='https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html'
                  className='text-muted'
                >
                  Java
                </a>
              </li>
              <li>
                <a
                  href='https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html'
                  className='text-muted'
                >
                  SpringBoot
                </a>
              </li>
              <li>
                <a
                  href='https://dev.mysql.com/doc/refman/5.7/en/'
                  className='text-muted'
                >
                  MySQL
                </a>
              </li>
              <li>
                <a
                  href='https://react-bootstrap.github.io/'
                  className='text-muted'
                >
                  React-Bootstrap v2.0.0
                </a>
              </li>
            </ul>
          </Col>
          <Col sm={10} md={6} lg={4}>
            <h5>Web Development</h5>
            <ul className='contact-info list-unstyled'>
              <li>Spring-React-Redux</li>
              <li>
                <a href='mailto:mountkingx@gmail.com' className='text-dark'>
                  mountkingx@gmail.com
                </a>
              </li>
              <li>https://github.com/MountKingX</li>
            </ul>
            <p className='text-muted'>Good luck with everything</p>
          </Col>
          <Col sm={10} md={6} lg={4}>
            <h5>Favorites</h5>
            <ul className='links list-unstyled'>
              <li>
                <a href='https://www.premierleague.com/' className='text-muted'>
                  Premier League
                </a>
              </li>
              <li>
                <a
                  href='https://www.premierleague.com/players/1208/Michael-Owen/overview'
                  className='text-muted'
                >
                  Michael Owen
                </a>
              </li>
              <li>
                <a
                  href='https://en.wikipedia.org/wiki/The_Three-Body_Problem_(novel)'
                  className='text-muted'
                >
                  Three Body Problems
                </a>
              </li>
              <li>
                <a
                  href='https://www.youtube.com/watch?v=gY2ekm_krNU'
                  className='text-muted'
                >
                  To Be a Better Man
                </a>
              </li>
            </ul>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

const Footer = (props: Props) => {
  const {
    location: { pathname },
  } = props;
  const showLinkContent = !pathname.startsWith('/admin');
  return (
    <div className='my-footer'>
      {showLinkContent && <LinkContent />}
      <div className='bg-dark text-center text-white py-3'>
        &copy; 2016-2021 by Kangmin Xie, kangmin.xie@gmail.com. All rights
        reserved. <Clock />
      </div>
    </div>
  );
};

export default withRouter(Footer);
