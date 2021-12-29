import React from 'react';
import { SideMenu } from '../../components/admin/sidemenu/SideMenu';
// import Header from '../../components/admin/header/Header';
import SectionedContent from '../../components/admin/content/SectionedContent';

import './adminPage.styles.scss';

const AdminPage = () => {
  return (
    <div id='admin-page'>
      <SideMenu />
      <div className='admin-page-main'>
        {/*<Header/>*/}
        <SectionedContent />
      </div>
    </div>
  );
};

export default AdminPage;
