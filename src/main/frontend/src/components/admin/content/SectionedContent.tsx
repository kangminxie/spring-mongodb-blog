import React from 'react';
import { Route, Switch } from 'react-router-dom';
import DashBoardSection from './section/DashboardSection';
import MyNoteSection from './section/MyNoteSection';
import FinanceSection from './section/FinanceSection';
import CalenderSection from './section/CalenderSection';
import UserSection from './section/UserSection';
import SettingSection from './section/SettingSection';

import './sectionedContent.styles.scss';

const SectionedContent = () => {
  return (
    <section id='dashboard-content'>
      <Switch>
        <Route path='/admin' exact component={DashBoardSection} />
        <Route path='/admin/dashboard' exact component={DashBoardSection} />
        <Route path='/admin/note' exact component={MyNoteSection} />
        <Route path='/admin/finance' exact component={FinanceSection} />
        <Route path='/admin/calendar' exact component={CalenderSection} />
        <Route path='/admin/user' exact component={UserSection} />
        <Route path='/admin/setting' exact component={SettingSection} />
      </Switch>
    </section>
  );
};

export default SectionedContent;
