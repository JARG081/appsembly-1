import { useState } from "react";
import { RiCloseLine } from "react-icons/ri";
import { HelperQuestionForm } from "../helpers/HelperQuestionForm";
import { useAssemblies } from "../hooks/useAssemblies";

export const QuestionForm = ({ assembly, registerQuestion, handleSubmitQuestion, handleShowQuestionForm, errorQuestion, token }) => {
  const [successfulUser, setSuccessfulUser] = useState(false);
  const questions = useAssemblies("localhost:8080/get/questions");

  const handleSuccessfulUser = () => {
    setSuccessfulUser(!successfulUser);
  }

  const onSubmitQuestion = (formData) => {
    HelperQuestionForm(formData, token, handleSuccessfulUser);
  };

  return (
    <>
      <div className="bg-white rounded-lg m-4">
        {/* close card */}
        <div className="right-0 flex justify-end p-2">
          <RiCloseLine
            onClick={handleShowQuestionForm}
            className="text-4xl rounded-full text-purple-800 shadow-sm shadow-neutral-600 p-0 hover:text-purple-500 hover:cursor-pointer"
          />
        </div>
        {/* card title */}
        <h1 className="text-center text-3xl">PREGUNTAS</h1>
        <div className="px-12 ">
          <div className="  mt-4 overflow-y-auto  flex flex-col text-center rounded-md  border-2 h-[120px]">
            
          </div>
        </div>

        <form onSubmit={handleSubmitQuestion(onSubmitQuestion)} className={`px-12 py-6 max-w-xl flex flex-col items-start ${successfulUser && "cursor-not-allowed"}  `}>

          <div className="mb-4 w-full text-start">
            <label htmlFor="lastName" className="text-blue-500 mb-2">
              Ingresa la pregunta
            </label>
            <input
              type="text"
              {...registerQuestion('question', {
                required: 'Este campo es requerido', // fixed typo
              })}
              className="rounded-md py-2 px-3 focus:outline-none w-full  border-2"
            />
            {errorQuestion.question && <p className="text-red-700">{errorQuestion.question.message}</p>}
          </div>

          <button type="submit" className={`bg-blue-600 text-white font-semibold py-2 px-4 rounded-md hover:bg-blue-800 transition duration-300 ease-in-out self-end ${successfulUser && "cursor-not-allowed"}`}>
            Envia pregunta
          </button>

        </form>
      </div>
    </>
  )
}