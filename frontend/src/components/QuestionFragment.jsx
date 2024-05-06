/* eslint-disable react/prop-types */

export const QuestionFragment = ({question}) => {
  return (
    <>
        <div key={question.questionID} className="hover:bg-slate-100 rounded-md border-2 mb-2 mx-1 mt-2 py-1">
            <div className="flex justify-start pl-4">
                <p>{question.questionText}</p>
            </div>
        </div>
    </>
  )
}
