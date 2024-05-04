import { useContext, useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { SuccessfulCard } from "../../cards/SuccessfulCard";
import { WarningCard } from "../../cards/WarningCard";
import { FormAssemblyCard } from "../../cards/admin/NewAssemblyCard";
import { onSubmitAssemblyHelper } from "../../helpers/HelperAssemblyForm";
import { onSubmitUserHelper } from "../../helpers/HelperUserForm";
import { AddUserCard } from "../../cards/admin/AddUserCard";
import { DeleteUserCard } from "../../cards/admin/DeleteUserCard";
import { UpdateUserCard } from "../../cards/admin/UpdateUserCard";
import { AssemblyFragment } from "../../components/AssemblyFragment";
import { ShowCard } from "../../cards/ShowCard";
import { DashboardCard } from "../../cards/admin/DashboardCard";
import { useAssemblies } from "../../hooks/useAssemblies";
import { getAssembliesHelper } from "../../helpers/GetAssemblies";
import { UserContext } from "../../context/UserContext";
import { formPropsHelper } from "../../helpers/formPropsHelper";

export const AdminPage = () => {
  const {user} = useContext(UserContext);

  const token = user.jwt;
  const {register:registerAssembly,handleSubmit: handleSubmitAssembly,formState: { errors:errorsAssembly },watch, reset} = useForm();
  const {register:registerUser,handleSubmit: handleSubmitUser,formState: { errors:errorsUser },watch:watchUser, reset:resetUser} = useForm();
  
  //Mostrar el formulario para crear nueva asamblea
  const [showFormAssembly, setShowFormAssembly] = useState(false);
  
  //Mostrar el formulario para crear nuevo usuario
  const [showFormAddUser, setShowFormAddUser] = useState(false);
 
  //Mostrar el formulario para eliminar un usuario
  const [showFormDeleteUser, setShowFormDeleteUser] = useState(false);
  
  //Mostrar el formulario para actualizar un usuario
  const [showFormUpdateUser, setShowFormUpdateUser] = useState(false);

  //Mostrar la notificación de proceso completado
  const [successfulAssembly, setSuccessfulAssembly] = useState(false);

  // Variable para almacenar las asambleas
  const [assemblies,setAssemblies] = useAssemblies({url:"http://localhost:8080/get/assembly"});
  console.log("LAS ASSEMBLIES SON: ", assemblies)

  useEffect(() => {
    
    const temporizador = setTimeout(() => {
      setSuccessfulAssembly(false);
      reset();
    }, 1500);

    // Limpia el temporizador cuando el componente se desmonta o cuando el estado cambia y el componente se oculta
    return () => clearTimeout(temporizador);

  }, [successfulAssembly]);

  const handleShowForm = () => {
    setShowFormAssembly(!showFormAssembly);
  };

  const handleShowFormAddUser = () => {
    setShowFormAddUser(!showFormAddUser);
  };
  
  const handleShowFormDeleteUser = () => {
    setShowFormDeleteUser(!showFormDeleteUser);
  };
  
  const handleShowFormUpdatesUser = () => {
    setShowFormUpdateUser(!showFormUpdateUser);
  };

  const handleSuccessful = () => {
    setSuccessfulAssembly(!successfulAssembly);
  };

  const onSubmitAssembly = async (formData) => {
    try {
      await onSubmitAssemblyHelper(formData, token);
      setAssemblies(await getAssembliesHelper({ url: "http://localhost:8080/get/assembly", token }));
      setSuccessfulAssembly(true);
      reset();
    } catch (error) {
      console.error("Error al enviar el formulario de la asamblea:", error);
    }
  };

  const formUpdateUser = formPropsHelper(registerUser,handleSubmitUser,handleShowFormUpdatesUser,errorsUser,successfulAssembly,(formData) => onSubmitUserHelper(formData,token,handleSuccessful))
  const formDeleteProps = formPropsHelper(registerUser, handleSubmitUser,handleShowFormDeleteUser,errorsUser,successfulAssembly,(formData) => onSubmitUserHelper(formData,token,handleSuccessful));
  const formUserProps = formPropsHelper(registerUser,handleSubmitUser,handleShowFormAddUser,errorsUser,successfulAssembly,(formData) => onSubmitUserHelper(formData,token,handleSuccessful,resetUser,"POST","http://localhost:8080/create/user"))
  const formAssemblyProps = formPropsHelper(registerAssembly,handleSubmitAssembly,handleShowForm,errorsAssembly,successfulAssembly,onSubmitAssembly)

  return (
    <>
      <div className="p-4 lg:p-10 bg-gray-100">
        {/* Title */}
        <div className={`mb-2 md:justify-center ${showFormAssembly ? "blur-sm" : ""}`}>
          <h1 className="text-3xl font-semibold">TABLERO DE ADMINISTRACIÓN</h1>
        </div>

        {/* Cards */}
        <div className={`flex flex-col md:grid md:grid-cols-2 gap-2 ${showFormAssembly ? "blur-sm" : ""}`}>
          {/* columna izquierda del tablero */}
          <div className="col-span-1 bg-gray-200">
            
            <div className="p-3 w-full">
              <DashboardCard title={"Promedio de asistencia"} customClass={"mb-4 bg-white flex justify-center w-full rounded-xl h-[225px]"}/>  
            </div>

            <div className="p-3 w-full">
              <DashboardCard title={"Asambleas creadas"} customClass={"mb-2 px-1 overflow-y-auto bg-white flex flex-col text-center w-full rounded-xl h-[225px]"}>
                  {assemblies && assemblies.map(assembly => (
                    <AssemblyFragment key={assembly.assemblyID} assembly={assembly}/>
                  ))}
              </DashboardCard>
            </div>

          </div>

          <div className="col-span-1 bg-red-500 text-2xl md:text-3xl">
            
            <div className="p-3 bg-gray-200 w-full">
              <DashboardCard title={"Crear una nueva asamblea"} customClass={"mb-0 bg-white flex flex-col items-center justify-around w-full rounded-xl h-[180px]"}>
                <button
                  onClick={handleShowForm}
                  className="bg-green-500 p-0.5 text-xl rounded-lg text-white  w-[150px] hover:shadow-lg hover:bg-green-600 transition duration-300 ease-in-out"
                >
                  Crear asamblea
                </button>
              </DashboardCard>
            </div>

            <div className="p-3 bg-gray-200 w-full">
              <DashboardCard customClass={"mb-0 bg-white flex flex-col items-center justify-evenly w-full rounded-xl h-[300px]"}>
                <WarningCard>
                    Cualquier cosa que hagas aquí modificará los registros de la
                    base de datos
                </WarningCard>
                <button 
                  onClick={handleShowFormAddUser}
                  className="bg-gray-100 w-[300px] md:w-[450px] text-2xl py-1 rounded-xl hover:bg-red-600 hover:border-4 hover:border-red-200 hover:text-slate-100 hover:font-semibold font-normal border-2 border-gray-300">
                  Agregar usuario
                </button>
                <button 
                  onClick={handleShowFormDeleteUser}
                  className="bg-gray-100 w-[300px] md:w-[450px] text-2xl py-1 rounded-xl hover:bg-red-600 hover:border-4 hover:border-red-200 hover:text-slate-100 hover:font-semibold font-normal border-2 border-gray-300">
                  Eliminar usuario
                </button>
                <button 
                  onClick={handleShowFormUpdatesUser}
                  className="bg-gray-100 w-[300px] md:w-[450px] text-2xl py-1 rounded-xl hover:bg-red-600 hover:border-4 hover:border-red-200 hover:text-slate-100 hover:font-semibold font-normal border-2 border-gray-300">
                  Actualizar usuario
                </button>
              </DashboardCard>
            </div>
          </div>
        </div>

        {/* Mostrar tarjetas */}
        <ShowCard show={showFormAssembly}>
          <SuccessfulCard text={"Asamblea creada exitosamente"} show={successfulAssembly}/>
          <h2 className="text-3xl p-2 text-white  font-bold">
            Nueva reunión:{" "}
            <span className="text-white font-normal shadow-sm">
              {watch("title")}
            </span>
          </h2>
          <div className="bg-slate-100 rounded-lg m-4">
            <FormAssemblyCard {...formAssemblyProps} />
          </div>
        </ShowCard>

        <ShowCard show={showFormAddUser} >
            <SuccessfulCard text={"Usuario creado exitosamente"} show={successfulAssembly} />
            <div className="bg-white rounded-lg m-4">
              <AddUserCard {...formUserProps} />
            </div>
        </ShowCard>
        
        <ShowCard show={showFormDeleteUser}>
            <SuccessfulCard text={"Usuario eliminado exitosamente"} show={successfulAssembly}/>
            <div className="bg-white rounded-lg m-4">
              <DeleteUserCard {...formDeleteProps} />
            </div>
        </ShowCard>
        
        <ShowCard show={showFormUpdateUser}>
            <SuccessfulCard text={"Usuario eliminado exitosamente"} show={successfulAssembly} />
            <div className="bg-white rounded-lg m-4">
              <UpdateUserCard {...formUpdateUser}  />
            </div>
        </ShowCard>

       
        

      </div>
    </>
  );
};
