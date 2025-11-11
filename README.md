FEIFOOD
Descrição

O FEIFOOD é um projeto voltado para o gerenciamento e controle de alimentos, desenvolvido com foco em organização e praticidade.
A ideia principal é permitir o cadastro, listagem e manutenção de informações relacionadas a comidas, pratos ou ingredientes.

O sistema foi estruturado em Java, utilizando o NetBeans como ambiente de desenvolvimento e o Apache Ant para build.

Funcionalidades

Cadastro de itens de comida

Edição e remoção de registros

Listagem e busca de alimentos

Estrutura modular, com separação entre código-fonte e bibliotecas

Geração de build e distribuição automatizada via build.xml

Estrutura do Projeto
FEIFOOD/
├─ build/         # Arquivos temporários de compilação
├─ dist/          # Saída final do projeto (build)
├─ lib/           # Bibliotecas externas
├─ nbproject/     # Configurações do NetBeans
├─ src/           # Código-fonte principal
├─ build.xml      # Script de build (Ant)
└─ manifest.mf    # Metadados da aplicação

Como Executar

Clone o repositório:

git clone https://github.com/primol/FEIFOOD.git
cd FEIFOOD


Compile o projeto com o Ant (ou via NetBeans):

ant clean
ant build


Execute o projeto:

O arquivo final pode ser encontrado dentro da pasta dist/

Execute com:

java -jar dist/FEIFOOD.jar

Requisitos

Java JDK 8+

Apache Ant (se executar via linha de comando)

NetBeans (opcional, para desenvolvimento)
