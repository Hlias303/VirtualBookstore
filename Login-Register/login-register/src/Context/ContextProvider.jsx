import React, { createContext, useContext, useState } from "react"

export const userContext = createContext();

let currentToken = localStorage.getItem("token") || null;

export const ContextProvider = ({children}) => {
    const token = currentToken; // Store for direct access  
    const [role,SetRole] = useState(null);
    
    return(
        <userContext.Provider value={{role,SetRole}}>
            {children}
        </userContext.Provider>
    );
};
