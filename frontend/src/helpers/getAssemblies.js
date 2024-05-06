export const getAssembliesHelper = async ({url, token, dataBoy}) => {

    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(dataBoy)
        });

        if (!response.ok) {
            throw new Error('La solicitud falló');
        }
        const data = await response.json();
        return data;
        
    } catch (error) {
        console.error('Hubo un error:', error);
        throw error;
    }
};
