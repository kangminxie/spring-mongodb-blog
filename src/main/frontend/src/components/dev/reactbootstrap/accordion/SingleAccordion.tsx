import React from 'react';
import { Accordion, Card } from 'react-bootstrap';
import { ContextAwareToggle } from '../util/ContextAwareToggle';
import { EntryProps } from './types';

export const SingleAccordion = (props: EntryProps) => {
  const { id, headerContent, bodyContent, isOpen } = props;
  const active = isOpen ? '0' : '1';
  return (
    <Accordion defaultActiveKey={active}>
      <Card key={`single-accordion-${id}`}>
        <Card.Header>
          <ContextAwareToggle eventKey='0'>{headerContent}</ContextAwareToggle>
        </Card.Header>
        <Accordion.Collapse eventKey='0'>
          <Card.Body>{bodyContent}</Card.Body>
        </Accordion.Collapse>
      </Card>
    </Accordion>
  );
};
