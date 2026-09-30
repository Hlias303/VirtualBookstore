import React, { useContext, useEffect, useState } from 'react'
import "../Styles/Header.css"
import {Link, useNavigate } from "react-router-dom"
import Cookies from 'universal-cookie'
import {userContext} from "../Context/ContextProvider.jsx";

function Header() {
    
    const navigate = useNavigate();
    const cookies = new Cookies();
    
    const token = cookies.get('token');
    console.log("cookies :",token);
    const {role,SetRole} = useContext(userContext);


    // Clear cookie when it expires/gets removed from another place
    useEffect(() => {
        if (!token) {
            SetRole(null);   // If token cleared elsewhere (e.g. backend rejected request), also clear client state
        }
    }, [token]);

    const Logout = (e) =>{
        e.preventDefault();

        // Clear cookie and role BEFORE navigating (fixes race condition when page might re-render with old auth)
        cookies.remove('token',{ path: '/' });
        
        // Reset user state  
        SetRole(null);
        
        navigate('/');  // Go home route instead of /User which redirects to / anyway 
    }

    return (<nav className='nav'>
      <Link to="/" className='site-title'>Virtual Book Store</Link>
      <ul>
        <li>
          <Link to='/Books'>Books</Link>
        </li>
        {role === "Admin" ? (<li>
          <Link to="/Users">Users</Link>
        </li>) : null}
        {!token ? (
          <li><Link to='/Login'>Login</Link></li>
        ) : (
          <li><Link to="/" onClick={Logout}>Logout</Link></li>
        )}
      </ul>
    </nav>)
}
export default Header
