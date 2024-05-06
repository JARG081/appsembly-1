export const onSubmitAssemblyHelper = async (formData, token, handleSuccessful, url) => {
    try {
      const response = await fetch(`${url}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        handleSuccessful()
      }
    } catch (error) {
      console.log("Hubo un error: " + error);
    }
};