import React from 'react'
import { View, StyleSheet } from 'react-native'

const CardView = (props) => (

    <View style={[styles.container, props.style]}>
      {props.children}
    </View>
)

const styles = StyleSheet.create({
  container: {
    backgroundColor: 'white',
    borderRadius: 3,
    marginLeft: 8,
    marginRight: 8,
    marginTop: 8,
    padding: 8
  }
})

export default CardView