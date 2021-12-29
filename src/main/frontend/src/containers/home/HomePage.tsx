import * as React from 'react';
import { Table } from 'react-bootstrap';

import './homePage.styles.scss';

const dummyData = [
  { id: 1001, firstName: 'Mark', lastName: 'Otto', username: '@mdo' },
  { id: 1002, firstName: 'Jacob', lastName: 'Thornton', username: '@fat' },
  { id: 1003, firstName: 'Michael', lastName: 'Owen', username: '@miow' },
  { id: 1004, firstName: 'James', lastName: 'Milner', username: '@jmil' },
];

const HomePage: React.FC = () => {
  return (
    <div id='home-page'>
      <section className='home-header'>
        <h4>This is Home Header</h4>
      </section>
      <div className='home-content'>
        <h6>This is the Main Content of Home Page</h6>
        <div className='home-table-wrapper'>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>#</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Username</th>
              </tr>
            </thead>
            <tbody>
              {dummyData.map((each) => {
                return (
                  <tr key={each.id}>
                    <td>{each.id}</td>
                    <td>{each.firstName}</td>
                    <td>{each.lastName}</td>
                    <td>{each.username}</td>
                  </tr>
                );
              })}
            </tbody>
          </Table>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
