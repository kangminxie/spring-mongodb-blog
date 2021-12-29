import React from 'react';
import MultiAccordion from './accordion/MultiAccordion';

const data = [
  { id: '0', headerContent: 'Carnegie Mellon University', bodyContent: 'CMU' },
  {
    id: '1',
    headerContent: 'University of South Carolina',
    bodyContent: 'USCarolina',
  },
];

export const MySchoolAccordion = () => {
  return <MultiAccordion data={data} activeKey='0' />;
};
