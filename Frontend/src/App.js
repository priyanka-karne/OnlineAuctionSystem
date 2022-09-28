import React from 'react';
import { BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import Navbar from './Components/Navbar';
import Footer from "./Components/Footer";
import './App.css';
import Home from './Components/Pages/Home';
import Bidding from './Components/Pages/Bidding';
import SignIn from './Components/Pages/SignIn';
import SignUp from './Components/Pages/SignUp';
import Products from './Components/Pages/Products';
// import AddItem from './Components/Pages/AddItem';

function App() {
  return (
    <>
    
    <Router>
      <Navbar/>
      <Switch>
        <Route path='/' exact component={Home}/>
        <Route path='/products' component={Products}/>
        <Route path='/bidding' component={Bidding}/>
        {/* <Route path='/add-item' component={AddItem}/> */}
        <Route path='/sign-up' component={SignUp}/>
        <Route path='/sign-in' component={SignIn}/>
      </Switch>
      <Footer/>
    </Router>
    </>
  );
}

export default App;
