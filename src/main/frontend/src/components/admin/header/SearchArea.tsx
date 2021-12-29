import React from 'react';
import { fsIcon } from '../../common/icon/FsUtil';

import './searchArea.styles.scss';

const SearchArea = () => {
  return (
    <div className='search-area'>
      {fsIcon('plus')} Search
      {/*<input type="text"*/}
      {/*       name="search-content"*/}
      {/*       value="search for..."*/}
      {/*/>*/}
    </div>
  );
};

export default SearchArea;
