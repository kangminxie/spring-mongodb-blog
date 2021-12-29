import React from 'react';
import MenuItem from './MenuItem';
import { FsIconName } from '../../common/icon/FsUtil';

import './menuItemList.styles.scss';

type tabEntry = {
  id: string;
  name: string;
  destination: string;
  icon: FsIconName;
};

const tabs: tabEntry[] = [
  { id: 'tab01', name: 'Dashboard', destination: 'dashboard', icon: 'home' },
  { id: 'tab02', name: 'MyNote', destination: 'note', icon: 'book' },
  { id: 'tab03', name: 'Finance', destination: 'finance', icon: 'money-bill' },
  { id: 'tab04', name: 'Calendar', destination: 'calendar', icon: 'calendar' },
  { id: 'tab05', name: 'User', destination: 'user', icon: 'user' },
  { id: 'tab06', name: 'Setting', destination: 'setting', icon: 'coffee' },
];

class MenuItemList extends React.Component {
  state = {
    activeItem: 'Dashboard',
  };

  _handleClick = (name: any) => {
    this.setState({
      activeItem: name,
    });
  };

  render() {
    return (
      <nav className='menu-item-list'>
        {tabs.map((each) => (
          <MenuItem
            key={each.id}
            name={each.name}
            icon={each.icon}
            active={each.name === this.state.activeItem}
            destination={each.destination}
            handleClick={this._handleClick}
          />
        ))}
      </nav>
    );
  }
}

export default MenuItemList;
