/* eslint-disable react/prop-types */
export const ShowCard = ({show, children}) => {
  return (
    <>
        {
            show && (
                <div className="fixed inset-0 flex flex-col items-center justify-center bg-black backdrop-blur-sm bg-opacity-75">
                    {children}
                </div>
            )
        }
    </>
  )
}
