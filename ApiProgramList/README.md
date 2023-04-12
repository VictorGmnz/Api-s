# ApiProgramList

// Api criada para o cliente Azimute Med.
// Objetivo: Listar um programa de medicamento na base de um cliente da Azimute que será informado no body. Baseado nas informações enviadas na requisição, a api deverá receber os parametros na requisição(sendo todos eles obrigatórios) e inserí-los na tabela CUSTOM_PROGRAM.
// Para entendimento melhor de quem for debugar este código, como ja explicando no readme principal deste repositório, existem alguns processos como classes, métodos e variáveis virtuais que ja são tratadas pela engine do CRM, neste código foram usados os seguintes atributos pré-definidos: 
1. _json(variável que contém o json enviado no corpo da requisição)
2. session.createSQLQuery(método que se conecta ao banco oracle através da classe java.sql.DriverManager)