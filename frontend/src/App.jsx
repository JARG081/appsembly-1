import { useContext, useEffect } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ProtectedRoute } from "./components/routes/ProtectedRoute";
import { UserContext } from "./context/UserContext";
import { Admin } from "./Admin";
import { User } from "./User";
import { Error } from "./Pages/generalPages/Error";
import { Login } from "./Pages/generalPages/Login";

export const App = () => {
  const { user, setUser } = useContext(UserContext);

  useEffect(() => {
    const userData = localStorage.getItem('userData');
    if (userData) {
      setUser(JSON.parse(userData)); // Rehydrate user state from local storage
    }
  }, []);

  return (
        
        
      <BrowserRouter>
        <Routes>
          
          <Route path="/" element={<Login /> } />
          
          <Route path="*" element={<Error /> } />
          
          <Route element={<ProtectedRoute  isAllowed={!!user && user.role.includes('USER')} />}>
            <Route path="/user" element={<User/>} />
          </Route>  
          
          <Route element={<ProtectedRoute  isAllowed={!!user && user.role.includes('ADMIN')} />}>
            <Route path="/admin" element={<Admin/>} />
          </Route>  
          
        </Routes>
      </BrowserRouter>
  )
}
