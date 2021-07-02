import './App.css';
import React from 'react';
import {Home} from './templates/UI/Home'
import Tabs from './components/Tabs/Tabs';
import Nav from './components/Nav/Nav';
// import ProjectList from './components/ProjectList/ProjectList';


function App() {
  return (
    <React.Fragment>
      {/* <Home /> */}
      <Nav />
     <Tabs />
     {/* <ProjectList /> */}
    </React.Fragment>
  );
}

export default App;
