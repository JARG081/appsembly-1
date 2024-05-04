import { Children } from "react";
import { CiWarning } from "react-icons/ci";

export const WarningCard = ({children}) => {
  return (
    <>
      <div className="max-w-md px-2 pb-0 w-[90%] md:w-[100%] shadow-md shadow-yellow-900 rounded-lg bg-yellow-500">
        <div className="flex flex-col">
          <div className="flex items-start gap-1">
            <CiWarning className="text-5xl" />
            <p className="text-lg py-2">{children}</p>
          </div>
        </div>
      </div>
    </>
  );
};
