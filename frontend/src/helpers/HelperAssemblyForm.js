export const onSubmitAssemblyHelper = async (formData, token, handleSuccessful) => {
    try {
      console.log("ESTOY TRATANDO DE ENVIAR EL CREAR ASAMBLEA")
      const response = await fetch("http://localhost:8080/create/assembly", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        console.log(handleSuccessful()); 
      }
    } catch (error) {
      console.log("Hubo un error: " + error);
    }
};