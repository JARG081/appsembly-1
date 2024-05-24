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
  