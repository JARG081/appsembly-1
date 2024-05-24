import { useState, useEffect } from 'react';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import { RiCloseLine } from "react-icons/ri";

export const QuestionCard = ({ handleSuccessful,handleShowForm,successfulAssembly, assembly }) => {
  const [stompClient, setStompClient] = useState(null);
  const [question, setQuestion] = useState('');
  const [answers, setAnswers] = useState(['sí', 'no']);
  const [assemblyID, setAssemblyID] = useState(`${assembly.assemblyID}`);

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
        assemblyID: assemblyID
      };
      stompClient.send('/send/answer', {}, JSON.stringify(message));
      setQuestion('');
    }
  };

  return (
    <>
        <div className="flex justify-end mb-0.5">
          <RiCloseLine
            onClick={handleShowForm}
            className="text-4xl rounded-full text-purple-800 shadow-md shadow-neutral-800 p-0 hover:text-purple-500 hover:cursor-pointer"
          />
        </div>
      <div>
        <input type="text" value={question} onChange={handleInputChange} />
        <button onClick={sendQuestion}>Enviar pregunta</button>
      </div>
    </>
  );
};
