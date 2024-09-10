## Task:

### Before start install Ollama [Link](https://ollama.com/blog/ollama-is-now-available-as-an-official-docker-image)
- If you are using Mac download and install it. 
- From Win/Linux you can run it in docker `docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama`
### Run Ollama3.1
- Mac `ollama run llama3.1`
- Win/Linux `docker exec -it ollama ollama run llama3.1`

## Pay attention that ollama version have to be 3.1 and could not be < 3.1. In case if you are using >3.2 change it in `application.yaml`

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