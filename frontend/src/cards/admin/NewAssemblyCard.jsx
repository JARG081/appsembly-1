/* eslint-disable react/prop-types */
import { RiCloseLine } from "react-icons/ri";


export const FormAssemblyCard = ({ register, handleSubmit, handleShowForm, errors, successfulAssembly, onSubmitHelper }) => {
  return(
    <>
        <div className="right-0 flex justify-end p-2">
            <RiCloseLine
                onClick={handleShowForm}
                className="text-4xl rounded-full text-purple-800 shadow-sm shadow-neutral-600 p-0 hover:text-purple-500 hover:cursor-pointer"
            />
        </div>
        <form onSubmit={handleSubmit(onSubmitHelper)} className={`px-12 py-6 w-full max-w-md flex flex-col items-start justify-between ${successfulAssembly && "cursor-not-allowed"}`}>
        <div className="mb-4 w-full">
            <label htmlFor="nameAsselby" className="text-blue-500 mb-2">
                Nombre de la asamblea
            </label>
            <input 
                type="text" 
                {...register('title', {
                    required: 'Este campo es requerido',
                })} 
                className="rounded-md py-2 px-3 focus:outline-none w-full border-2"
            />
                {errors.title && <p className="text-red-700">{errors.title.message}</p>}
            </div>
            <div className="mb-4 w-full">
                <label htmlFor="description" className="text-blue-500 mb-2">
                    Descripción
                </label>
                <input 
                    type="text" 
                    {...register('proposal', {
                        required: 'Este campo es requerido',
                    })} 
                    className="rounded-md py-2 px-3 focus:outline-none w-full border-2"
                />
                {errors.proposal && <p className="text-red-700">{errors.proposal.message}</p>}
            </div>
            <div className="mb-4 w-full">
                <label htmlFor="" className="text-blue-500 mb-2">
                    Fecha
                </label>
                <input 
                    type="date" 
                    {...register('startDate', {
                        required: 'Este campo es requerido',
                    })} 
                    className="rounded-md py-2 px-3 focus:outline-none w-full border-2"
                />
                {errors.start && <p className="text-red-700">{errors.start.message}</p>}
            </div>
            <button type="submit" className={`bg-blue-600 text-white font-semibold py-2 px-4 rounded-md hover:bg-blue-800 transition duration-300 ease-in-out self-end ${successfulAssembly && "cursor-not-allowed"}`}>
                Crear reunión
            </button>
        </form>
    </>
  )
}
