package khshanovskyi.ktaipromptengineering.assistants;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;

public interface UkrLawsAssistant {

    /*
    Task: Write prompts for a Ukrainian law and constitution expert model

    Steps:
    1. Identify the type of legal information the user needs help with. This could be anything from the Ukrainian Constitution, Ukrainian Criminal code, or Code of Civil Protection of Ukraine.
    2. Write a prompt that clearly states the legal question or problem. Make sure to include all necessary details for the model to understand the problem.
    3. Include a statement in the prompt that informs the model of its limitation, i.e., it can only answer questions related to the Ukrainian constitution and its laws.
    4. Write a fallback prompt for situations where the model might be asked a question that is not related to the Ukrainian constitution and its laws. This prompt
        should inform the user that the requested task is out of the model's duty.
    5. Ensure that the answer provided by the model does not exceed 2000 tokens. If it does, revise the prompt to make it more specific or limit the scope of the question.
    6. Review the prompts to ensure they are clear, concise, and follow the best practices for writing prompts.

    Example Prompts:
        1: "As an expert in Ukrainian law, can you provide a short and concise explanation of Article 5 of the Ukrainian Constitution?"
        2: "Remember, as a Ukrainian law expert, you can only answer questions related to the Ukrainian constitution and its laws. Can
            you help me understand the implications of Section 3 of the Ukrainian Criminal code?"
        3: "As an expert in Ukrainian law, I'm sorry but I can't assist with your request as it is not related to the Ukrainian constitution and its laws."
     */
    String SYSTEM_PROMPT = """
            You are an expert in Ukrainian Constitution and laws, Ukrainian Criminal code and Code of Civil Protection of Ukraine,
             and your role is consultant in the Ukrainian constitution and its laws.
            Your main goal is to provide SHORT and CONCISE answers with users questions related to Ukrainian constitution and its laws.
            You are strictly prohibited to answer to questions that are not relayed to the Ukrainian constitution and laws. In case if
             question do not related to Ukrainian constitution and its laws you need to respond that you are not eligible to answers
             that is not related to Ukrainian constitution and its laws.
            Make you answer not more than 2000 tokens.
            """;

    /**
     * Non-streaming
     */
    @SystemMessage(SYSTEM_PROMPT)
    String chat(String userMessage);

    /**
     * Streaming
     */
    @SystemMessage(SYSTEM_PROMPT)
    TokenStream chatStream(String userMessage);

}
