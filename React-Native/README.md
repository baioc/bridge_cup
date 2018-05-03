# React Native

Utilize este projeto se escolher resolver o desafio utilizando JavaScript para React Native.

Abaixo algumas recomendações e dicas:

**Caso nunca tenha usado o React Native, por favor siga os passos para configurar seu ambiente e instalar os componentes necessários para o React Native conforme a [Documentação Oficial](https://facebook.github.io/react-native/docs/getting-started.html)**

## Passos para rodar o projeto:
1. Clone o repositório 
2. Caso ainda não tenha instalado, instale o [NodeJS](https://nodejs.org/en/)
3. Garanta que tanto o NodeJS e o NPM estão instalados, digitando os seguintes comandos no terminal:
```
node -v
npm -v
```
4. Instale o Gerenciador de Pacotes [Yarn](https://yarnpkg.com/en/docs/install) (também funciona com o NPM se quiser, mas os comandos abaixo precisam ser adaptados)
5. Na pasta `desafio-mobile-copa-do-mundo/React-Native` digite no terminal:
```
yarn install
```
6. Escolha  um emulador ou dispositivo físico para rodar:
- Android:
`yarn react-native run-android`
- iOS:
`yarn react-native run-ios`

**Atenção**:
- O React-Native não consegue lançar o emulador do Android, então antes de rodar o comando acima, conecte seu dispositivo físico via USB ou lance um emulador pelo Android Studio. Para ver quais dispositivos estão ligados, no terminal:
```
adb devices
```
