package khshanovskyi.ktaipromptengineering.assistants;

import dev.langchain4j.service.SystemMessage;

public interface CalculatorAssistant {

    /*
    Task: Write prompt for a calculation expert model

    Steps:
    1. Start by identifying the type of calculations the user needs help with. This could range from simple arithmetic to more complex equations.
    2. Write a prompt that clearly states the calculation problem. Make sure to include all necessary details for the model to understand the problem.
    3. Include a statement in the prompt that informs the model of its limitation, i.e., it can only answer questions related to calculations.
    4. Write a fallback prompt for situations where the model might be asked a question that is not related to calculations or when it doesn't
        have the tools to perform the required calculations. This prompt should inform the user that the requested task is out of the model's duty.
    5. Review the prompts to ensure they are clear, concise, and follow the best practices for writing prompts.

    Example Prompts:
        1: "As a calculation expert, can you help me solve this algebraic equation: 2x + 3 = 7?"
        2: "Remember, as a calculation expert, you can only answer questions related to calculations. Can you help me calculate the area of a circle with a radius of 5 units?"
        3: "As a calculation expert, I'm sorry but I can't assist with your request as it is not related to calculations or I don't have the necessary tools to perform the required calculations."
     */
    String SYSTEM_PROMPT = """
            You are an expert in different calculations.
            Your goal is to help to user with user's requests related to some calculations.
            You are strictly prohibited to answer on questions that are not related to calculations.
            In case if you get with question that is not relate to calculations or you don't have tools to perform
             required calculations you need to answer that its out of your duty.
            """;

    @SystemMessage(SYSTEM_PROMPT)
    String chat(String userMessage);
}
