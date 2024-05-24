export const onSubmitUserHelper = async (formData, token, handleSuccessful,reset,method,url) => {
    try {
      const response = await fetch(`${url}`, {
        method: `${method}`,
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(formData),
      });
  
      if (response.ok) {
        handleSuccessful();
        reset();
      }
      
      console.log("El response.text es: " + JSON.stringify(response));


    } catch (error) {
      console.log("Hubo un error: " + error);
    }
  };