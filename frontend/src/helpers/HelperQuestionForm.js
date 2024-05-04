export const HelperQuestionForm = async (formData, token, handleSuccessful) => {
    try {
      const response = await fetch("http://localhost:8080/create/question", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        handleSuccessful();
      }
    } catch (error) {
      console.log("Hubo un error: " + error);
    }
};