import React, { useContext } from 'react';
import { AccordionContext, useAccordionToggle } from 'react-bootstrap';
import { ChevronDownIcon, ChevronUpIcon } from '../../../common/icon';

type Props = {
  children?: any;
  eventKey: string;
  callback?: any;
};

// https://react-bootstrap.github.io/components/accordion/
export const ContextAwareToggle = ({ children, eventKey, callback }: Props) => {
  const currentEventKey = useContext(AccordionContext);
  const decoratedOnClick = useAccordionToggle(
    eventKey,
    () => callback && callback(eventKey)
  );

  const isCurrentEventKey = currentEventKey === eventKey;

  return (
    <div onClick={decoratedOnClick}>
      {children}
      <span className='float-right'>
        {isCurrentEventKey ? <ChevronUpIcon /> : <ChevronDownIcon />}
      </span>
    </div>
  );
};
