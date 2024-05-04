import { useForm } from "react-hook-form";

export const LoginForm = () => {
    const { register, formState: { errors }, handleSubmit, watch } = useForm();

    const onSubmit = async (formData) => {
        try {
            onSubmit
            const response = await fetch('http://localhost:8080/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });
            const data = await response.json();

            // Manejar la respuesta del servidor según tus necesidades (redireccionar, guardar token, etc.)
            console.log(data);
        } catch (error) {
            console.log("Hubo un error: " + error);
        }
    };

    return (
        <>
        <div className="text-black flex min-h-screen flex-col items-end mr-5 pt-16 sm:justify-center sm:pt-0">
            <div className="relative  mt-12 w-full max-w-lg sm:mt-10">
                <div className="relative -mb-px h-px w-full bg-gradient-to-r from-transparent via-sky-300 to-transparent"
                    >asdasdasd</div>
                <div
                    className="mx-5 border-2 border-slate  shadow-slate-500/10 dark:shadow-white/20 rounded-lg  sm:shadow-sm lg:rounded-xl lg:shadow-none">
                    <div className="flex flex-col p-6">
                        <h3 className="text-xl mx-auto font-semibold leading-6 tracking-tighter">Inicia sesión</h3>
                        
                    </div>
                    <div className="p-6 pt-0">
                        <form onSubmit={handleSubmit(onSubmit)}>
                            <div>
                                <div>
                                    {errors.email && <p>{errors.email.message}</p>}
                                    <div
                                        className="group relative rounded-lg border focus-within:border-sky-200 px-3 pb-1.5 pt-2.5 duration-200 focus-within:ring focus-within:ring-sky-300/30">
                                        <div className="flex justify-between">
                                            
                                            <label
                                                htmlFor="email" className="text-xs font-medium text-muted-foreground group-focus-within:text-white text-gray-400">email</label>
                                            <div className="absolute right-3 translate-y-2 text-green-200">
                                                {/* <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                                    fill="currentColor" className="w-6 h-6">
                                                    <path fillRule="evenodd"
                                                        d="M2.25 12c0-5.385 4.365-9.75 9.75-9.75s9.75 4.365 9.75 9.75-4.365 9.75-9.75 9.75S2.25 17.385 2.25 12Zm13.36-1.814a.75.75 0 1 0-1.22-.872l-3.236 4.53L9.53 12.22a.75.75 0 0 0-1.06 1.06l2.25 2.25a.75.75 0 0 0 1.14-.094l3.75-5.25Z"
                                                        clipRule="evenodd" />
                                                </svg> */}
                                            </div>
                                        </div>
                                        <input type="text" id="email" name="email" placeholder="correo"
                                            autoComplete="off"
                                            className="block w-full border-0 bg-transparent p-0 text-sm file:my-1 file:rounded-full file:border-0 file:bg-accent file:px-4 file:py-2 file:font-medium placeholder:text-muted-foreground/90 focus:outline-none focus:ring-0 sm:leading-7 text-foreground" 
                                            {...register('email', {
                                                required: 'Este campo es requerido',
                                                pattern: {
                                                    value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/i,
                                                    message: 'El formato del correo es incorrecto',
                                                },
                                            })}
                                            />
                                    </div>
                                </div>
                            </div>
                            <div className="mt-4">
                                <div>
                                    {errors.password && <p>{errors.password.message}</p>}
                                    <div
                                        className="group relative rounded-lg border focus-within:border-sky-200 px-3 pb-1.5 pt-2.5 duration-200 focus-within:ring focus-within:ring-sky-300/30">
                                        <div className="flex justify-between">
                                            <label
                                                htmlFor="password" className="text-xs font-medium text-muted-foreground group-focus-within:text-white text-gray-400">Password
                                            </label>
                                        </div>
                                        <div className="flex items-center">
                                            <input type="password" id="password" name="password" placeholder="contraseña"
                                                className="block w-full border-0 bg-transparent p-0 text-sm file:my-1 placeholder:text-muted-foreground/90 focus:outline-none focus:ring-0 focus:ring-teal-500 sm:leading-7 text-foreground" 
                                                {...register('password', { required: 'Este campo es requerido' })}
                                            />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="mt-4 flex items-center justify-end gap-x-2">
                                {/* <a className="inline-flex items-center justify-center rounded-md text-sm font-medium transition-all focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:ring hover:ring-white h-10 px-4 py-2 duration-200"
                                    href="/register">Register</a> */}
                                <button
                                    className="font-semibold hover:bg-black hover:text-white hover:ring hover:ring-white transition duration-300 inline-flex items-center justify-center rounded-md text-sm focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-white text-black h-10 px-4 py-2"
                                    type="submit">Inicia sesión</button>
                            </div>
                            <div className="mt-4 flex items-center justify-between">
                               
                                <a className="text-sm font-medium text-foreground mx-auto" href="/forgot-password">
                                            ¿Olvidaste tu contraseña?
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </>
    );
};
