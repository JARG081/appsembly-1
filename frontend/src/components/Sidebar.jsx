import { RiLogoutBoxRLine } from "react-icons/ri";
import { BsNut } from "react-icons/bs";
import { useContext } from "react";
import { UserContext } from "../context/UserContext";

export const Sidebar = ({ sidebar }) => {
  const { setUser } = useContext(UserContext);

  const handleSetUser = () => {
    setUser(null);
  };

  return (
    <>
      <div
        className={`fixed top-0 ${
          sidebar ? "right-0" : "-right-full"
        } md:w-[40%] lg:w-full shadow-2xl transition-all duration-300 bg-green-500 w-full h-full overflow-y-scroll col-span-1 md:col-span-2 lg:col-span-1 p-8 border-l lg:overflow-y-hidden lg:static `}
      >
        {/* logotipo */}
        <div className="text-center p-8">
          <h1 className="uppercase font-bold tracking-[4px]">Tu logo</h1>
        </div>
        <div className="flex flex-col py-5 justify-between h-[600px] md:h-[400px] lg:h-[550px] -mr-5">
          {/*MENU -- implementar router dom */}
          <nav className="flex flex-col items-center">
            <ul className="space-y-2">
              <li>
                <button
                  onClick={handleSetUser}
                  className="flex items-center gap-2 hover:bg-sky-600 p-4 text-gray-900 hover:text-white rounded-lg transition-colors"
                >
                  cerrar sesión
                  <RiLogoutBoxRLine />
                </button>
              </li>
              <li>
                <a
                  href="#"
                  className="flex bg-slate-100 pointer-events-none disabled:cursor-not-allowed  items-center gap-2 hover:bg-sky-600 p-4 text-gray-900 hover:text-white rounded-lg transition-colors"
                >
                  configuración
                  <BsNut />
                </a>
              </li>
            </ul>
          </nav>
          {/* Image and logout */}
          <div className="flex flex-col items-center align-end">
            {/* <img src="img.svg" alt="Image" className="mb-0 "/> */}
            <a
              href="#"
              className="flex pointer-events-none bg-slate-100 items-center gap-4 hover:bg-sky-600 p-4 text-gray-900 hover:text-white rounded-lg transition-colors"
            >
              Descarga tu información
              <RiLogoutBoxRLine />
            </a>
          </div>
        </div>
      </div>
    </>
  );
};
