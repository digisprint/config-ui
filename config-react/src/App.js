import './App.css';
import React from 'react';
import Nav from './components/Nav/Nav';
import Container from './Container';
import { TypeaHead } from './container/TypeaHead/TypeaHead';
// import ProjectList from './components/ProjectList/ProjectList';
import Footer from './components/Footer/Footer';


function App() {
  return (
    <React.Fragment>
      <Nav />
      <TypeaHead />
      <Container/>
     {/* <ProjectList /> */}
     <Footer />
    </React.Fragment>
  );
}

export default App;
