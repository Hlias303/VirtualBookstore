import React, { useContext, useEffect, useState } from 'react'
import "../Styles/Header.css"
import {Link, useNavigate } from "react-router-dom"
import Cookies from 'universal-cookie'
import {userContext} from "../Context/ContextProvider.jsx";

function Header() {
    
    const navigate = useNavigate();
    const cookies = new Cookies();

    // Keep token in STATE (not just read-once) so UI updates immediately on login/logout
    const [token, setToken] = useState(() => cookies.get('token'));

    const {role, SetRole} = useContext(userContext) || {};

    console.log("cookies :", token);

    // Keep state in sync with the cookie whenever role changes (e.g. after login)
    useEffect(() => {
        setToken(cookies.get('token'));
    }, [role]);

    // Clear role when token disappears (e.g. removed elsewhere)
    useEffect(() => {
        if (!token && typeof SetRole === 'function') {
            SetRole(null);
        }
    }, [token]);

    const Logout = (e) => {
        e.preventDefault();

        // Remove the cookie (with and without explicit path, to cover how it was set)
        cookies.remove('token', { path: '/' });
        cookies.remove('token');

        // Update local state -> header re-renders and switches to "Login"
        setToken(undefined);

        // Reset user role (also clears localStorage via ContextProvider wrapper)
        if (typeof SetRole === 'function') {
            SetRole(null);
        }

        navigate('/');
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
