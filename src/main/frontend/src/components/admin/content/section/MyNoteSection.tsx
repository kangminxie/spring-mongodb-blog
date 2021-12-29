import React from 'react';

import './sectionCommon.styles.scss';

const MyNoteSection = () => {
  return (
    <section className='content-section'>
      <div className='content-section--header'>
        <h3>MyNote Header</h3>
      </div>
      <div className='content-section--main'>I am MyNote section</div>
    </section>
  );
};

export default MyNoteSection;
