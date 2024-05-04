export const getAssembliesHelper = async ({url, token}) => {
    // const token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNzE0NDM5MTg1LCJpYXQiOjE3MTQ0MjQ3ODUsInJvbGVzIjoiVVNFUiJ9.vkJCtcJiBmgIvoVh_gR6FyiRnO3FtRJ8BVpRA3Co9KCZKkImsa0XBHmWXvtS-FSfIBKpWqXbpamXeflG6rHQ9E3RJ8NYkl1SwUUZa6PPKLvbf7txnDwCffv4iECHxbGNrd8wERBhqfuaOnSPpDVIavGOG3PsuKZbHh6pBxN08NPRFr8Ktg0oz1DuRBakEwVris846GCb1VL-LLH4gzyeXIts0SQUJuFJkLuzm5r2upPR3xGvB1mY_bvx8V4qycAZe499CbJN4-ruSYCHn3h0tKbwlKaCaUo8TRu1tz-68edco-n4iPAQ_omWly2g5YZ8XQ_0vsslVhedN4wwo16oWw"

    try {
        const response = await fetch(`${url}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
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
