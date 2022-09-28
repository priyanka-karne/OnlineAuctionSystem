import React, {useState, useEffect} from 'react'
import { Link, useHistory }from 'react-router-dom'
import { Button } from './Button';
import './Navbar.css';

function Navbar() {


    
    const [click, setClick] = useState(false);
    const [button, setButton] = useState(true);

    const handleClick = () => setClick(!click);
    const closeMobileMenu = () => setClick(false);

    const logout = () =>{
            sessionStorage.clear();
            window.location="/";
          };
    

    const showButton = () => {
        if (window.innerWidth <= 960){
            setButton(false);
        }
        else{
            setButton(true);
        }
    };

    useEffect(() => {
        showButton();
    },[]);

    window.addEventListener('resize',showButton);

    let logged = sessionStorage.getItem("consumer");
    console.log(logged);
    
  return (
    <>
    <nav className="navbar">
        <div className='navbar-container'>
            <Link to="/" className="navbar-logo" onClick={closeMobileMenu}>
                AUCFO<i className='fab fa-typo3'/>                
            </Link>
            <div className='menu-icon' onClick={handleClick}>
                <i className={click ? 'fas fa-times': 'fas fa-bars'}/>
            </div>
            <ul className={click ? 'nav-menu active' : 'nav-menu'}>
                <li className='nav-items'>
                    <Link to='/' className='nav-links' onClick={closeMobileMenu}>
                        Home
                    </Link>
                </li>
                <li className='nav-items'>
                    <Link to='/products' className='nav-links' onClick={closeMobileMenu}>
                        Products
                    </Link>
                </li>
                {logged &&
                <li className='nav-items logout'>
                    <button className='nav-links' onClick={logout}>
                        Log Out
                    </button>
                </li>}

                {!logged &&
                <li className='nav-items sign'>
                    <Link to='/sign-in' className='nav-links' onClick={closeMobileMenu}>
                        Sign In
                    </Link>
                </li>}

                {!logged &&
                <li className='nav-items sign'>
                    <Link to='/sign-up' className='nav-links' onClick={closeMobileMenu}>
                        Sign Up
                    </Link>
                </li>}
            </ul>
        </div>
    </nav>
    </>
  )
}

export default Navbar