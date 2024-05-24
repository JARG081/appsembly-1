import { useContext, useState } from "react";
import { useForm } from "react-hook-form";
import { UserContext } from "../../context/UserContext";

import { useNavigate } from "react-router-dom";
import { ErrorCard } from "../../cards/ErrorCard";

export const Login = () => {
  
  const navigate = useNavigate();
  const { register:registerLogin,handleSubmit:handleSubmitLogin, formState: { errors: errorLogin } } = useForm();
  const { setUser } = useContext(UserContext);
  const [loginError, setLoginError] = useState(false);

  const onSubmitLogin = async (formData) => {
    try {
      setLoginError(false);
      const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        const responseData = await response.json();
        localStorage.setItem('userData', JSON.stringify(responseData));
        setUser(responseData);

        if (responseData.role.includes('USER')) {
          navigate("/user");
        } else {
          navigate("/admin");
        }
      } else {
        setLoginError(true); // Establece el mensaje de error si la respuesta no es exitosa
      }
    } catch (error) {
      console.log("Hubo un error: " + error);
    }
  };

  const handleCloseError = ()=>{
    setLoginError(!loginError);
  }

  return (
    <>
      <section className="bg-slate-900 flex flex-col md:flex-row h-screen justify-center ">

        {/* <Link to="/private" className="bg-red-500 text-6xl">RUTA PRIVADA</Link> */}
        <div className="hidden lg:block w-full md:w-1/2 xl:w-2/3 h-screen">
          <img src="https://source.unsplash.com/random" alt="" className="w-full h-full object-cover" />
        </div>

        <div className=" w-full md:max-w-md lg:max-w-full md:mx-auto  md:w-1/2 xl:w-1/3  px-6 lg:px-4 xl:px-12
              flex items-center justify-center">
          <div className="bg-white w-full pb-8 border-2 shadow-slate-800 shadow-lg h-100 rounded-lg">

            <h1 className="text-center text-4xl font-normal md:text-3xl leading-tight mt-12">Inicia sesión</h1>

            <form onSubmit={handleSubmitLogin(onSubmitLogin)} className="mt-6">
              <div className="px-8">
                <label className="block text-gray-700">Correo electrónico</label>
                <input 
                  type="email"
                  {
                    ...registerLogin('email',{
                      required: 'Este campo es requerido',
                    })
                  } 
                  className="w-full px-4 py-3 rounded-lg bg-gray-200 mt-2 border focus:border-blue-500 focus:bg-white focus:outline-none"
                />
                {errorLogin.email && <p className="text-red-700">{errorLogin.email.message}</p>}

              </div>

              <div className="mt-4 px-8">
                <label className="block text-gray-700">Contraseña</label>
                <input 
                  type="password"
                  id="password" 
                  placeholder="Enter Password" minLength="6" 
                  className="w-full px-4 py-3 rounded-lg bg-gray-200 mt-2 border focus:border-blue-500
                      focus:bg-white focus:outline-none" required 
                  {
                    ...registerLogin('password',{
                      required: 'Este campo es requerido',
                    })
                  }
                />
                {errorLogin.password && <p className="text-red-700">{errorLogin.password.message}</p>}

              </div>

              {/* <div className="text-right mt-2">
                <a href="#" className="text-sm font-semibold text-gray-700 hover:text-blue-700 focus:text-blue-700">Se te olvidó tu contrase{a?</a>
              </div> */}

              <div className="px-8 mb-5">
                <button type="submit" className="w-full block bg-blue-500 hover:bg-blue-4900 focus:bg-blue-800 hover:shadow-xl  text-white font-semibold rounded-lg
                    py-3 mt-6">
                        Log In
                </button>
              </div>
              <div className="px-8">
                {loginError && <ErrorCard handleClose={handleCloseError}/>}
              </div>
            </form>

          </div>
        </div>
      </section>
    </>
  );
};

