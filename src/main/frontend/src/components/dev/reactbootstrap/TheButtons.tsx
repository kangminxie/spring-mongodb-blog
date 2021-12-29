import React from 'react';
import { Button } from 'react-bootstrap';

export const TheButtons = () => {
  return (
    <div className='test-button my-3'>
      <h3>Bootstrap Buttons </h3>
      <div className='my-1'>
        <div className='my-1'>
          <Button variant='primary' type='button'>
            Primary Button
          </Button>{' '}
          <Button variant='secondary' type='button'>
            Secondary Button
          </Button>{' '}
          <Button variant='success' type='button'>
            Success Button
          </Button>{' '}
          <Button variant='warning' type='button'>
            Warning Button
          </Button>{' '}
        </div>
        <div className='my-1'>
          <Button variant='danger' type='button'>
            Danger Button
          </Button>{' '}
          <Button variant='info' type='button'>
            Info Button
          </Button>{' '}
          <Button variant='dark' type='button'>
            Dark Button
          </Button>{' '}
          <Button variant='light' type='button'>
            Light Button
          </Button>{' '}
        </div>
      </div>
      <div className='my-1'>
        <div className='my-1'>
          <Button variant='outline-primary' type='button'>
            Outline Primary
          </Button>{' '}
          <Button variant='outline-secondary' type='button'>
            Outline Secondary
          </Button>{' '}
          <Button variant='outline-success' type='button'>
            Outline Success
          </Button>{' '}
          <Button variant='outline-warning' type='button'>
            Outline Warning
          </Button>{' '}
        </div>
        <div className='my-1'>
          <Button variant='outline-danger' type='button'>
            Outline Danger
          </Button>{' '}
          <Button variant='outline-info' type='button'>
            Outline Info
          </Button>{' '}
          <Button variant='outline-dark' type='button'>
            Outline Dark
          </Button>{' '}
          <Button variant='outline-light' type='button'>
            Outline Light
          </Button>{' '}
        </div>
      </div>
      <br />
    </div>
  );
};
