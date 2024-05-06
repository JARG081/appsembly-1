/* eslint-disable react/prop-types */
import { useState } from "react";
import { QuestionCard } from "../cards/admin/QuestionCard";
import { SuccessfulCard } from "../cards/SuccessfulCard";
import { ShowCard } from "../cards/ShowCard";

export const AssemblyFragment = ({ assembly, successfulAssembly,handleSuccessful }) => {

  const [showQuestionForm, setShowQuestionForm] = useState(false);

  const handleShowQuestionForm = () => {
    setShowQuestionForm(!showQuestionForm);
  }


  return (
    <>
      <div className="hover:bg-slate-300  rounded-xl border-2 mb-2 py-1">
        <button onClick={handleShowQuestionForm} className="grid grid-cols-2 w-full items-center ">
          <div className="flex justify-start pl-4">
            <p>{assembly.title}</p>
          </div>
          <div className="flex flex-col space-y-2 items-end pr-2">
            <p>fecha: {assembly.startDate}</p>
            <p>Estado: {assembly.status ? "Activa" : "Finalizada"}</p>
          </div>
        </button>
        <ShowCard show={showQuestionForm}>
          <SuccessfulCard text={"Pregunta creada exitosamente"} show={successfulAssembly}/>
          <div className="bg-white rounded-lg m-4">
            <QuestionCard  assembly={assembly} successfulAssembly={successfulAssembly} handleSuccessful={handleSuccessful} handleShowForm={handleShowQuestionForm} />
          </div>
        </ShowCard>
      </div>
    </>
  )
}