🍽️ FEIFOOD — Gerenciamento de Alimentos

O FEIFOOD é um sistema desenvolvido para facilitar o controle, organização e manutenção de informações sobre alimentos — seja comida pronta, ingredientes ou pratos.
O foco é simplicidade, agilidade e fácil manutenção.

Construído em Java, utilizando NetBeans e Apache Ant, o projeto segue uma estrutura modular para garantir melhor organização e escalabilidade.

🚀 Funcionalidades

✅ Cadastro de alimentos

✏️ Edição de itens já cadastrados

🗑️ Remoção de registros

📋 Listagem completa dos alimentos

🔍 Busca por nome

🧩 Estrutura modular dividida entre código, libs e configs

⚙️ Build automatizado via build.xml

🗂️ Estrutura do Projeto
FEIFOOD/
├─ build/          # Arquivos temporários de compilação
├─ dist/           # Arquivos finais gerados (build)
├─ lib/            # Bibliotecas externas
├─ nbproject/      # Configurações do NetBeans
├─ src/            # Código-fonte principal
├─ build.xml       # Script de build do Ant
└─ manifest.mf     # Metadados da aplicação

▶️ Como Executar
1️⃣ Clonar o repositório
git clone https://github.com/primol/FEIFOOD.git
cd FEIFOOD

2️⃣ Compilar o projeto com Ant (ou pelo NetBeans)
ant clean
ant build

3️⃣ Executar o programa

O .jar gerado fica na pasta dist/.

java -jar dist/FEIFOOD.jar

📌 Requisitos

☕ Java JDK 8+

🛠️ Apache Ant (caso compile via terminal)

🧪 NetBeans (opcional, mas recomendado para desenvolvimento)
