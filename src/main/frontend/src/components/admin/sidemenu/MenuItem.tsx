import React from 'react';
import { Link } from 'react-router-dom';
import { fsIcon, FsIconName } from '../../common/icon/FsUtil';

import './menuItem.styles.scss';

type Props = {
  name: string;
  icon: FsIconName;
  destination: string;
  active: boolean;
  handleClick: (name: string) => any;
};

const MenuItem = ({
  name,
  icon,
  active = false,
  destination,
  handleClick,
}: Props) => {
  return (
    <div className='menu-item' onClick={() => handleClick(name)}>
      <Link className={active ? 'active' : ''} to={'/admin/' + destination}>
        {fsIcon(icon)}&nbsp;&nbsp;{name}
      </Link>
    </div>
  );
};

export default MenuItem;
