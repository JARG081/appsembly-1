import { useState, useEffect } from 'react';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import { RiCloseLine } from "react-icons/ri";

export const QuestionCard = ({ handleSuccessful, handleShowForm, successfulAssembly, assembly }) => {
  const [stompClient, setStompClient] = useState(null);
  const [question, setQuestion] = useState('');
  const [answers, setAnswers] = useState(['sí', 'no']);
  const [assemblyID, setAssemblyID] = useState(`${assembly.assemblyID}`);
  const [disabledBtn, setDisabledBtn] = useState(false);

  useEffect(() => {
    const connectWebSocket = () => {
      const socket = new SockJS('http://localhost:8080/websock');
      const client = Stomp.over(socket);
      client.connect({}, () => {
        setStompClient(client);
      }, (error) => {
        console.error('Error al conectar al WebSocket:', error);
      });
    };

    connectWebSocket();

    return () => {
      if (stompClient !== null) {
        stompClient.disconnect();
      }
    };
  }, []);

  const handleInputChange = (e) => {
    setQuestion(e.target.value);
  };

  const sendQuestion = () => {
    if (stompClient && question.trim() !== '') {
      
      const message = {
        type: 'question',
        content: question,
        options: answers,
        assemblyID: assemblyID,
        time: 20
      };

      stompClient.send('/send/answer', {}, JSON.stringify(message));
      
      setQuestion('');
      setDisabledBtn(true); // Deshabilita el botón al enviar la pregunta
      setTimeout(() => {
        setDisabledBtn(false); // Habilita nuevamente el botón después de 60 segundos
      }, 150000); // 60 segundos en milisegundos

    }
  };

  return (
    <>
      <div className="flex flew-row justify-end mb-0.5 p-2">
        <RiCloseLine
          onClick={handleShowForm}
          className="text-4xl rounded-full text-purple-800 shadow-md shadow-neutral-800 p-0 hover:text-red-600 hover:cursor-pointer"
        />
      </div>
      <div className="flex flex-col items-center justify-center p-4 w-[350px] md:w-[700px]">
        <div className="flex flex-col items-start w-5/6">
        <p className="text-lg">Ingresa la pregunta</p>
          <textarea 
            value={question} 
            onChange={handleInputChange} 
            className="overflow-auto border-2 rounded-md w-full mr-2 px-2 py-1 resize-none"
            // Ajusta el número inicial de filas
          />
          <button 
            onClick={sendQuestion} 
            className={`${disabledBtn ? 'pointer-events-none bg-red-300' : ''} p-2 mt-2 hover:cursor-pointer bg-slate-200 rounded-md  self-end`}
          >
            {disabledBtn ? "No puedes enviar (votando) 🚫":"Enviar pregunta"}
          </button>
        </div>
      </div>
    </>
  );
};
