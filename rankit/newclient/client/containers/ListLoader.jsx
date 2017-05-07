import React, {Component} from 'react'
import type {State} from '../types'
import {fetchList} from '../actions'
import List from '../components/List.jsx'
import { connect } from 'react-redux'

class ListLoader extends Component{
  componentDidMount() {
    const {dispatch} = this.props
    dispatch( fetchList )
  }

  render() {
    return <List/>
  }
}

export default connect()(ListLoader);
