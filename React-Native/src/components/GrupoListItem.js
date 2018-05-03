import React, { Component } from 'react'
import { Text, Image, View, StyleSheet } from 'react-native'

//Components
import CardView from '../components/CardView'

//Utils
import Colors from '../utils/Colors'

export default class GrupoListItem extends Component {
  render() {
    const grupo = this.props.grupo

    return (
        <CardView style={styles.card}>
          <View style={styles.titleContainer}>
            <Text style={styles.title}>Grupo</Text>
            <Text style={[styles.title, styles.titleLetter]}>{grupo.letra}</Text>
          </View>
          <View style={styles.flagsContainer}>
            <Image source={{uri: grupo.selecoes[0].iconeURL}} style={styles.flag} />
          </View>
        </CardView>
    )
  }
}

const styles = StyleSheet.create({
  card: {
    flexDirection: 'row'
  },
  titleContainer: {
    flex: 1,
    padding: 8
  },
  title: {
    color: Colors.black,
    flex: 1,
    fontSize: 22,
    fontWeight: '700',
    textAlign: 'center'
  },
  titleLetter: {
    fontSize: 44
  },
  flagsContainer: {
    flex: 3,
    padding: 8
  },
  flag: {
    height: 23,
    resizeMode: 'contain',
    width: 35
  }
})