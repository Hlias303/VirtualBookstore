import React, { useState } from "react"
import axios from "axios"
import "../Styles/HomePage.css"
import {userContext} from "./../Context/ContextProvider.jsx";
import {useEffect, useContext} from "react"

function HomePage() {
  const [recommendedBooks, setRecommendedBooks] = useState([]);
  const {role} = useContext(userContext);
  
  // Load and show recommendations only when logged in (role is not null)
  useEffect(() => {
    if (!role) {
      return;
    }

    const loadRecommendations = async () => {
      try {
        const response = await axios.get("http://localhost:8080/Recommendations");
        setRecommendedBooks(response.data || []); 
      } catch (error) {
        console.log("Could not load recommendations:", error.message);
      }
    };
    
    loadRecommendations();
  }, [role]);

  return (
    <div className="Center">
      <h1>Welcome To Virtual Book Store</h1>
      
      {/* Only show recommendations if user is logged in AND we have books */}
      {role && recommendedBooks.length > 0 && (
        <div className="recommendations">
          <h2>You May Also Like:</h2>
          
          <div className="book-cards">
            {recommendedBooks.map(book => (
              <div key={book.id} className="book-card" onClick={() => window.location.href = `/Books/${book.id}`} style={{cursor: 'pointer'}}>
                <img 
                  src="/images/placeholder.jpg" 
                  alt={book.name} 
                  className="book-thumb" 
                  onError={(e) => { e.target.src = `data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='120' height='170'%3E%3Crect fill='%23ddd' width='100%25' height='100%25'/ %3Ctext text-anchor='center' dy='.3em'%3e${book.name.substring(0, 1)}%3C/text%3E%3C/svg%3E`;}}
                />
                
                <h3>{book.name}</h3>
                <p className="price">${book.price}</p>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
}

export default HomePage
