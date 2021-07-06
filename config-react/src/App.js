import './App.css';
import React from 'react';
import Nav from './components/Nav/Nav';
import NavBar from './NavBar';
import { TypeaHead } from './container/TypeaHead/TypeaHead';
// import ProjectList from './components/ProjectList/ProjectList';
import Footer from './components/Footer/Footer';


function App() {
  return (
    <React.Fragment>
      <Nav />
      <TypeaHead />
      <NavBar/>
     {/* <ProjectList /> */}
     <Footer />
    </React.Fragment>
  );
}

export default App;
