import React from 'react'
import ReactDOM from 'react-dom/client'
//import App from './App.jsx'
import "./styles.scss"
import "./locales"

import SingUp from './pages/SingUp/Index.jsx'



ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <SingUp/>
  </React.StrictMode>,
)
