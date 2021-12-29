import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faCloud,
  faArrowUp,
  faBlog,
  faCheck,
  faBook,
  faCalculator,
  faCalendar,
  faChevronUp,
  faChevronDown,
  faCoffee,
  faExclamationTriangle,
  faHome,
  faInfo,
  faMoneyBill,
  faPlus,
  faSignInAlt,
  faSignOutAlt,
  faSmile,
  faTimes,
  faUser,
} from '@fortawesome/free-solid-svg-icons';

export type FsIconName =
  | 'admin'
  | 'arrow-up'
  | 'blog'
  | 'book'
  | 'check'
  | 'cloud'
  | 'success'
  | 'calculator'
  | 'calendar'
  | 'chevron-up'
  | 'chevron-down'
  | 'coffee'
  | 'error'
  | 'home'
  | 'info'
  | 'money-bill'
  | 'plus'
  | 'sign-in'
  | 'sign-out'
  | 'user'
  | 'warning'
  | '';

export const fsIcon = (name: FsIconName) => {
  switch (name) {
    case 'admin':
    case 'cloud':
      return <FontAwesomeIcon icon={faCloud} />;
    case 'arrow-up':
      return <FontAwesomeIcon icon={faArrowUp} />;
    case 'blog':
      return <FontAwesomeIcon icon={faBlog} />;
    case 'book':
      return <FontAwesomeIcon icon={faBook} />;
    case 'check':
    case 'success':
      return <FontAwesomeIcon icon={faCheck} />;
    case 'calculator':
      return <FontAwesomeIcon icon={faCalculator} />;
    case 'calendar':
      return <FontAwesomeIcon icon={faCalendar} />;
    case 'chevron-up':
      return <FontAwesomeIcon icon={faChevronUp} />;
    case 'chevron-down':
      return <FontAwesomeIcon icon={faChevronDown} />;
    case 'coffee':
      return <FontAwesomeIcon icon={faCoffee} />;
    case 'error':
      return <FontAwesomeIcon icon={faTimes} />;
    case 'home':
      return <FontAwesomeIcon icon={faHome} />;
    case 'info':
      return <FontAwesomeIcon icon={faInfo} />;
    case 'money-bill':
      return <FontAwesomeIcon icon={faMoneyBill} />;
    case 'plus':
      return <FontAwesomeIcon icon={faPlus} />;
    case 'sign-in':
      return <FontAwesomeIcon icon={faSignInAlt} />;
    case 'sign-out':
      return <FontAwesomeIcon icon={faSignOutAlt} />;
    case 'user':
      return <FontAwesomeIcon icon={faUser} />;
    case 'warning':
      return <FontAwesomeIcon icon={faExclamationTriangle} />;
    default:
      return <FontAwesomeIcon icon={faSmile} />;
  }
};
