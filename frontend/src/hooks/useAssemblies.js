import { useState,useEffect, useContext } from "react"
import { getAssembliesHelper } from "../helpers/GetAssemblies"
import { UserContext } from "../context/UserContext"

export const useAssemblies = ({url,dataBody}) => {

  const {user} = useContext(UserContext);
  
  const token = user.jwt;
  const [assemblies, setAssemblies] = useState([])

  useEffect(() => {
    
    const fetchData = async () => {
      try {
        
        const data = await getAssembliesHelper({url,token,dataBody});
        setAssemblies(data);
      } catch (error) {
        console.log('Hubo un error al obtener las asambleas:', error)
      }
    }
    
    fetchData();
    
  }, [])
  
  return [assemblies,setAssemblies];
}
