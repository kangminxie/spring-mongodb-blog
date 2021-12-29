import React from 'react';
import {
  Accordion,
  Badge,
  Button,
  Card,
  CardGroup,
  Col,
  Form,
  Row,
} from 'react-bootstrap';
import { ControlledCarousel } from './ControlledCarousel';
import { TheButtons } from '../../../components/dev/reactbootstrap/TheButtons';
import { SingleAccordion } from '../../../components/dev/reactbootstrap/accordion/SingleAccordion';
import { MySchoolAccordion } from '../../../components/dev/reactbootstrap/MySchoolAccordion';

import 'holderjs';
import '../../../components/dev/reactbootstrap/accordion/accordion.styles.scss';

type SelectionOption = {
  label: string;
  value: string;
};

type SingleSelectionProps = {
  options: SelectionOption[];
  handleSelectionChange: (event: any) => any;
};

const ReactSingleSelectTestSection = (props: SingleSelectionProps) => {
  const { options, handleSelectionChange } = props;
  return (
    <Form>
      <Form.Group controlId='exampleForm.ControlSelect1'>
        <Form.Label>Example of Simple Select</Form.Label>
        <Form.Control as='select' onChange={handleSelectionChange}>
          <option key={'no-selection'} label={'--- Please Select One ---'}>
            {''}
          </option>
          {options.map((each) => (
            <option key={each.label} label={each.label}>
              {each.value}
            </option>
          ))}
        </Form.Control>
      </Form.Group>
    </Form>
  );
};

class ReactBootstrapSection extends React.Component<any> {
  componentDidMount() {
    // const elements = document.getElementById("dev-page-holder");
    // Holder.run({
    //   images: elements,
    // });
  }

  handleSingleSelectionChange = (event: any) => {
    event.preventDefault();
    // eslint-disable-next-line no-console
    console.log('### event.target' + event.target.value);
  };

  render() {
    return (
      <div className='dev-page-content-section' id='dev-page-holder'>
        <Row>
          <Col md={6}>
            <TheButtons />
            <div className='test-accordion my-3'>
              <h3>Accordion and Badges</h3>
              <MySchoolAccordion />
              <hr />
              <SingleAccordion
                id='s1'
                bodyContent='b1'
                headerContent='hd1'
                isOpen={true}
              />
              <SingleAccordion id='s2' bodyContent='b2' headerContent='hd2' />
              <SingleAccordion id='s3' bodyContent='b3' headerContent='hd3' />
              <hr />
              <Accordion defaultActiveKey='1'>
                <Card>
                  <Accordion.Toggle as={Card.Header} eventKey='0'>
                    Carnegie Mellon University{' '}
                    <Badge variant='info'>August 2018</Badge>
                  </Accordion.Toggle>
                  <Accordion.Collapse eventKey='0'>
                    <Card.Body>M.S in Information Technology</Card.Body>
                  </Accordion.Collapse>
                </Card>
                <Card>
                  <Accordion.Toggle as={Card.Header} eventKey='1'>
                    University of South Carolina{' '}
                    <Badge variant='warning'>December 2016</Badge>
                  </Accordion.Toggle>
                  <Accordion.Collapse eventKey='1'>
                    <Card.Body>Ph.D in Physical Chemistry</Card.Body>
                  </Accordion.Collapse>
                </Card>
                <Card>
                  <Accordion.Toggle as={Card.Header} eventKey='2'>
                    University of Science and Technology of China{' '}
                    <Badge variant='primary'>July 2011</Badge>
                  </Accordion.Toggle>
                  <Accordion.Collapse eventKey='2'>
                    <Card.Body>B.S. in Chemistry</Card.Body>
                  </Accordion.Collapse>
                </Card>
              </Accordion>
            </div>

            <hr />
            <div className='test-card my-3'>
              <h3>Card</h3>
              <div className='my-2 mx-1 row'>
                <Card style={{ width: '18rem' }}>
                  <Card.Img variant='top' src='holder.js/100px180' />
                  <Card.Body>
                    <Card.Title>Card Title 1</Card.Title>
                    <Card.Text>
                      Some quick example text to build on the card title and
                      make up the bulk of the card&apos;s content.
                    </Card.Text>
                    <Button variant='primary'>Go somewhere</Button>
                  </Card.Body>
                </Card>
                <Card style={{ width: '18rem' }}>
                  <Card.Img variant='top' src='holder.js/100px180' />
                  <Card.Body>
                    <Card.Title>Card Title 2</Card.Title>
                    <Card.Text>
                      Some quick example text to build on the card title and
                      make up the bulk of the card&apos;s content.
                    </Card.Text>
                    <Button variant='success'>Go somewhere</Button>
                  </Card.Body>
                </Card>
              </div>
              <div className='my-2'>
                <CardGroup>
                  <Card style={{ width: '18rem' }}>
                    <Card.Img variant='top' src='holder.js/100px180' />
                    <Card.Body>
                      <Card.Title>Card Title</Card.Title>
                      <Card.Text>
                        Some quick example text to build on the card title and
                        make up the bulk of the card&apos;s content.
                      </Card.Text>
                      <Button variant='primary'>Go somewhere</Button>
                    </Card.Body>
                  </Card>
                  <Card style={{ width: '18rem' }}>
                    <Card.Img variant='top' src='holder.js/100px180' />
                    <Card.Body>
                      <Card.Title>Card Title</Card.Title>
                      <Card.Text>
                        Some quick example text to build on the card title and
                        make up the bulk of the card&apos;s content.
                      </Card.Text>
                      <Button variant='success'>Go somewhere</Button>
                    </Card.Body>
                  </Card>
                  <Card style={{ width: '18rem' }}>
                    <Card.Img variant='top' src='holder.js/100px180' />
                    <Card.Body>
                      <Card.Title>Card Title</Card.Title>
                      <Card.Text>
                        Some quick example text to build on the card title and
                        make up the bulk of the card&apos;s content.
                      </Card.Text>
                      <Button variant='warning'>Go somewhere</Button>
                    </Card.Body>
                  </Card>
                </CardGroup>
              </div>
            </div>
          </Col>

          <Col md={6}>
            <div className='test-select my-3 mr-4'>
              <h3>React-Boostrap Select</h3>
              <ReactSingleSelectTestSection
                options={[
                  { label: 'One', value: '1' },
                  { label: 'Two', value: '2' },
                  { label: 'Three', value: '3' },
                  { label: 'Four', value: '4' },
                ]}
                handleSelectionChange={this.handleSingleSelectionChange}
              />
              <Form>
                <Form.Group controlId='exampleForm.ControlSelect2'>
                  <Form.Label>Example multiple select</Form.Label>
                  <Form.Control as='select' multiple>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                  </Form.Control>
                </Form.Group>
                <Row>
                  <Col md={6}>
                    <Form.Group controlId='date'>
                      <Form.Label>Select Date</Form.Label>
                      <Form.Control
                        type='date'
                        name='date'
                        placeholder='Date of Birth'
                      />
                    </Form.Group>
                  </Col>
                  <Col md={6}>
                    <Form.Group controlId='time'>
                      <Form.Label>Select Time</Form.Label>
                      <Form.Control
                        type='time'
                        name='time'
                        placeholder='Time to Send'
                      />
                    </Form.Group>
                  </Col>
                </Row>
              </Form>
            </div>

            <hr />
            <div className='test-carousel my-3'>
              <h3>Controlled Carousel</h3>
              <ControlledCarousel />
            </div>
          </Col>
        </Row>
      </div>
    );
  }
}

export default ReactBootstrapSection;
