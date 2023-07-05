import React from 'react';
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import ButtonAppBar from './components/ButtonAppBar';
import Student from './components/Student';

function App() {
  return (
    <div className="App">
      <ButtonAppBar/>
      <Student/>
    </div>
  );
}

export default App;
