import React, {useEffect, useState, useContext} from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import Stack from '@mui/material/Stack';
import "../Styles/Book.css"
import Cookies from 'universal-cookie'
import { userContext } from '../Context/ContextProvider.jsx'

function Book() {
  const {id} = useParams();
  const [book, SetBook] = useState(null);
  const [imageUrl, setImageUrl] = useState(null);

  const navigate = useNavigate();
  const cookies = new Cookies();

  // Safely read role from ContextProvider (|| {} prevents crash if provider missing)
  const { role } = useContext(userContext) || {};

  console.log("Book component mounted with ID:", id, "| Role:", role);

  useEffect(() => {
    if (!id) {
      navigate("/Books");
      return;
    }

    const FetchBook = async () => {
      try {
        // First fetch book data
        const response = await axios.get(`http://localhost:8080/Books/${id}`);

        console.log("✓ Book fetched:", response.data.name || "unknown");
        SetBook(response.data);

        // Try to fetch the book's cover image
        try {
          const imageResponse = await axios.get(`http://localhost:8080/Books/${id}/image`, {
            responseType: 'blob'
          });

          if (imageResponse.status === 200 && imageResponse.data && imageResponse.data.size > 0) {
            const imageURL = URL.createObjectURL(imageResponse.data);
            setImageUrl(imageURL);
            console.log("✓ Image loaded successfully for:", response.data.name);
          } else {
            console.warn("⚠ Backend returned empty/no image data for book:", response.data.name);
          }
        } catch (e) {
          console.error(`Failed to load image for ${response.data.name || id}:`, e.message);
        }

      } catch (error) {
        console.error("Failed to fetch book details for ID:", id, error.response?.status || error.message);
        SetBook({
          name: "Error loading page",
          description: "Cannot display this book. Please check network or login.",
          price: 0,
          category: "",
          release_date: ""
        });
      }
    };

    FetchBook();
  }, [id]);

  const DeleteBook = async () => {
    const token = cookies.get("token");
    try {
      await axios.delete(`http://localhost:8080/Books/${id}`, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      alert("Product deleted successfully");
      navigate("/Books");
    } catch (error) {
      console.error("Error deleting product:", error);
      alert("Delete failed: " + (error.response?.data?.message || error.message));
    }
  };

  const handleEditClick = () => {
    navigate(`/BookUpdate/${id}`);
  };

  if (!book) {
    return <div style={{textAlign: 'center', color: '#333'}}>Loading...</div>;
  }

  // First letter of the book name as placeholder fallback
  const placeholderText = ((book.name || "?").substring(0, 1)).toUpperCase();

  return (
    <>
      <div className="containers">
        {/* Display image if we have actual fetched blob URL */}
        {imageUrl ? (
          <img src={imageUrl} alt={book.name || 'book'} className="left-column-img" />
        ) : (
          // No imageUrl - show placeholder gradient with first letter
          <div className="left-column-img placeholder-cover">
            {placeholderText}
          </div>
        )}

        <div className="right-column">
          <div className="book-description">
            <div style={{ display: 'flex', justifyContent: 'space-between' }}>
              <span className="category">{book.category || "General"}</span>
              <p className="release-date">
                <h6>Listed: <i>{book.release_date ? new Date(book.release_date).toLocaleDateString() : "N/A"}</i></h6>
              </p>
            </div>

            <h1 className="name">{book.name || "Unknown Book Title - fetch failed"}</h1>

            <p className="description-label">BOOK DESCRIPTION:</p>
            <p className="description-content">{book.description || "No description available."}</p>
          </div>

          <div className="book-price">
            <span>{book.price ? book.price + "$" : "$0"}</span>
          </div>
        </div>

        {/* Admin buttons - shown only when ContextProvider role is "Admin" */}
        {role === 'Admin' && (
          <Stack spacing={10}>
            <div style={{display: 'flex', justifyContent: 'center', gap: '2rem'}}>
              <Button variant='outlined' startIcon={<DeleteIcon/>} onClick={DeleteBook}>
                Delete
              </Button>
              <Button variant='contained' onClick={handleEditClick}>Update</Button>
            </div>
          </Stack>
        )}
      </div>
    </>
  );
}

export default Book
