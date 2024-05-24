
export const AssemblyFragmentUser = ({assembly}) => {
  return (
    <>
        <div className="hover:bg-blue-100 bg-white  rounded-xl border-2 mb-8 py-1 mx-5">
            <button className="grid grid-cols-2 w-full items-center ">
            <div className="flex justify-start pl-4">
                <p>{assembly.title}</p>
            </div>
            <div className="flex flex-col space-y-2 items-end pr-2">
                <p>fecha: {assembly.startDate}</p>
                <p>Estado: {assembly.status ? "Activa" : "Finalizada"}</p>
            </div>
            </button>
            {/* <ShowCard show={showQuestionForm}>
            <SuccessfulCard text={"Pregunta creada exitosamente"} show={successfulAssembly}/>
            <div className="bg-white rounded-lg m-4">
                <QuestionCard  assembly={assembly} successfulAssembly={successfulAssembly} handleSuccessful={handleSuccessful} handleShowForm={handleShowQuestionForm} />
            </div>
            </ShowCard> */}
      </div>
    </>
  )
}
