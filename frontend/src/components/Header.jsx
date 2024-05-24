import { IoNotificationsOutline } from "react-icons/io5";
import {RiCheckboxBlankCircleFill, RiArrowDownSLine} from "react-icons/ri";


export const Header = () => {
  return (
    <>
    <header className="flex flex-col md:flex-row items-center justify-center md:justify-end w-full h-[10%] md:h-[15%] bg-slate-100">
            
            {/* Notifications */}
            <nav className="w-full flex md:mb-0 justify-center  md:justify-end md:w-[25%] lg:w-[20%]">
              <ul className="flex items-end gap-1  p-5">
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
