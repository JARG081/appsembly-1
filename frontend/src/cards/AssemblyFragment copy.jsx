import { useState } from "react";
import { useForm } from "react-hook-form";
import { formPropsHelper } from "../helpers/formPropsHelper";

export const AssemblyFragment = ({ question }) => {
  const token = "";

  const { register: registerQuestion, handleSubmit: handleSubmitQuestion, formState: { errors: errorQuestion }, watch, reset } = useForm();
  const [showQuestionForm, setShowQuestionForm] = useState(false);

  const handleShowQuestionForm = () => {
    setShowQuestionForm(!showQuestionForm);
  }

  const formQuestionProps = formPropsHelper(registerQuestion,handleSubmitQuestion,handleShowQuestionForm,errorQuestion,null,null,null)
  // const formQuestionProps = {
  //   question,
  //   registerQuestion,
  //   handleSubmitQuestion,
  //   handleShowQuestionForm,
  //   errorQuestion,
  //   token
  // };

  return (
    <>
      <div key={question.questionID} className="hover:bg-slate-300  rounded-xl border-2 mb-2 py-1">
        <div className="flex justify-start pl-4">
          <p>{question.questionText}</p>
        </div>
      </div>
    </>
  )
}