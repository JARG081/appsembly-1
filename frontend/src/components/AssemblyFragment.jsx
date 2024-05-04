import { useState } from "react";
import { QuestionForm } from "../cards/QuestionForm";
import { useForm } from "react-hook-form";
import { HelperQuestionForm } from "../helpers/HelperQuestionForm";

export const AssemblyFragment = ({ assembly }) => {
  const token = "";

  const { register: registerQuestion, handleSubmit: handleSubmitQuestion, formState: { errors: errorQuestion }, watch, reset } = useForm();
  const [showQuestionForm, setShowQuestionForm] = useState(false);

  const handleShowQuestionForm = () => {
    setShowQuestionForm(!showQuestionForm);
  }

  const formQuestionProps = {
    assembly,
    registerQuestion,
    handleSubmitQuestion,
    handleShowQuestionForm,
    errorQuestion,
    token
  };

  return (
    <>
      <div key={assembly.assemblyID} className="hover:bg-slate-300  rounded-xl border-2 mb-2 py-1">
        <button onClick={handleShowQuestionForm} className="grid grid-cols-2 w-full items-center ">
          <div className="flex justify-start pl-4">
            <p>{assembly.title}</p>
          </div>
          <div className="flex flex-col space-y-2 items-end pr-2">
            <p>fecha: {assembly.startDate}</p>
            <p>Estado: {assembly.status ? "Activa" : "Finalizada"}</p>
          </div>
        </button>
      </div>
      {showQuestionForm && (
        <div className="fixed inset-0 flex pt-36 flex-col items-center justify-en bg-black bg-opacity-50">
          <QuestionForm {...formQuestionProps} />
        </div>
      )}
    </>
  )
}