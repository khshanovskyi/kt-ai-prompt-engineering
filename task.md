## Task:

## Prerequisites:
### Choose LLM that you will use:
- **Ollama:** Run locally `llama3.1`, **PAY ATTENTION THAT IT CAN WORK SLOW DEPENDING ON MACHINE**
   - On Windows/Linux or Mac with Intel:
      - pull image and run ollama `docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama`
      - run llama3.1 `docker exec -it ollama ollama run llama3.1`
   - On Mac with M-series processors:
      - install ollama from [link](https://ollama.com/blog/ollama-is-now-available-as-an-official-docker-image)
      - run `ollama run llama3.1`
      -
- **OpenAI: PAY ATTENTION THAT YOU NEED TO PAY CREDIT (1$-10$) TO USE ITS API** [OpenAI Platform](https://platform.openai.com/)
   - add your API_KEY to `application.yaml` -> `open-ai.api-key` instead of `WRONG_API_KEY` (if you have it env variables check that it has proper name OPENAI_API_KEY)
   - set `open-ai.enabled` as `true' in the `application.yaml`
   - by default, we are using `gpt-3.5-turbo` model

##
1. You need to write prompt for `CalculatorAssistant` and test it:
    - Write system prompt according to best practices
    - Run Application
    - Test it via Postman. `localhost:8080/v1/calculator/chat?question=<Your question>`
    - **Optional:** take a look at the code (`CalculatorAssistantConfig`)
    - 
2. You need to write prompt for `UkrLawsAssistant` and test it:
   - Write system prompt according to best practices
   - Run Application
   - Test streaming in browser `localhost:8080`
   - Test it via Postman. `localhost:8080/v1/laws/chat?question=<Your question>`
   - Identify differences between streaming approach and regular REST calls
   - **Optional:** take a look at the code (`UkrLawsAssistantConfig`)
   - 
3. You need to write prompt for `InterviewAssistant` and test it:
   - Write system prompt according to best practices
   - Run Application
   - Test it via Postman. `localhost:8080/v1/interview/chat?question=<Your question>`
   - **Optional:** take a look at the code (`InterviewAssistantConfig`)


[Read more](https://medium.com/@springs_apps/prompt-engineering-examples-and-best-practices-82b1da724643)