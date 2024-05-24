
export const ErrorCard = ({handleClose}) => {
  return (
    <>
        <div className="flex bg-white shadow-md shadow-red-400 rounded-lg border-red-500 border-t border-b-2 border-r-4">
            <div className="icon bg-red-600 flex justify-center items-center py-2 px-2 rounded-tr-3xl rounded-lg">
                <button onClick={handleClose} >
                    <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-8 w-8 bg-white rounded-full text-red-600 p-1 hover:text-slate-600 "
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                    strokeWidth={2}
                    >
                    <path
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        d="M6 18L18 6M6 6l12 12"
                    />
                    </svg>
                </button>
            </div>
            <div className="flex flex-col p-4 rounded-tr-lg rounded-br-lg">
                <h2 className="font-semibold text-red-600">Error</h2>
                <p className="text-gray-700">Credenciales incorrectas</p>
            </div>
        </div>
    </>
  )
}
