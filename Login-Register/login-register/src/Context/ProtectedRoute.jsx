import React,{ useContext, useEffect } from "react";
import { useNavigate, Outlet } from "react-router-dom";  
import Cookies from 'universal-cookie';
import { userContext } from "./ContextProvider";

const ProtectedRoute = () => {
    const navigate = useNavigate();
    const cookies = new Cookies();
    const { role } = useContext(userContext);
    
    // Check if we're logged in (have a token stored)
    const token = cookies.get('token');
    
    useEffect(() => {
        if (!token || !role) {
            // If not authenticated, redirect to login page
            navigate('/Login');
        }
    }, [token, role, navigate]);
    
    return (
       <Outlet />  // Render the child route if authenticated; will re-render on new navigation
    );
};

export default ProtectedRoute;
