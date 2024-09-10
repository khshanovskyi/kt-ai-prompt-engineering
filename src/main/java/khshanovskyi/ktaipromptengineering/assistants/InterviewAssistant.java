package khshanovskyi.ktaipromptengineering.assistants;

import dev.langchain4j.service.SystemMessage;

public interface InterviewAssistant {

    /*
    Task: Write prompts for a programming technical interview question generator model

    Steps:
    1. Identify the topic of the programming technical interview from the user's request. This could range from specific
        programming languages to broader topics like data structures or algorithms.
    2. Write a prompt that clearly states the requirement of generating interview questions. Make sure to specify the
        number of questions needed and the topic they should be related to.
    3. Include a format in the prompt for the model to follow. This format should be "|Question number|Question|Difficulty|Evaluation|Comment|".
        Here, the 'Question number' should be an incremented ordered question number, 'Question' is the question to be asked,
        'Difficulty' is a rate from 1 to 10, and 'Evaluation' and 'Comment' fields should be left empty for interviewer fulfillment.
    4. Write a fallback prompt for situations where the model might be asked a request that is not related to programming interview help. This prompt should return an empty table.
    5. Review the prompts to ensure they are clear, concise, and follow the best practices for writing prompts.

    Example Prompts:
        1: "As an expert in preparing programming technical interview questions, can you generate the top 10 questions
            about Python data structures? Please follow this format: |Question number|Question|Difficulty|Evaluation|Comment|."
        2: "Remember, as a programming technical interview question generator, you can only generate questions related
            to programming topics. Can you generate the top 5 questions about SQL database management? Please follow this
            format: |Question number|Question|Difficulty|Evaluation|Comment|."
        3: "As an expert in preparing programming technical interview questions, I'm sorry but I can't assist with your
        request as it is not related to programming interview help. Here is the empty table as requested: |Question number|Question|Difficulty|Evaluation|Comment|."
     */
    String SYSTEM_PROMPT = """
            
            """;

    @SystemMessage(SYSTEM_PROMPT)
    String chat(String userMessage);
}
