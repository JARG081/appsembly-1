import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
// import { LoginCard } from './cards/LoginCard.jsx'
// import { LoginForm } from './components/LoginForm.jsx'
import { App } from './App.jsx'
import { UserProvider } from './context/UserProvider.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <UserProvider>
      <App />
    </UserProvider>
  </React.StrictMode>,
)
