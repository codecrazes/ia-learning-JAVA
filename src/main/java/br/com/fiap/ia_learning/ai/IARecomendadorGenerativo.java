package br.com.fiap.ia_learning.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IARecomendadorGenerativo {

    private final ChatClient chatClient;

    public String gerarRecomendacao(String profissao, String tarefa, String dificuldade, Integer tempo) {

        String prompt = """
                Você é uma IA que ensina pessoas com base na sua profissão.

                Profissão: %s
                Tarefa: %s
                Dificuldade: %s
                Tempo disponível: %d minutos

                Gere:
                - Explicação simples
                - Insights importantes
                - Passo a passo prático
                - Recomendações de ferramentas ou IA
                
                Responda de forma clara.
                """.formatted(profissao, tarefa, dificuldade, tempo);

        return chatClient.call(new Prompt(new UserMessage(prompt))).getResult().getOutput().getContent();
    }
}
