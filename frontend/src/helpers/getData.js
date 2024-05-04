export const getData = async (url,token) => {
    try {
      const response = await fetch(`${url}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
      if (!response.ok) {
        throw new Error('Error al obtener los objetos');
      }
      const data = await response.json();
      return data;
    } catch (error) {
      console.error('Error al obtener los objetos:', error);
      throw error;
    }
  };
  
//   export const guardarObjeto = async (nuevoObjeto, token) => {
//     try {
//       const response = await fetch('/api/objetos', {
//         method: 'POST',
//         headers: {
//           'Content-Type': 'application/json',
//           'Authorization': `Bearer ${token}` // Se agrega el token en el encabezado
//         },
//         body: JSON.stringify(nuevoObjeto)
//       });
//       if (!response.ok) {
//         throw new Error('Error al guardar el objeto');
//       }
//       const data = await response.json();
//       return data;
//     } catch (error) {
//       console.error('Error al guardar el objeto:', error);
//       throw error;
//     }
//   };
  