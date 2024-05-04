import { IoNotificationsOutline } from "react-icons/io5";
import {RiCheckboxBlankCircleFill, RiArrowDownSLine} from "react-icons/ri";


export const Header = () => {
  return (
    <>
    <header className="flex flex-col md:flex-row items-center justify-end p-4 w-full bg-gray-50">
            
            {/* Notifications */}
            <nav className="w-full flex mb-5 md:mb-0 justify-center md:justify-end md:w-[25%] lg:w-[20%] ">
              <ul className="flex items-center gap-1">
                <li>
                  <a href="#" className="relative">
                    <IoNotificationsOutline />
                    <RiCheckboxBlankCircleFill className="absolute -right-0.5 -top-1  text-xs text-slate-800"/> 
                  </a>
                </li>
                <li>
                  <a href="#" className="flex items-center gap-1">
                    Nueva asamblea <RiArrowDownSLine  />
                  </a>
                </li>
              </ul>
            </nav>
          </header>
    </>
  )
}
