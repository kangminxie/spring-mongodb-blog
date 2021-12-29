import React from 'react';

import OptionArea from './OptionArea';
import SearchArea from './SearchArea';

import './header.styles.scss';

const Header = () => {
  return (
    <header className='dashboard-header'>
      <SearchArea />
      <OptionArea />
    </header>
  );
};

export default Header;
