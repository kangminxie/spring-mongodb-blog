import React from 'react';
import MenuItemList from './MenuItemList';

import './sideMenu.styles.scss';

export const SideMenu = () => {
  return (
    <section id='admin-side-menu'>
      <div className='greeting'>Hello, MountKingX</div>
      <MenuItemList />
    </section>
  );
};
