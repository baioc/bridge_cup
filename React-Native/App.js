import React, { Component } from 'react'
import { StackNavigator } from 'react-navigation'

import GruposScreen from './src/screens/GruposScreen'

const Stack = StackNavigator({
  Grupos: { screen: GruposScreen }
});

export default class App extends Component {
  render() {
    return (
      <Stack />
    )
  }
}
