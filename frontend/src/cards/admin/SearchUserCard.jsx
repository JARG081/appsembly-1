/* eslint-disable react/prop-types */
import { RiCloseLine } from "react-icons/ri";
import { getData } from "../../helpers/getData";
import { useContext, useState } from "react";
import { UserContext } from "../../context/UserContext";

export const SearchUserCard = ({handleShowForm}) => {
    
    const {user} = useContext(UserContext);
    const token = user.jwt;

    const [userData, setUserData] = useState();
    const [findData, setFindData] = useState('');
    
    const handleFindData = (e)=>{
        setFindData(e.target.value);
    }

    const hadnleSubmit = async ()=>{
        setUserData();
        const data = await getData(`http://localhost:8080/user/search?personalCode=${findData}`,token);
        setUserData(data);
    }

    return (
        <>
            <div className="right-0 flex justify-end p-2">
                <RiCloseLine
                    onClick={handleShowForm}
                    className="text-4xl rounded-full text-purple-800 shadow-sm shadow-neutral-600 p-0 hover:text-purple-500 hover:cursor-pointer"
                />
            </div>
            <div className="px-12 py-6 mx-auto max-w-xl flex items-start justify-between">
                <input type="text" value={findData} onChange={handleFindData} className="rounded-md py-2 px-3 focus:outline-none w-full border-2" />
                <button onClick={hadnleSubmit}>Buscar</button>
            </div>
            {userData && 
                <form action="" className={`px-12 py-6 mx-auto max-w-lg flex flex-col items-start justify-between`}>
                    <div className="grid grid-cols-2 gap-2">
                        <div className="mb-4 w-full">
                            <label htmlFor="firstName">Nombre</label>
                            <input type="text" name="" defaultValue={userData.firstName} id="firstName" className="rounded-md py-2 px-3 focus:outline-none w-full border-2" />
                        </div>
                        <div className="mb-4 w-full">
                            <label htmlFor="lastName">Apellido</label>
                            <input type="text" name="" defaultValue={userData.lastName} id="lastName" className="rounded-md py-2 px-3 focus:outline-none w-full border-2" />
                        </div>
                        <div className="mb-4 w-full">
                            <label htmlFor="email">Correo</label>
                            <input type="text" name="" defaultValue={userData.email} id="email" className="rounded-md py-2 px-3 focus:outline-none w-full border-2" />
                        </div>
                        <div className="mb-4 w-full">
                            <label htmlFor="personalCode">Cédula</label>
                            <input type="text" name="" defaultValue={userData.personalCode} id="personalCode" className="rounded-md py-2 px-3 focus:outline-none w-full border-2" />
                        </div>
                    </div>
                    {/* ${successfulAssembly && "cursor-not-allowed"}`} */}
                    <button type="submit" className={`bg-blue-600 text-white font-semibold py-2 px-4 rounded-md hover:bg-blue-800 transition duration-300 ease-in-out self-end`}>
                        Actualizar usuario
                    </button>
                </form>
            }

        </>
    )
}