import React from 'react';
import './App.css';
import Home from './components/Home'
import Navigation from './components/Navigation'
import Signup from './components/Signup'
import { Route, BrowserRouter as Router } from 'react-router-dom'

function App() {
  return (
    <div className="App">
      <Navigation/>
      <Router>
      <Route exact path="/" component={App} />
        <Route exact path="/home" component={Home}/>
        <Route exact path="/sign-up" component ={Signup}/>
      </Router>
    </div>
  );
}

export default App;
