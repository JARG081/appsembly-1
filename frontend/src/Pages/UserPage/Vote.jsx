import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import { useEffect, useState } from "react";
import { Header } from "../../components/Header";
import { Sidebar } from "../../components/Sidebar";
import { GrClose, GrMenu } from 'react-icons/gr';
import { useParams } from 'react-router-dom';
import { NoData } from '../../components/draws/NoData';

export const Vote = () => {

    const [stompClient, setStompClient] = useState(null);
    const [messages, setMessages] = useState([]);
    const [sidebar, setSidebar] = useState(false);
    const [timer,setTimer] = useState();
    const [id, setId] = useState();
    const [disabled, setDisabled] = useState(true);
    const { assemblyId } = useParams();


    const handleSidebar = () => {
        setSidebar(!sidebar);
    }
    

    useEffect(() => {
        const socket = new SockJS('http://localhost:8080/websock');
        const client = Stomp.over(socket);

        client.connect({}, () => {
            setStompClient(client);
        }, (error) => {
            console.error('Error al conectar al WebSocket:', error);
        });

        return () => {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
        };
    }, []);

    useEffect(() => {
        if (stompClient) {
            const subscription = stompClient.subscribe('/topic/questions', (message) => {
                const receivedMessage = JSON.parse(message.body);
                if (receivedMessage.type === 'question') {
                    setMessages(prevMessages => [...prevMessages, receivedMessage]);
                    console.log("La pregunta es: " + JSON.stringify(receivedMessage));
                    // Comenzar a contar el tiempo cuando llega una nueva pregunta
                    startTimer(receivedMessage.time);
                    setId(receivedMessage.questionID);
                    setDisabled(false);
                }
            });

            return () => {
                subscription.unsubscribe();
            };
        }
    }, [stompClient]);

    const startTimer = (time) => {
        setTimer(time / 1000); // Convertir el tiempo de milisegundos a segundos
        const intervalId = setInterval(() => {
            setTimer(prevTime => {
                if (prevTime <= 0) {
                    clearInterval(intervalId); // Detener el intervalo cuando el tiempo restante llega a 0
                    setMessages([]);
                    setDisabled(true);
                    return 0;
                } else {
                    return prevTime - 1;
                }
            });
        }, 1000); // Actualizar cada segundo
    };
    
    const sendAnswer = (answer) => {
        if (stompClient) {
            const message = {
                type: 'answer',
                content: answer,
                questionID:id,
                options: []
            };
            prompt("La respuesta elegida es: " + answer);
            stompClient.send('/send/answer', {}, JSON.stringify(message));
            setMessages([]);
        }
    };

    return (
        <>
        {
            <div className="h-screen grid grid-cols-1 lg:grid-cols-6">
                <div className="col-span-5">
                    <Header />
                    <div className="h-[90%] md:h-[85%] flex flex-col pt-32 items-center">
                        {disabled ?("") :(<p className="text-green-700 text-lg font-normal mt-4 font-serif">
                            Tiempo restante: 
                            <span className="text-red-500 pl-2">
                                {timer}
                                <span className="pl-1">
                                    segundos
                                </span>
                            </span> 
                        </p>)}
                        
                        {messages && messages.length > 0 ? (
                            <>
                                <div className=" w-5/6 rounded-xl border-4 shadow-2xl shadow-slate-500 border-slate-300 ">
                                    {messages.map((message, index) => (
                                        <>
                                            <div key={index} >
                                                {message.assemblyID === assemblyId && (
                                                    <>
                                                        <div>
                                                            <div className="p-10 text-center text-2xl bg-blue-50 overflow-y-auto w-full rounded-xl">
                                                                    <p className="font-normal">Pregunta: 
                                                                    <span className="font-light break-words text-pretty"> {message.content}</span> 
                                                                    </p>
                                                                
                                                            </div>
                                                            <p className="text-xl pl-5 w-fit font-extralight">Respuestas:</p>
                                                            <div className="flex flex-col p-5 h-full items-center text-2xl font-light">
                                                                {message.expired ? (
                                                                    <p key="timeout" className="mb-2 p-1 bg-white text-red-500">El tiempo ha expirado.</p>
                                                                ) : (
                                                                    message.options.map((option, optionIndex) => (
                                                                        <p key={optionIndex} onClick={() => sendAnswer(option)} className="mb-2 p-1 bg-white hover:cursor-pointer hover:bg-green-200 hover:shadow-md hover:rounded-md hover:shadow-slate-400">{option}</p>
                                                                    ))
                                                                )}
                                                            </div>
                                                        </div>
                                                        
                                                    </>
                                                )}
                                            </div>
                                        </>
                                    ))}
                                </div>
                            </>
                            
                            ) : (
                            <>
                                <NoData text={"No hay preguntas"}/>
                            </>
                        )}
                    </div>

                </div>
                <Sidebar sidebar={sidebar} />
                <button onClick={handleSidebar} className="block fixed  bottom-4 right-4 bg-blue-500 p-4 text-white rounded-full text-2xl lg:hidden ">
                    {sidebar ? <GrClose />:<GrMenu />}
                </button>
            </div>

        }
        </>
    )
}
