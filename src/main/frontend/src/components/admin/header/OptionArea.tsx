import React from 'react';
import { fsIcon } from '../../common/icon/FsUtil';

import './optionArea.styles.scss';

const OptionArea = () => {
  return (
    <div className='option-area'>
      {/* eslint-disable-next-line */}
      <a href='#' className='add'>
        {fsIcon('plus')} Add
      </a>
      {/* eslint-disable-next-line */}
      <a href='#' className='notification'>
        {fsIcon('plus')}
        <span className='circle'>3</span>
      </a>
      {/* eslint-disable-next-line */}
      <a href='#'>
        <span className='user-img' />
        {fsIcon('plus')}
      </a>
    </div>
  );
};

export default OptionArea;
