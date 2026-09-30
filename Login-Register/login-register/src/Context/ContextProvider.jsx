import React, { createContext, useState } from "react"

export const userContext = createContext();

export const ContextProvider = ({children}) => {
    // Initialize role from localStorage so it survives page refreshes
    const [role, SetRoleState] = useState(localStorage.getItem("role") || null);

    // Wrapper that keeps localStorage in sync with React state
    const SetRole = (newRole) => {
        if (newRole) {
            localStorage.setItem("role", newRole);
        } else {
            localStorage.removeItem("role");
        }
        SetRoleState(newRole);
    };

    return(
        <userContext.Provider value={{role, SetRole}}>
            {children}
        </userContext.Provider>
    );
};
