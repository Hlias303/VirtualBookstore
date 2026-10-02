import React, { useState } from "react"
import axios from "axios"
import "../Styles/HomePage.css"
import {userContext} from "./../Context/ContextProvider.jsx";
import {useEffect, useContext} from "react"
import Cookies from 'universal-cookie'
import BookItem from './../helpers/BookItem.jsx'

function HomePage() {
  const [recommendedBooks, setRecommendedBooks] = useState([]);
  const {role} = useContext(userContext);
  const cookies = new Cookies();

  // Load and show recommendations only when logged in (role is not null)
  useEffect(() => {
    if (!role) {
      return;
    }

    const loadRecommendations = async () => {
      try {
        const token = cookies.get("token");
        const response = await axios.get("http://localhost:8080/Recommendations", {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });

        // Fetch each recommended book's cover image alongside its data
        const booksWithImages = await Promise.all(
          (response.data || []).map(async (book) => {
            try {
              const imageResponse = await axios.get(
                `http://localhost:8080/Books/${book.id}/image`,
                { responseType: "blob" }
              );
              if (imageResponse.status === 200 && imageResponse.data && imageResponse.data.size > 0) {
                return { ...book, imageUrl: URL.createObjectURL(imageResponse.data) };
              }
              return { ...book, imageUrl: null };
            } catch (error) {
              console.error(`Image fetch failed for book ${book.id}:`, error.message);
              return { ...book, imageUrl: null };
            }
          })
        );

        setRecommendedBooks(booksWithImages);
      } catch (error) {
        console.log("Could not load recommendations:", error.message);
      }
    };

    loadRecommendations();
  }, [role]);

  // Fallback cover: gradient box with the book's first letter
  const fallbackCover = (name) =>
    `data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='120' height='170'%3E%3Crect fill='%23ddd' width='100%25' height='100%25'/%3Ctext text-anchor='middle' x='50%25' dy='.35em'%3E${encodeURIComponent(name ? name.substring(0, 1) : '?')}%3C/text%3E%3C/svg%3E`;

  return (
    <div className="Center">
      <h1>Welcome To Virtual Book Store</h1>

      {role && recommendedBooks.length > 0 && (
        <div className="recommendations">
          <h2>You May Also Like:</h2>

          <div className="book-cards">
            {recommendedBooks.map(book => (
              <BookItem 
                key={book.id} 
                image={book.imageUrl || fallbackCover(book.name)}
                name={book.name}
                price={book.price}
                bookId={book.id}
              />
            ))}
          </div>
        </div>
      )}
    </div>
  );
}

export default HomePage
