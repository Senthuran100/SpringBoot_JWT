import React from 'react';
import './App.css';
import Home from './components/Home'
import Navigation from './components/Navigation'

function App() {
  return (
    <div className="App">
      <Navigation/>
      <Home/>
    </div>
  );
}

export default App;
