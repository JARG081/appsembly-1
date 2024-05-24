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
  
    const {assemblyId} = useParams();
    const handleSidebar = ()=>{
        setSidebar(!sidebar)
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
            console.log("La pregunta es: " + JSON.stringify(receivedMessage))
        }
        });

        return () => {
        subscription.unsubscribe();
        };
    }
    }, [stompClient]);

    const sendAnswer = (answer) => {
    if (stompClient) {
        const message = {
        type: 'answer',
        content: answer,
        options: []
        };
        prompt("La respuesta elegida es: " + answer)
        stompClient.send('/send/answer', {}, JSON.stringify(message));
    }
    };
console.log("assemblyID del messages es: " + messages.assemblyID + " el assemblyId es: " +assemblyId )
  return (
        <>
        {/* <div className="min-h-screen flex flex-wrap md:grid grid-col-1 md:grid-col-3 lg:grid-cols-6">
            <div className="col-span-5">
                <Header />
                <AdminPage />
            </div>
            <Sidebar sidebar={sidebar} />

            <button onClick={handleSidebar} className="block fixed  bottom-4 right-4 bg-blue-500 p-4 text-white rounded-full text-2xl lg:hidden ">
            {sidebar ? <GrClose/>:<GrMenu />}
            </button>
        </div> */}
        {
            
            <div className="h-screen grid grid-cols-1 lg:grid-cols-6">
                <div className="col-span-5">
                    <Header />
                    <div className=" bg-slate-200 h-[90%] md:h-[85%] flex flex-col justify-center items-center">
                            {messages && messages.length > 0 ? (
                                <div className="bg-slate-100 w-5/6 h-5/6 rounded-xl border-2 shadow-xl border-green-500">
                                    {messages.map((message, index) => (
                                        <div key={index}>
                                            {message.assemblyID===assemblyId && (
                                                <>
                                                    <div className='flex flex-col mx-auto '>
                                                        <div className=' p-16 text-center text-2xl bg-red-200'>
                                                            <p>Pregunta: {message.content}</p>
                                                        </div>
                                                        <div className="flex flex-col space-x-1 bg-blue-500">
                                                            {message.options.map((option, optionIndex) => (
                                                                <p key={optionIndex} onClick={() => sendAnswer(option)} className="m-0">{option}</p>
                                                            ))}
                                                        </div>
                                                    </div>
                                                </>
                                            )}
                                        </div>
                                    ))}
                                </div>) : (
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
