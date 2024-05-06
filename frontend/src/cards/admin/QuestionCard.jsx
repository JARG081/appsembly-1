/* eslint-disable react/prop-types */
import { RiCloseLine } from "react-icons/ri";
import { DashboardCard } from "./DashboardCard";
import { useForm } from "react-hook-form";
import { onSubmitAssemblyHelper } from "../../helpers/HelperAssemblyForm";
import { useAssemblies } from "../../hooks/useAssemblies";
import { useContext } from "react";
import { UserContext } from "../../context/UserContext";
import { getAssembliesHelper } from "../../helpers/GetAssemblies";
import { QuestionFragment } from "../../components/QuestionFragment";

export const QuestionCard = ({ handleSuccessful,handleShowForm,successfulAssembly, assembly }) => {
  
  const {user} = useContext(UserContext);

  const token = user.jwt

  const { register, handleSubmit, formState: { errors }, watch, reset } = useForm();
  // const [assemblies,setAssemblies] = useAssemblies({url:"http://localhost:8080/get/assembly"});

  const [questions,setQuestions] = useAssemblies({url:`http://localhost:8080/get/questions?assemblyID=${assembly.assemblyID}`})

  const onSubmitHelper = async (formData) => {
    try {
      await onSubmitAssemblyHelper(formData, token, handleSuccessful, "http://localhost:8080/create/question");
      setQuestions(await getAssembliesHelper({ url: `http://localhost:8080/get/questions?assemblyID=${assembly.assemblyID}`, token }));
      reset();
    } catch (error) {
      console.error("Error al enviar el formulario de la asamblea:", error);
    }
  };
  
  console.log("La pregunta es:", questions)

  return (
    <>
      <div className="bg-white rounded-lg m-4">
        {/* close card */}
        <div className="flex justify-end mb-0.5">
          <RiCloseLine
            onClick={handleShowForm}
            className="text-4xl rounded-full text-purple-800 shadow-md shadow-neutral-800 p-0 hover:text-purple-500 hover:cursor-pointer"
          />
        </div>
        {/* card title */}

           <DashboardCard title={"Preguntas creadas"} customClass={"flex flex-col text-center rounded-md mt-2 overflow-y-auto h-[200px]"}>          
           <div className="border-green-200 border-t-8 border-r-4 border-l-[20px] rounded-t-xl mt-8 pb-2">
            {questions && questions.map(question => (
                  <QuestionFragment key={question.questionID} question={question} />
                ))
              }
           </div>
  
          </DashboardCard> 

        <form onSubmit={handleSubmit(onSubmitHelper)} className={`py-6 w-[600px]  flex flex-col items-start ${successfulAssembly && "cursor-not-allowed"}  `}>

          <div className="mb-4 w-full text-start ">
            <label htmlFor="lastName" className="text-blue-500 mb-2">
              Ingresa la pregunta
            </label>
            <input
              type="text"
              {...register('question', {
                required: 'Este campo es requerido', // fixed typo
              })}
              className="rounded-md py-2 px-3 focus:outline-none w-full  border-2"
            />
            {errors.question && <p className="text-red-700">{errors.question.message}</p>}
            <input
              type="hidden"
              {...register('assemblyID')} defaultValue={`${assembly.assemblyID}`}
              className="rounded-md py-2 px-3 focus:outline-none w-full  border-2"
            />
          </div>

          <button type="submit" className={`bg-blue-600 text-white font-semibold py-2 px-2 rounded-md hover:bg-blue-800 transition duration-300 ease-in-out self-end ${successfulAssembly && "cursor-not-allowed"}`}>
            Envia pregunta
          </button>

        </form>
      </div>
    </>
  )
}
