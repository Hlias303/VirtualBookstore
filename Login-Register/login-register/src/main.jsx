import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
// Bootstrap must load FIRST so our custom styles can override it
import 'bootstrap/dist/css/bootstrap.min.css'
import './index.css'
import App from './App.jsx'
import {ContextProvider} from './Context/ContextProvider.jsx'
//import '../src/Styles/HomePage.css'

createRoot(document.getElementById('root')).render(
    
  <ContextProvider>
    <App />
  </ContextProvider>
    
)
