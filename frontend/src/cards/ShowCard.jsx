/* eslint-disable react/prop-types */
export const ShowCard = ({show, children}) => {
  return (
    <>
        {
            show && (
                <div className="fixed inset-0 flex pt-24 flex-col items-center justify-start bg-black bg-opacity-50">
                    {children}
                </div>
            )
        }
    </>
  )
}
