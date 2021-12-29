import React from 'react';
import { Accordion, Card } from 'react-bootstrap';
import { ContextAwareToggle } from '../util/ContextAwareToggle';
import { EntryProps } from './types';

type Props = {
  data: EntryProps[];
  activeKey?: string;
};

const MultiAccordion = (props: Props) => {
  const { data, activeKey } = props;
  const defaultActiveKey = activeKey || '0';
  return (
    <Accordion defaultActiveKey={defaultActiveKey}>
      {data.map((record, index) => {
        return (
          <Card key={`entry-${record.id}`}>
            <Card.Header>
              <ContextAwareToggle eventKey={index.toString()}>
                {record.headerContent}
              </ContextAwareToggle>
            </Card.Header>
            <Accordion.Collapse eventKey={index.toString()}>
              <Card.Body>{record.bodyContent}</Card.Body>
            </Accordion.Collapse>
          </Card>
        );
      })}
    </Accordion>
  );
};

export default MultiAccordion;
