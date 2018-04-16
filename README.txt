Estória;
	COMEXPORT Java Challenge; Avaliação do desenvolvimento backend para rest API utilizando Java.

Narrativa;
Como a 'COMEXPORT'
Desejo ter 'um rest API para lançamentos e recuperação de contas contábeis'
Que me permita 'inserir, recuperar e listar os registros de contas contábeis, além de me permitir fazer algumas estatísticas simples nos registros armazenados'

Cenário 1: Dados obrigatórios não fornecidos na inserção
Dado 'Um registro ContaContabil'
Quando 'esta ContaContabil não contém todas as propriedades mandatórias'
Então 'uma exceção deve ser levantada, indicando o problema em questão'

Cenário 2: Dados fornecidos na inserção não são válidos
Dado 'Um registro ContaContabil'
Quando 'esta ContaContabil contém as propriedades mandatórias mas as informações não são válidas'
Então 'uma exceção deve ser levantada, indicando o problema em questão'

Cenário 3: Inserção com sucesso
Dado 'Um registro ContaContabil'
Quando 'esta ContaContabil contém todos as propriedades devidamente preenchidas e válidas'
Então 'deve ser calculado internamente um valor UUID que identifica o registro em questão'
	'o registro em questão deve ser armazenado internamente'
	'o UUID calculado deve ser retornado para quem requisitou o armazenamento do registro ContaContabil'

Cenário 4: Recuperação com falha, de um registro ContaContabil com UUID inválido
Dado 'Uma String que deve corresponder a um registro ContaContabil'
Quando 'ao fazer o parsing desta String e a mesma não corresponder devidamente a um UUID válido'
Então 'uma exceção deve ser levantada, indicando o problema em questão'

Cenário 5: Recuperação com falha, de um registro ContaContabil com UUID válido porém não existe um registro ContaContabil correspondente
Dado 'Uma String que deve corresponder a um registro ContaContabil'
Quando 'ao fazer o parsing desta String'
	E 'tentar obter da lista interna de registros uma ContaContabil identificado pelo UUID em questão, mas nenhum registro correspondente for encontrado'
Então 'uma exceção deve ser levantada, indicando o problema em questão'

Cenário 6: Recuperar com sucesso, um registro ContaContabil baseado num UUID
Dado 'Uma String que deve corresponder a um registro ContaContabil'
Quando 'ao fazer o parsing desta String'
	E 'tentar obter da lista interna de registros uma ContaContabil identificado pelo UUID em questão'
	E 'um e apenas um registro deve existir para o UUID em questão'
Então 'o registro encontrado baseado no UUID deve ser retornado'

Cenário 7: Listagem de registros ContaContabil
Dado 'A solicitação de listagem de todos os registros'
Quando 'há N registros armazenados internamente (N >= 0)'
Então 'retornar uma lista contendo os registros armezenados internamente'

Cenário 8: Listagem com falha, de registros ContaContabil filtrados pelo parâmetro "contaContabil" inválido
Dado 'Um parâmetro chamado "contaContabil", na solicitação de listagem de todos os registros filtrados por este parâmetro'
Quando 'o parâmetro em questão é null, string vazia ou não é um Integer válido'
Então 'uma exceção deve ser levantada, indicando o problema em questão'

Cenário 9: Listagem com sucesso, de registros ContaContabil filtrados pelo parâmetro "contaContabil"
Dado 'Um parâmetro chamado "contaContabil", na solicitação de listagem de todos os registros filtrados por este parâmetro'
Quando 'o parâmetro em questão é um Integer válido'
	E 'aplicado um filtro sobre a lista de registros atualmente armazenado internamente'
Então 'retornar a lista de registros atualmente armazenada após aplicação do filtro com base no parâmetro "contaContabil"'

Cenário 10: Aplicação de operações estatísticas sobre os lançamentos de ContaContabil atualmente armazenados
Dado 'Solicitação da apresentação de estatísticas para os registros existentes de ContaContabil'
Quando 'há N registros armazenados internamente (N >= 0)'
	E 'aplicar sobre a propriedade "valor" da lista atual de ContaContabil as operações; soma, min, max, media e qtde'
Então 'retornar um registro com propriedades e valores resultantes das operações; soma, min, max e qtde'

Cenário 11: Aplicação com falha, de operações estatísticas sobre os lançamentos de ContaContabil, baseado no parâmetro "contaContabil" inválido
Dado 'Um parâmetro chamado "contaContabil", na solicitação  da aplicação de estatísticas nos os registros filtrados por este parâmetro'
Quando 'o parâmetro em questão é null, string vazia ou não é um Integer válido'
Então 'uma exceção deve ser levantada, indicando o problema em questão'

Cenário 12: Aplicação com sucesso, de operações estatísticas sobre os lançamentos de ContaContabil atualmente armazenados, filtrado pelo parâmetro "contaContabil"
Dado 'Solicitação da apresentação de estatísticas para os registros existentes de ContaContabil, sendo estes registros filtrados pelo parâmetro "contaContabil"'
Quando 'há N registros armazenados internamente (N >= 0)'
	E 'aplicar filtro dos tais registros baseados no parâmetro em questão "contaContabil"'
	E 'aplicar os registros resultantes, sobre suas correspondentes propriedade "valor", as operações; soma, min, max, media e qtde'
Então 'retornar um registro com propriedades e valores resultantes das operações; soma, min, max e qtde'
