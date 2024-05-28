package appsembly.appsembly.services;

import java.util.List;

import appsembly.appsembly.entities.QuestionEntity;
import appsembly.appsembly.services.models.dtos.QuestionDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;
// Definición de la interfaz IQuestionService, que declara los métodos relacionados con las preguntas
public interface IQuestionService {
    // Método para crear una nueva pregunta. Recibe un QuestionDTO como parámetro y devuelve un ResponseDTO.
    // Puede lanzar una excepción en caso de error.
    public ResponseDTO create(QuestionDTO question) throws Exception;
    // Método para obtener una lista de todas las preguntas asociadas a una asamblea específica.
    // Recibe el ID de la asamblea como parámetro y devuelve una lista de QuestionEntity.
    public List<QuestionEntity> findAllQuestions(String assemblyID);
}
