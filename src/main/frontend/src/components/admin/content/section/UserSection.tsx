import React from 'react';
import { Table } from 'react-bootstrap';

import './sectionCommon.styles.scss';

const UserSection = () => {
  return (
    <section className='content-section'>
      <div className='content-section--header'>
        <h3>User Header</h3>
      </div>
      <div className='content-section--main'>
        I am User section
        <div className='users-table-wrapper'>
          <Table striped bordered hover size='sm'>
            <thead>
              <tr>
                <th>#</th>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Role</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>@mdo</td>
                <td>Mark</td>
                <td>Otto</td>
                <td>ADMIN</td>
              </tr>
              <tr>
                <td>2</td>
                <td>@mdo</td>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>NORMAL</td>
              </tr>
            </tbody>
          </Table>
        </div>
      </div>
    </section>
  );
};

export default UserSection;
