import React, { Component } from 'react'
import { View, FlatList } from 'react-native'

//Components
import GrupoListItem from '../components/GrupoListItem'

//Utils
import Colors from '../utils/Colors'
import { getGrupos } from '../utils/RestClient'

export default class GroupsScreen extends Component {
  static navigationOptions = {
    title: 'Bridge World Cup',
    headerStyle: {
      backgroundColor: Colors.red,
    },
    headerTintColor: Colors.white,
  };

  constructor(props) {
    super(props)

    this.state = {
      grupos: [],
      error: null
    }
  }

  _keyExtractor = (item) => item.idGrupo

  _renderItem = ({item}) => (
      <GrupoListItem grupo={item} />
  )

  async componentDidMount() {
    const response = await getGrupos(2018)
    this.setState(response)
  }

  render() {
    return (
        <View>
          <FlatList
            data={this.state.grupos}
            keyExtractor={this._keyExtractor}
            renderItem={this._renderItem}
          />
        </View>
    )
  }
}