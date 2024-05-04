/* eslint-disable react/prop-types */
export const ShowCard = ({show, children}) => {
  return (
    <>
        {
            show && (
                <div className="fixed inset-0 flex pt-24 flex-col items-center justify-start bg-red-500 bg-opacity-50">
                    {children}
                </div>
            )
        }
    </>
  )
}
