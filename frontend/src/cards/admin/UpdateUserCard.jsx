/* eslint-disable react/prop-types */
import { RiCloseLine } from "react-icons/ri";

export const UpdateUserCard = ({ register, handleSubmit, handleShowForm, errors, successfulAssembly, onSubmitHelper }) => {
    return(
        <>
            {/* close card */}
            <div className="right-0 flex justify-end p-2">
                <RiCloseLine
                    onClick={handleShowForm}
                    className="text-4xl rounded-full text-purple-800 shadow-sm shadow-neutral-600 p-0 hover:text-purple-500 hover:cursor-pointer"
                />
            </div>
            
            {/* card title */}
            <h1 className="text-center text-3xl">Actualizando usuario</h1>
            
            <form onSubmit={handleSubmit(onSubmitHelper)} className={`px-12 py-6 mx-auto max-w-xl flex flex-col items-start justify-between ${successfulAssembly && "cursor-not-allowed"}  `}>
                                   
                <div className="mb-4 w-full">
                    <label htmlFor="lastName" className="text-blue-500 mb-2">
                        Ingresa el número de documento del usuario
                    </label>
                    <input 
                        type="text" 
                        {...register('lastName', {
                            equired: 'Este campo es requerido',
                        })} 
                        className="rounded-md py-2 px-3 focus:outline-none w-full border-2"
                    />
                    {errors.lastName && <p className="text-red-700">{errors.lastName.message}</p>}
                </div>

                <button type="submit" className={`bg-blue-600 text-white font-semibold py-2 px-4 rounded-md hover:bg-blue-800 transition duration-300 ease-in-out self-end ${successfulAssembly && "cursor-not-allowed"}`}>
                    Buscar usuario
                </button>
            
            </form>
        </>
      )
}
