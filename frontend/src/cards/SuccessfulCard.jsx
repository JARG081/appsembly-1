
export const SuccessfulCard = ({text,show}) => {
    console.log("Show value in SuccessfulCard:", show);

  return (
    <>
        {
            show && (      
                <div className="max-w-md py-4 fixed top-2 px-16 shadow-2xl shadow-green-800 rounded-lg bg-green-500 p-4 ease-in-out">
                    <div className="flex flex-col">
                        <div className="flex items-center gap-2">
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                className="h-6 w-6 text-white"
                                fill="none"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                                strokeWidth={2}
                            >
                                <path strokeLinecap="round" strokeLinejoin="round" d="M5 13l4 4L19 7" />
                            </svg>
                            <h3 className="text-xl text-white font-semibold">Éxito</h3>
                        </div>
                        <p className="text-white">{text}</p>
                    </div>
                </div>
            )
        }
    </>
  )
  
}
