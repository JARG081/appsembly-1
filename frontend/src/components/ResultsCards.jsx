export const ResultsCards = () => {
  return (
    <>
        <div className="bg-white gap-6 flex rounded-xl p-4 w-full drop-shadow-xl border-2 border-transparent hover:border-[#06ff0641] hover:cursor-pointer">
                {/* Title */}
                <div className="w-full md:w-[70%]">
                    <h1>
                    REUNIÓN DE ESTO QUE NO IMPORTA
                    </h1>
                </div>
                {/* Time */}
                <div className="w-full md:w-[30%] space-y-6 flex flex-col items-end">
                    <h3>dd/mm/aaaa</h3>
                    <p>estado: <span>finalizado</span></p>
                </div>
        </div>
    </>
  )
}
